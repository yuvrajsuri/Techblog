///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.tech.blog.servlets;
//
///**
// *
// * @author yuvraj.suri
// */
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//public class CountryDataToDB {
//
//    public static void main(String[] args) {
//        try {
//            // Fetch country data from Rest Countries API
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://restcountries.com/v3.1/all"))
//                .build();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            JSONArray countriesArray = new JSONArray(response.body());
//
//            // Database connection
//            String dbUrl = "jdbc:postgresql://localhost:5432/techblog";
//            String dbUser = "postgres";
//            String dbPassword = "Kipl1234";
//            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//
//            // Prepare SQL insert statement
//            String sql = "INSERT INTO DmCountries (CountryName) VALUES (?)";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//
//            // Iterate over countries and insert into the database
//            for (int i = 0; i < countriesArray.length(); i++) {
//                JSONObject country = countriesArray.getJSONObject(i);
//                String countryName = country.getJSONObject("name").getString("common");
//
//                // Set the country name in the prepared statement
//                pstmt.setString(1, countryName);
//                pstmt.addBatch();
//            }
//
//            // Execute the batch insert
//            pstmt.executeBatch();
//
//            System.out.println("Countries inserted successfully.");
//            conn.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
