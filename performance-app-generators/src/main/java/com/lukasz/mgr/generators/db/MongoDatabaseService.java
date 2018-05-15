package com.lukasz.mgr.generators.db;

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
import com.lukasz.mgr.api.dijkstra.Graph;
import com.lukasz.mgr.api.dijkstra.Graph2;
import com.lukasz.mgr.api.dijkstra.Node;
import com.lukasz.mgr.api.dijkstra.Vertex2;
import com.lukasz.mgr.api.users.User;
import com.lukasz.mgr.generators.control.dijkstra.DijkstraGraphGenerator;
import com.lukasz.mgr.generators.control.users.UsersGenerator;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

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

    @POST
    @Path("/users")
    public void insertUsers(@QueryParam("collectionName") String collectionName,
                            @QueryParam("count") Integer count) {
        List<User> users = UsersGenerator.generate(count);
        MongoCollection<Document> collection = mongoConnector.getCollection(collectionName);
        List<Document> rows = new ArrayList<>();
        int i = 0;
        for(User user : users) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("name", user.getName());
            dataMap.put("surname", user.getSurname());
            dataMap.put("age", user.getAge());
            dataMap.put("birthDate", user.getBirthDate());
            dataMap.put("uuid", user.getUuid());
            Document doc = new Document(dataMap);
            rows.add(doc);
            if(i > 0 && i % 1000 == 0) {
                collection.insertMany(rows);
                rows = new ArrayList<>();
            }
            i++;
        }
        collection.insertMany(rows);
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

    @GET
    @Path("dijkstra2")
    public Graph2 getDijkstra2Graph(@QueryParam("collectionName") String collectionName) {
        Objects.requireNonNull(collectionName);

        MongoCollection<Document> graphCollection = mongoConnector.getCollection(collectionName);
        FindIterable<Document> documents = graphCollection.find();
        Graph2 graph = new Graph2();

        for (Document doc : documents) {
            String name = (String) doc.get("name");
            Map<String, Integer> subNodesMap = (Map<String, Integer>) doc.get("nodes");
            List<Vertex2> subNodes = subNodesMap.entrySet().stream()
                    .map(e -> new Vertex2(e.getKey(), e.getValue()))
                    .collect(Collectors.toList());
            graph.addVertex(name, subNodes);
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
