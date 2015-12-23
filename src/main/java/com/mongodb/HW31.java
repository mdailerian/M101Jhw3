package com.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.orderBy;

/**
 * Created by MMAA-local on 10/25/2015.
 */
public class HW31 {
    public static void main(String[] args) {
        System.out.println(" Start");
        MongoClient mongoClient = new MongoClient();
        DB school = mongoClient.getDB("school");
        DBCollection studentsCollection = school.getCollection("students");

        DBCursor students = studentsCollection.find();

        DBObject student = null;
        while(students.hasNext()) {
            student = students.next();
            //System.out.println( student);
            List<DBObject> scores = (List<DBObject>)student.get("scores");
            //System.out.println( scores.toString());

            DBObject lowestScore = null;

            for(DBObject scoreObject : scores) {

                double score = (Double) scoreObject.get("score");
                String scoreType = (String) scoreObject.get("type");

                if (lowestScore == null && "homework".equals(scoreType)) {
                    lowestScore = scoreObject;
                    continue;
                }
                if(lowestScore != null && (score < (Double)lowestScore.get("score") && ("homework").equals(scoreType))) {
                    lowestScore = scoreObject;
                }
            }
            //System.out.println(lowestScore.toString());
            scores.remove(lowestScore);
            studentsCollection.update(new BasicDBObject("_id", student.get("_id")), new BasicDBObject("$set", new BasicDBObject("scores", scores)));
        }

        System.out.println(" End");
    }
}
