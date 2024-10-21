/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yuvraj.suri
 */
package com.tech.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class CountryDao {
    private Connection conn;

    public CountryDao(Connection conn) {
        this.conn = conn;
    }

    // Method to save a single country
    public boolean saveCountry(String countryName) {
        boolean success = false;
        try {
            String query = "INSERT INTO DmCountries (CountryName) VALUES (?)";
            PreparedStatement pstmt = this.conn.prepareStatement(query);
            pstmt.setString(1, countryName);
            pstmt.executeUpdate();
            pstmt.close();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // Method to batch save all countries from the API response
    public boolean saveCountriesFromJson(JSONArray countriesArray) {
        boolean success = false;
        try {
            String query = "INSERT INTO DmCountries (CountryName) VALUES (?)";
            PreparedStatement pstmt = this.conn.prepareStatement(query);

            for (int i = 0; i < countriesArray.length(); i++) {
                JSONObject country = countriesArray.getJSONObject(i);
                String countryName = country.getJSONObject("name").getString("common");

                pstmt.setString(1, countryName);
                pstmt.addBatch();
            }

            // Execute batch insert
            pstmt.executeBatch();
            pstmt.close();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}

