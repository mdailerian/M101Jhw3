package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by MMAA-local on 10/25/2015.
 */
public class InsertTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("insertTest");

        //coll.drop();

        Document smith = new Document("name", "Smith")
                .append("age", 30);

        Document jones = new Document("name", "Jones")
                .append("age", 35);

//        printJson( smith );

//        coll.insertMany(asList(smith, jones));

    }
}
