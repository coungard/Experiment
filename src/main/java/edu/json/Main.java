package edu.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("data.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray orgs = (JSONArray) obj;
            System.out.println(orgs);

            //Iterate over employee array
            orgs.forEach(emp -> parseOrgs((JSONObject) emp));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseOrgs(JSONObject org) {
        JSONObject orgObject = (JSONObject) org.get("org");

        //Get employee first name
        String name = (String) orgObject.get("name");
        System.out.println(name);

        //Get employee last name
        String address = (String) orgObject.get("address");
        System.out.println(address);

        //Get employee website name
        String inn = (String) orgObject.get("inn");
        System.out.println(inn);
    }
}
