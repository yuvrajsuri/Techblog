////package com.tech.blog.servlets;
////
////import java.io.IOException;
////import java.io.PrintWriter;
////import javax.servlet.ServletException;
////import javax.servlet.annotation.MultipartConfig;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////
////import com.tech.blog.helper.ConnectionProvider;
////import org.json.JSONArray;
////
////@MultipartConfig
////public class CountryServlet extends HttpServlet {
////
////    /**
////     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
////     *
////     * @param request servlet request
////     * @param response servlet response
////     * @throws ServletException if a servlet-specific error occurs
////     * @throws IOException if an I/O error occurs
////     */
////    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        response.setContentType("text/plain;charset=UTF-8");
////        try (PrintWriter out = response.getWriter()) {
////            try {
////                // Call the CountryDataFetcher to fetch and insert country data
////                CountryDataFetcher.main(null);
////                
////                // Send success message
////                out.println("Countries inserted successfully.");
////            } catch (Exception e) {
////                e.printStackTrace();
////                out.println("Failed to insert countries.");
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////
////    /**
////     * Handles the HTTP <code>GET</code> method.
////     *
////     * @param request servlet request
////     * @param response servlet response
////     * @throws ServletException if a servlet-specific error occurs
////     * @throws IOException if an I/O error occurs
////     */
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
////
////    /**
////     * Handles the HTTP <code>POST</code> method.
////     *
////     * @param request servlet request
////     * @param response servlet response
////     * @throws ServletException if a servlet-specific error occurs
////     * @throws IOException if an I/O error occurs
////     */
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
////
////    /**
////     * Returns a short description of the servlet.
////     *
////     * @return a String containing servlet description
////     */
////    @Override
////    public String getServletInfo() {
////        return "Country Servlet for inserting countries into the database";
////    }
////}
//
////////////////////////////////////////////////////////////////////////////////////////
//package com.tech.blog.servlets;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.json.JSONArray;
//
//@MultipartConfig
//public class CountryServlet extends HttpServlet {
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("application/json;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            try {
//                // Read JSON data from the file
//                String jsonData = new String(Files.readAllBytes(Paths.get(getServletContext().getRealPath("/countries+states+cities.json"))));
//                
//                // Parse the JSON data into a JSONArray
//                JSONArray countries = new JSONArray(jsonData);
//                
//                // Send JSON response back to the client
//                out.println(countries.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//                out.println("{\"error\":\"Failed to fetch countries.\"}");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Country Servlet for fetching country data from JSON file";
//    }
//}
//
////////////////////////////////////////////////////////////////////////////////////////
// package com.tech.blog.servlets;

// import java.io.IOException;
// import java.io.PrintWriter;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import javax.servlet.ServletException;
// import javax.servlet.annotation.MultipartConfig;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import org.json.JSONArray;
// import org.json.JSONObject;

// @MultipartConfig
// public class CountryServlet extends HttpServlet {

//     private static final String DB_URL = "jdbc:postgresql://localhost:5432/techblog";
//     private static final String USER = "postgres";
//     private static final String PASS = "Kipl1234"; // Replace with your password

//     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         response.setContentType("text/plain;charset=UTF-8");
//         try (PrintWriter out = response.getWriter()) {
//             // Read JSON data from the file
//             String jsonData = new String(Files.readAllBytes(Paths.get(getServletContext().getRealPath("/countries+states+cities.json"))));
//             JSONArray countries = new JSONArray(jsonData);

//             try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
//                 conn.setAutoCommit(false); // Disable auto-commit

//                 for (int i = 0; i < countries.length(); i++) {
//                     JSONObject country = countries.getJSONObject(i);
//                     String countryName = country.getString("name");

//                     // Insert Country
//                     String insertCountryQuery = "INSERT INTO dmcountries (CountryName) VALUES (?) RETURNING CountryId";
//                     try (PreparedStatement countryStmt = conn.prepareStatement(insertCountryQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
//                         countryStmt.setString(1, countryName);
//                         int affectedRows = countryStmt.executeUpdate();
//                         long countryId = -1;

//                         if (affectedRows > 0) {
//                             try (ResultSet countryRs = countryStmt.getGeneratedKeys()) {
//                                 if (countryRs.next()) {
//                                     countryId = countryRs.getLong(1);
//                                 }
//                             }
//                         }

//                         // Insert States
//                         JSONArray states = country.getJSONArray("states");
//                         for (int j = 0; j < states.length(); j++) {
//                             JSONObject state = states.getJSONObject(j);
//                             String stateName = state.getString("name");

//                             // Insert State
//                             String insertStateQuery = "INSERT INTO dmcircles (CirNm) VALUES (?) RETURNING CirId";
//                             try (PreparedStatement stateStmt = conn.prepareStatement(insertStateQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
//                                 stateStmt.setString(1, stateName);
//                                 affectedRows = stateStmt.executeUpdate();
//                                 long stateId = -1;

//                                 if (affectedRows > 0) {
//                                     try (ResultSet stateRs = stateStmt.getGeneratedKeys()) {
//                                         if (stateRs.next()) {
//                                             stateId = stateRs.getLong(1);
//                                         }
//                                     }

//                                     // Insert Cities
//                                     JSONArray cities = state.getJSONArray("cities");
//                                     for (int k = 0; k < cities.length(); k++) {
//                                         JSONObject city = cities.getJSONObject(k);
//                                         String cityName = city.getString("name");

//                                         // Insert City
//                                         String insertCityQuery = "INSERT INTO dmcities (CityNm) VALUES (?)";
//                                         try (PreparedStatement cityStmt = conn.prepareStatement(insertCityQuery)) {
//                                             cityStmt.setString(1, cityName);
//                                             cityStmt.executeUpdate();
//                                         }
//                                     }
//                                 }
//                             }
//                         }
//                     }
//                 }

//                 conn.commit(); // Commit all changes if successful
//                 out.println("Countries, states, and cities inserted successfully.");
//             } catch (SQLException e) {
//                 e.printStackTrace();
//                 out.println("Failed to insert countries, states, and cities: " + e.getMessage());
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     protected void doGet(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         processRequest(request, response);
//     }

//     protected void doPost(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         processRequest(request, response);
//     }

//     public String getServletInfo() {
//         return "Country Servlet for inserting countries, states, and cities into the database";
//     }
// }
////////////////////////////////////////////////////////////////////////////////////////
package com.tech.blog.servlets;

import com.tech.blog.helper.ConnectionProvider;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@MultipartConfig
public class CountryServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:postgresql://150.230.238.136:5432/mdm_mdms_java_justeat_v1";
    private static final String USER = "dblink_user";
    private static final String PASS = "DB!koch*T3chno@789"; // Replace with your password

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Read JSON data from the file
            String jsonData = new String(Files.readAllBytes(Paths.get(getServletContext().getRealPath("/countries+states+cities.json"))));
            JSONArray countries = new JSONArray(jsonData);

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                conn.setAutoCommit(false); // Disable auto-commit for transactional safety

                for (int i = 0; i < countries.length(); i++) {
                    JSONObject country = countries.getJSONObject(i);
                    String countryName = country.getString("name");

                    // Insert Country
                    String insertCountryQuery = "INSERT INTO dmcountries_v2 (CountryName) VALUES (?) RETURNING CountryId";
                    try (PreparedStatement countryStmt = conn.prepareStatement(insertCountryQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                        countryStmt.setString(1, countryName);
                        countryStmt.executeUpdate();

                        // Get the generated CountryId
                        long countryId = -1;
                        try (ResultSet countryRs = countryStmt.getGeneratedKeys()) {
                            if (countryRs.next()) {
                                countryId = countryRs.getLong(1);
                            }
                        }

                        // Insert States and map them to the country
                        JSONArray states = country.getJSONArray("states");
                        for (int j = 0; j < states.length(); j++) {
                            JSONObject state = states.getJSONObject(j);
                            String stateName = state.getString("name");

                            // Insert into DmCircles
                            String insertStateQuery = "INSERT INTO dmcircles_v2 (CirNm) VALUES (?) RETURNING CirId";
                            try (PreparedStatement stateStmt = conn.prepareStatement(insertStateQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                                stateStmt.setString(1, stateName);
                                stateStmt.executeUpdate();

                                // Get the generated CirId (state ID)
                                long circleId = -1;
                                try (ResultSet stateRs = stateStmt.getGeneratedKeys()) {
                                    if (stateRs.next()) {
                                        circleId = stateRs.getLong(1);
                                    }
                                }

                                // Map Country to Circle in DmCountryCircleInfo
                                String insertCountryCircleQuery = "INSERT INTO DmCountryCircleInfo_v2 (CountryId, CircleId) VALUES (?, ?)";
                                try (PreparedStatement countryCircleStmt = conn.prepareStatement(insertCountryCircleQuery)) {
                                    countryCircleStmt.setLong(1, countryId);
                                    countryCircleStmt.setLong(2, circleId);
                                    countryCircleStmt.executeUpdate();
                                }

                                // Insert Cities and map them to the state (circle)
                                JSONArray cities = state.getJSONArray("cities");
                                for (int k = 0; k < cities.length(); k++) {
                                    JSONObject city = cities.getJSONObject(k);
                                    String cityName = city.getString("name");

                                    // Insert City into DmCities
                                    String insertCityQuery = "INSERT INTO dmcities_v2 (CityNm) VALUES (?) RETURNING CityId";
                                    try (PreparedStatement cityStmt = conn.prepareStatement(insertCityQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                                        cityStmt.setString(1, cityName);
                                        cityStmt.executeUpdate();

                                        // Get the generated CityId
                                        long cityId = -1;
                                        try (ResultSet cityRs = cityStmt.getGeneratedKeys()) {
                                            if (cityRs.next()) {
                                                cityId = cityRs.getLong(1);
                                            }
                                        }

                                        // Map Circle (state) to City in CountryCircleCityInfo
                                        String insertCircleCityQuery = "INSERT INTO DmCountryCircleCityInfo_v2 (CountryCircleId, CityId) VALUES (?, ?)";
                                        try (PreparedStatement circleCityStmt = conn.prepareStatement(insertCircleCityQuery)) {
                                            // Retrieve the CountryCircleId for this country-state pair
                                            String selectCountryCircleIdQuery = "SELECT CountryCircleId FROM DmCountryCircleInfo_v2 WHERE CountryId = ? AND CircleId = ?";
                                            try (PreparedStatement selectStmt = conn.prepareStatement(selectCountryCircleIdQuery)) {
                                                selectStmt.setLong(1, countryId);
                                                selectStmt.setLong(2, circleId);
                                                try (ResultSet rs = selectStmt.executeQuery()) {
                                                    if (rs.next()) {
                                                        long countryCircleId = rs.getLong(1);
                                                        circleCityStmt.setLong(1, countryCircleId);
                                                        circleCityStmt.setLong(2, cityId);
                                                        circleCityStmt.executeUpdate();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                conn.commit(); // Commit all changes if successful
                out.println("Countries, states, and cities inserted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
//                conn.rollback(); // Rollback on error
                out.println("Failed to insert countries, states, and cities: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Country Servlet for inserting countries, states, and cities into the database";
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
//package com.tech.blog.servlets;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//
///**
// *
// * @author 8129K
// */
//@MultipartConfig
//public class CountryServlet extends HttpServlet {
//    private static final String DB_URL = "jdbc:postgresql://localhost:5432/techblog";
//    private static final String USER = "postgres";
//    private static final String PASS = "Kipl1234";
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        JSONArray countryList = new JSONArray();
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
//            String sql = "SELECT CountryId, CountryName FROM DmCountries WHERE Status = true";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//
//           while (rs.next()) {
//    JSONObject country = new JSONObject();
//    country.put("CountryId", rs.getInt("CountryId"));
//    country.put("CountryName", rs.getString("CountryName"));
//    countryList.put(country);
//}
//System.out.println("Country List: " + countryList.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        response.setContentType("application/json");
//response.setCharacterEncoding("UTF-8");
//response.getWriter().write(countryList.toString());  // Send the JSON array
//
//System.out.println("Country List: " + countryList.toString());
//
//
//    }
//}