package com.example.training.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetToJson {
    public static final JsonArray ResultSetToJsonArray(ResultSet rs) {
        JsonObject element = null;
        JsonArray ja = new JsonArray();
        ResultSetMetaData rsmd = null;
        String columnName, columnValue = null;
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                element = new JsonObject();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i + 1);
                    columnValue = rs.getString(columnName);
                    element.addProperty(columnName, columnValue);
                }
                ja.add(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ja;
    }

    public static final JsonObject ResultSetToJsonObject(ResultSet rs, String name) {
        JsonObject element = null;
        JsonArray ja = new JsonArray();
        JsonObject jo = new JsonObject();
        ResultSetMetaData rsmd = null;
        String columnName, columnValue = null;
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                element = new JsonObject();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i + 1);
                    columnValue = rs.getString(columnName);
                    element.addProperty(columnName, columnValue);
                }
                ja.add(element);
            }
            jo.add(name, ja);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jo;
    }


    public static final String ResultSetToJsonString(ResultSet rs, String name) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        JsonArray ja = new JsonArray();
        ResultSetMetaData rsmd = null;
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                JsonObject element = new JsonObject(); // 在每次迴圈開始時創建一個新的 JsonObject
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    String columnName = rsmd.getColumnName(i + 1);
                    String columnValue = rs.getString(columnName);
                    element.addProperty(columnName, columnValue);
                }
                ja.add(element); // 將這一行的 JsonObject 添加到 JsonArray 中
            }
            jo.add(name, ja); // 將 JsonArray 添加到最終的 JsonObject 中
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gson.toJson(jo); // 使用 Gson 將 JsonObject 轉換為 JSON 字串並返回
    }
}