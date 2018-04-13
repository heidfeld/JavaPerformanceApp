package performance.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import performance.control.algorithm.dijkstra.DijkstraGraphGenerator;
import performance.control.algorithm.dijkstra.Graph;
import performance.control.algorithm.dijkstra.Node;

/**
 * Created by Lukasz Karmanski
 */
@Path("mongo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MongoDatabaseService {

    @Inject
    private MongoConnector mongoConnector;

    @POST
    @Path("dijkstra")
    public void insertDijkstraGraph(@QueryParam("nodeCount") Integer nodeCount, @QueryParam("collectionName") String collectionName) {
        Objects.requireNonNull(nodeCount);
        Objects.requireNonNull(collectionName);
        Graph graph = DijkstraGraphGenerator.generate(nodeCount);

        MongoCollection<Document> graphCollection = mongoConnector.getCollection(collectionName);
        List<Document> rows = new ArrayList<>();

        for(Node node : graph.getNodes()) {

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("_id", node.getName());
            dataMap.put("name", node.getName());

            Map<Node, Integer> adjacentNodes = node.getAdjacentNodes();
            Map<String, Integer> dbMap = adjacentNodes.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().getName(), e -> e.getValue()));
            dataMap.put("nodes", dbMap);

            Document doc = new Document(dataMap);
            rows.add(doc);

        }
        graphCollection.insertMany(rows);
    }

    @GET
    @Path("dijkstra")
    public Graph getDijkstraGraph(@QueryParam("collectionName") String collectionName) {
        Objects.requireNonNull(collectionName);

        MongoCollection<Document> graphCollection = mongoConnector.getCollection(collectionName);
        FindIterable<Document> documents = graphCollection.find();
        Graph graph = new Graph();

        List<Node> nodes = new ArrayList<>();

        for (Document doc : documents) {
            String name = (String) doc.get("name");
            nodes.add(new Node(name));
        }

        for (Document doc : documents) {
            String name = (String) doc.get("name");
            Map<String, Integer> subNodesMap = (Map<String, Integer>) doc.get("nodes");
            Node rootNode = getNode(nodes, name);
            subNodesMap.entrySet().stream().forEach(e -> rootNode.addDestination(getNode(nodes, e.getKey()), e.getValue()));
            graph.addNode(rootNode);
        }
        return graph;
    }

    private Node getNode(List<Node> nodes, String name) {
        return nodes.stream()
                        .filter(n -> n.getName().equals(name))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No Node with specified id."));
    }

}
