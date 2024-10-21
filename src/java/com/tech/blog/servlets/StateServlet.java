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
public class StateServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/techblog";
    private static final String USER = "postgres";
    private static final String PASS = "Kipl1234";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String countryId = request.getParameter("countryId");
        JSONArray stateList = new JSONArray();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT CirId, CirNm FROM DmCircles s " +
                         "JOIN DmCountryCircleInfo cci ON s.CirId = cci.CircleId " +
                         "WHERE cci.CountryId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, countryId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                JSONObject state = new JSONObject();
                state.put("CirId", rs.getInt("CirId"));
                state.put("CirNm", rs.getString("CirNm"));
                stateList.put(state);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.getWriter().write(stateList.toString());
    }
}