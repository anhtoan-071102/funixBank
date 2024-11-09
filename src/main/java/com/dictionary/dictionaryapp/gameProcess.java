/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dictionary.dictionaryapp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class gameProcess {

    private static String B1URL = "C:/Users/ADMIN/DictionaryApp/src/resources/com/dictionary/resources/data/game_data/B1.jsonl";
    private static String B2URL = "C:/Users/ADMIN/DictionaryApp/src/resources/com/dictionary/resources/data/game_data/B2.jsonl";
    private static String C1URL = "C:/Users/ADMIN/DictionaryApp/src/resources/com/dictionary/resources/data/game_data/C1.jsonl";
    private static String C2URL = "C:/Users/ADMIN/DictionaryApp/src/resources/com/dictionary/resources/data/game_data/C2.jsonl";

    private final static int NUM_B1 = 28;
    private final static int NUM_B2 = 58;
    private final static int NUM_C1 = 25;
    private final static int NUM_C2 = 9;
    
    private static ArrayList<JsonObject> B1;
    private static ArrayList<JsonObject> B2;
    private static ArrayList<JsonObject> C1;
    private static ArrayList<JsonObject> C2;
    
    private static ArrayList<String> paRamSB1;
    private static ArrayList<String> paRamSB2;
    private static ArrayList<String> paRamSC1;
    private static ArrayList<String> paRamSC2;
    
    private static ArrayList<ArrayList<JsonObject>> QuAandAswB1;
    private static ArrayList<ArrayList<JsonObject>> QuAandAswB2;
    private static ArrayList<ArrayList<JsonObject>> QuAandAswC1;
    private static ArrayList<ArrayList<JsonObject>> QuAandAswC2;
   
    /**
     * contructor
     * @param b1
     * @param b2
     * @param c1
     * @param c2 
     */
    public gameProcess(String b1, String b2, String c1, String c2) {
        B1 = partJson(b1);
        B2 = partJson(b2);
        C1 = partJson(c1);
        C2 = partJson(c2);
        
        paRamSB1 = getParams(B1);
        paRamSB2 = getParams(B2);
        paRamSC1 = getParams(C1);
        paRamSC2 = getParams(C2);
        
        QuAandAswB1 = getQuaAndAswer(B1);
        QuAandAswB2 = getQuaAndAswer(B2);
        QuAandAswC1 = getQuaAndAswer(C1);
        QuAandAswC2 = getQuaAndAswer(C2);
    }
    
    /**
     * get list jsonObject quesAndAswer
     * @return 
     */
    public static ArrayList<ArrayList<JsonObject>> getQuAandAswB1() {    
        return QuAandAswB1;
    }

    public static ArrayList<ArrayList<JsonObject>> getQuAandAswB2() {
        return QuAandAswB2;
    }

    public static ArrayList<ArrayList<JsonObject>> getQuAandAswC1() {
        return QuAandAswC1;
    }

    public static ArrayList<ArrayList<JsonObject>> getQuAandAswC2() {
        return QuAandAswC2;
    }

    
    /**
     * get number of question
     * @return 
     */
    public static int getNUM_B1() {
        return NUM_B1;
    }

    public static int getNUM_B2() {
        return NUM_B2;
    }

    public static int getNUM_C1() {
        return NUM_C1;
    }

    public static int getNUM_C2() {
        return NUM_C2;
    }

    /**
     * get parameter
     * @return 
     */
    public static ArrayList<String> getPaRamSB1() {
        return paRamSB1;
    }

    public static ArrayList<String> getPaRamSB2() {
        return paRamSB2;
    }

    public static ArrayList<String> getPaRamSC1() {
        return paRamSC1;
    }

    public static ArrayList<String> getPaRamSC2() {
        return paRamSC2;
    }
    
    /**
     * part json from string in java
     * @param level
     * @return 
     */
    private ArrayList<JsonObject> partJson(String level) {
        ArrayList<JsonObject> arrJson = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/ADMIN/DictionaryApp/src/resources/com/dictionary/resources/data/game_data/" + level + ".jsonl"))) {
            String line;
            while ((line = br.readLine()) != null) {
                 JsonObject jsonElement = JsonParser.parseString(line).getAsJsonObject();
                 arrJson.add(jsonElement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrJson;
    }
    
    /**
     * get context of parameter from json object
     * @param levelType
     * @return 
     */
    private ArrayList<String> getParams(ArrayList<JsonObject> levelType) {
        ArrayList<String> params = new ArrayList<>();
        for (int i = 0; i < levelType.size(); i++) {
            String context = levelType.get(i).get("text").getAsString();
            String title = levelType.get(i).get("title").getAsString();
            String param = title + "\n" + context;
            params.add(param);
        }
        return params;
    }
    
    /**
     * get QuesTion and Answer from json object
     * @param levelType
     * @return 
     */
    private ArrayList<ArrayList<JsonObject>> getQuaAndAswer(ArrayList<JsonObject> levelType) {
        ArrayList<ArrayList<JsonObject>> QuA = new ArrayList<>();
        for (int i = 0; i < levelType.size(); i++) {
            JsonObject quess = levelType.get(i).get("questions").getAsJsonObject();
            ArrayList<JsonObject> QuJson = new ArrayList<>();
            for (String key : quess.keySet()) {
                JsonObject quessAndAns = quess.get(key).getAsJsonObject();
                QuJson.add(quessAndAns);
            }
            QuA.add(QuJson);
        }
        return QuA;
    }
}
