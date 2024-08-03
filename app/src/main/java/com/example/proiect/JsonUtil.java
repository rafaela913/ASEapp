package com.example.proiect;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUtil {

    public static String getJsonFromResources(Context context, int resId) {
        String result = null;
        try (InputStream is = context.getResources().openRawResource(resId)) {
            result = new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .parallel()
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static List<Universitate> parseJsonContent(String jsonContent) {
        List<Universitate> result = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonContent);
            result = new ArrayList<>();
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                Universitate universitate = readJsonUniversitate(jsonObject);
                result.add(universitate);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static Universitate readJsonUniversitate(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt("id");
        String nume = jsonObject.getString("nume");
        JSONArray facultatiJsonArray = jsonObject.getJSONArray("facultati");
        List<Facultate> facultati = new ArrayList<>();
        for (int i = 0; i < facultatiJsonArray.length(); i++) {
            JSONObject facultateJson = facultatiJsonArray.getJSONObject(i);
            Facultate facultate = readJsonFacultate(facultateJson);
            facultati.add(facultate);
        }
        return new Universitate(id, nume, facultati);
    }

    private static Facultate readJsonFacultate(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt("id");
        int universitateId = jsonObject.getInt("universitateId");
        String nume = jsonObject.getString("nume");
        JSONArray departamenteJsonArray = jsonObject.getJSONArray("departamente");
        List<Departament> departamente = new ArrayList<>();
        for (int i = 0; i < departamenteJsonArray.length(); i++) {
            JSONObject departamentJson = departamenteJsonArray.getJSONObject(i);
            Departament departament = readJsonDepartament(departamentJson);
            departamente.add(departament);
        }
        return new Facultate(id, universitateId, nume, departamente);
    }

    private static Departament readJsonDepartament(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt("id");
        int facultateId = jsonObject.getInt("facultateId");
        String nume = jsonObject.getString("nume");
        return new Departament(id, facultateId, nume);
    }
}
