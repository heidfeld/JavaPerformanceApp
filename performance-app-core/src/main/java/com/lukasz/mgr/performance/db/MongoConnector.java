package com.lukasz.mgr.performance.db;

import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Lukasz Karmanski
 */
@ApplicationScoped
public class MongoConnector {

    private final static String MONGO_DB_HOST = "localhost";
    private final static Integer MONGO_DB_PORT = 27017;

    private final static String DIJKSTRA_DATABASE = "dijkstra";
    private final static String USERS_DATABASE = "users";

    MongoDatabase dijkstraDatabase;
    MongoDatabase usersDatabase;

    @PostConstruct
    public void prepareConnection() {
        MongoClient mongoClient = new MongoClient( MONGO_DB_HOST , MONGO_DB_PORT );
        MongoDatabase dijkstraDatabase = mongoClient.getDatabase(DIJKSTRA_DATABASE);
        MongoDatabase usersDatabase = mongoClient.getDatabase(USERS_DATABASE);

        Objects.requireNonNull(mongoClient);
        this.dijkstraDatabase = dijkstraDatabase;
        this.usersDatabase = usersDatabase;
    }

    public MongoCollection<Document> getDijkstraCollection(String collectionName) {
        return dijkstraDatabase.getCollection(collectionName);
    }

    public MongoCollection<Document> getUsersCollection(String collectionName) {
        return usersDatabase.getCollection(collectionName);
    }

}
