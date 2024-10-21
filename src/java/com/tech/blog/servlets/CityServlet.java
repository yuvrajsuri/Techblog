/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tech.blog.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author 8129K
 */
public class CityServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/techblog";
    private static final String USER = "postgres";
    private static final String PASS = "Kipl1234";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stateId = request.getParameter("stateId");
        JSONArray cityList = new JSONArray();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT CityId, CityNm FROM DmCities c " +
                         "JOIN DmCountryCircleCityInfo cc ON c.CityId = cc.CityId " +
                         "WHERE cc.CountryCircleId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, stateId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                JSONObject city = new JSONObject();
                city.put("CityId", rs.getInt("CityId"));
                city.put("CityNm", rs.getString("CityNm"));
                cityList.put(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.getWriter().write(cityList.toString());
    }
}