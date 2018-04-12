package performance.db;

import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Lukasz Karmanski
 */
public class MongoConnector {

    private final static String MONGO_DB_HOST = "localhost";
    private final static Integer MONGO_DB_PORT = 27017;



    public void prepareConnection() {
        MongoClient mongoClient = new MongoClient( MONGO_DB_HOST , MONGO_DB_PORT );
        MongoDatabase db = mongoClient.getDatabase("database name");

        MongoCollection<Document> collection = db.getCollection("test");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "test");

        FindIterable<Document> result = collection.find(searchQuery);
        for(Document doc : result) {
            String test = doc.getString("test");
        }

    }

}
