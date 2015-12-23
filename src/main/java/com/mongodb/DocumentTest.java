package com.mongodb;

//import static com.mongodb.m101j.utils.Helpers.printJson;
import org.bson.Document;

import java.util.Arrays;

/**
 * Created by MMAA-local on 10/25/2015.
 */
public class DocumentTest {
    public static void main(String[] args) {
        Document document = new Document()
                    .append( "str", "MongoDB, hello")
                    .append("int", 42 )
                    .append("double", 1.4)
                    .append("embeddedDoc", new Document( "x", 0))
                    .append("list", Arrays.asList(1,2,3));

        String str = document.getString("str");
        int i = document.getInteger("int");
    }
}
