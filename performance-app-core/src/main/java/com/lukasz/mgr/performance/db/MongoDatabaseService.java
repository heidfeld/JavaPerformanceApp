package com.lukasz.mgr.performance.db;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.lukasz.mgr.api.users.User;
import com.mongodb.BasicDBObject;
import org.bson.BSON;
import org.bson.Document;
import com.lukasz.mgr.api.dijkstra.Graph;
import com.lukasz.mgr.api.dijkstra.Graph2;
import com.lukasz.mgr.api.dijkstra.Node;
import com.lukasz.mgr.api.dijkstra.Vertex2;
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

    @GET
    @Path("dijkstra")
    public Graph getDijkstraGraph(@QueryParam("collectionName") String collectionName) {
        Objects.requireNonNull(collectionName);

        MongoCollection<Document> graphCollection = mongoConnector.getDijkstraCollection(collectionName);
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

        MongoCollection<Document> graphCollection = mongoConnector.getDijkstraCollection(collectionName);
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

    @GET
    @Path("user/random")
    public User getRandomUser(@QueryParam("collectionName") String collectionName) {
        MongoCollection<Document> usersCollection = mongoConnector.getUsersCollection(collectionName);

        String[] surnames = new String[]{
                "Smith","Johnson","Williams","Jones","Brown","Davis","Miller","Wilson","Moore",
                "Taylor","Anderson","Thomas","Jackson","White","Harris","Martin","Thompson","Garcia",
                "Martinez","Robinson","Clark","Lewis","Lee","Walker","Hall","Allen","Young",
                "King","Wright","Hill","Scott","Green","Adams","Baker","Carter","Turner",
        };

        int random = ThreadLocalRandom.current().nextInt(surnames.length - 1);

        BasicDBObject query = new BasicDBObject();
        query.put("surname", surnames[random]);

        Document userDocument = usersCollection.find(query).first();
        User user = new User();
        user.setAge(userDocument.getInteger("age"));
        user.setBirthDate(userDocument.getDate("birthDate"));
        user.setName(userDocument.getString("name"));
        user.setSurname(userDocument.getString("surname"));
        user.setUuid(userDocument.getString("uuid"));
        return user;
    }
}
