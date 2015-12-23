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
public class Homework23 {
    public static void main(String[] args) {
        System.out.println(" Start");

        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> coll = db.getCollection("grades");

        // sort by stufdent and then by score
        //iterate and print
/*
        BasicDBObject query = new BasicDBObject("type","homework");
//        FindIterable<Document> cursor = coll.find(query).sort(new BasicDBObject("student_id", 1).append("score", 1));
        DBCursor cursor = coll.find(query).sort(new BasicDBObject("student_id",1).append("score",1));
                 int id = -1;
                try {
                        while(cursor.hasNext()) {
                                 DBObject obj = cursor.next();
                                 int curId = (Integer) obj.get("student_id");
                                if(id != curId){
                                 coll.remove(new BasicDBObject("_id",obj.get("_id")));
                                 id=curId;
                             }
                         }
                     } finally {
                         cursor.close();
                     }

*/
        Bson projection = fields(include("student_id", "type", "score"), excludeId());
        //Bson sort = new Document("student", 1).append("score", 1); // 1 ascending/ -1 descending
        //Bson sort = orderBy( ascending( "student"), ascending("score"));
        Bson sort = ascending( "student_id", "score");

        Bson filter = new Document( "type", "homework");
        List<Document> all = coll.find(filter).projection(projection).sort(sort).into( new ArrayList<Document>());

        int count = 0;
        String comparator = "-1";

        for( Document cur : all)
        {
            System.out.println(cur);
            Object id = cur.get("student_id");
            String current_sid = id.toString();
            System.out.println("id " + current_sid);
            if( !comparator.equals( current_sid)){
                System.out.println("will remove on from " + current_sid );
                comparator = current_sid;
                count++;
                // delete
                //coll.deleteOne(eq("student_id", current_sid));
                //coll.deleteOne()
            }
        }

        System.out.println( "List Size: " + all.size() );
        System.out.println( "counted removals: " + count );

        System.out.println("Collection Size: " + coll.count());

//        remove the grade of type "homework" with the lowest score for
//        each student from the dataset that you imported in the previous
//        homework. Since each document is one grade, it should remove one document per student.

 //       If you select homework grade-documents,
 //               sort by student and then by score,
 //       you can iterate through and find the lowest score for each student by noticing
 //       a change in student id.
 //               As you notice that change of student_id, remove the document.

        System.out.println(" End");
    }
}
