/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.tech.blog.servlets;
//
///**
// *
// * @author yuvraj.suri
// */
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import java.sql.Connection;
//import com.tech.blog.helper.ConnectionProvider;
//import com.tech.blog.dao.CountryDao;
//
//public class CountryDataFetcher {
//
//    public static void main(String[] args) {
//        try {
//            // Create a URL object for the REST Countries API
//            URL url = new URL("https://restcountries.com/v3.1/all");
//
//            // Open the connection
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Accept", "application/json");
//
//            // Check if the request was successful
//            int responseCode = connection.getResponseCode();
//            if (responseCode == 200) {
//                // Read the response
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//
//                in.close();
//
//                // Parse the JSON response
//                JSONArray countriesArray = new JSONArray(response.toString());
//
//                // Get database connection
//                Connection conn = ConnectionProvider.getConnection();
//
//                // Create CountryDao object and pass the connection
//                CountryDao countryDao = new CountryDao(conn);
//
//                // Call method to insert countries into the database
//                boolean result = countryDao.saveCountriesFromJson(countriesArray);
//
//                // Check if the operation was successful
//                if (result) {
//                    System.out.println("Countries inserted successfully.");
//                } else {
//                    System.out.println("Failed to insert countries.");
//                }
//
//                // Close the connection
//                conn.close();
//
//            } else {
//                System.out.println("Failed to fetch data. Response code: " + responseCode);
//            }
//
//            // Disconnect the connection
//            connection.disconnect();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

