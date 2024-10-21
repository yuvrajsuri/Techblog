/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tech.blog.servlets;

import com.tech.blog.helper.ConnectionProvider;
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
//@WebServlet("/LocationServlet")
@MultipartConfig
public class LocationServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/techblog";
    private static final String USER = "postgres";
    private static final String PASS = "Kipl1234";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        JSONArray jsonResponse = new JSONArray();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement stmt;
            ResultSet rs;

            if ("countries".equals(type)) {
                String sql = "SELECT CountryId, CountryName FROM DmCountries WHERE Status = true";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    JSONObject country = new JSONObject();
                    country.put("CountryId", rs.getInt("CountryId"));
                    country.put("CountryName", rs.getString("CountryName"));
                    jsonResponse.put(country);
                }
            } else if ("states".equals(type) && id != null) {
                String sql = "SELECT CirId, CirNm FROM DmCircles s " +
                             "JOIN DmCountryCircleInfo cci ON s.CirId = cci.CircleId " +
                             "WHERE cci.CountryId = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    JSONObject state = new JSONObject();
                    state.put("CirId", rs.getInt("CirId"));
                    state.put("CirNm", rs.getString("CirNm"));
                    jsonResponse.put(state);
                }
            } else if ("cities".equals(type) && id != null) {
                String sql = "SELECT CityId, CityNm FROM DmCities c " +
                             "JOIN DmCountryCircleCityInfo cc ON c.CityId = cc.CityId " +
                             "WHERE cc.CountryCircleId = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    JSONObject city = new JSONObject();
                    city.put("CityId", rs.getInt("CityId"));
                    city.put("CityNm", rs.getString("CityNm"));
                    jsonResponse.put(city);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
