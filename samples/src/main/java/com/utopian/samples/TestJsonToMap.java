package com.utopian.samples;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.time.Instant;
import java.util.HashMap;

/**
 * @author Ganesh Ramasubramanian
 */
public class TestJsonToMap {
    public static void main(String[] args) {

        String jsonString = "{\"username\":\"garamasu\",\"firstname\":\"Ganesh Kumar\",\"lastname\":\"Ramasubramanian\",\"timestamp\": " + Instant.now().toEpochMilli() + " }";

        HashMap<String, String> map =
                new Gson().fromJson(jsonString, new TypeToken<HashMap<String, String>>() {
                }.getType());

        System.out.println(map);
    }
}
