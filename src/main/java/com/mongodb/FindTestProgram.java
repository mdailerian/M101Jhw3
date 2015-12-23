package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class FindTestProgram {
        public static void main(String[] args) {

            System.out.println(" Start");

            MongoClient client = new MongoClient();
            MongoDatabase db = client.getDatabase("course");
            MongoCollection<Document> coll = db.getCollection("findTest");

            //coll.drop();

//            for(int i=0; i<10; i++)
  //              coll.insertOne(new Document("x",i));

            System.out.println(" End");

            System.out.println( "Size: " + coll.count());

        }
    }


