package com.lukasz.mgr.generators.db;

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

    private final static String MONGO_DB_NAME = "dijkstra";
    //private final static String MONGO_DB_NAME = "users";

    MongoDatabase db;

    @PostConstruct
    public void prepareConnection() {
        MongoClient mongoClient = new MongoClient( MONGO_DB_HOST , MONGO_DB_PORT );
        MongoDatabase db = mongoClient.getDatabase(MONGO_DB_NAME);

        Objects.requireNonNull(mongoClient);
        this.db = db;
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return db.getCollection(collectionName);
    }



}
