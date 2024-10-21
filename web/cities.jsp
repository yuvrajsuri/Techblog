<%@ page import="java.sql.*, java.util.*" %>
<html>
<head>
    <title>Cities in State</title>
</head>
<body>
    <h2>Cities in <%= request.getParameter("stateName") %> of <%= request.getParameter("countryName") %></h2>
    
    <%
        String countryName = request.getParameter("countryName");
        String stateName = request.getParameter("stateName");
        List<String> cities = new ArrayList<>();

        // Database connection details
        String dbUrl = "jdbc:postgresql://localhost:5432/techblog";
        String dbUser = "postgres";
        String dbPassword = "Kipl1234";  // Update with your password

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish connection
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // SQL query to fetch cities for a state in a particular country
            String sql = "SELECT c.CityNm " +
                         "FROM DmCities c " +
                         "JOIN DmCountryCircleCityInfo ccsi ON c.CityId = ccsi.CityId " +
                         "JOIN DmCountryCircleInfo cci ON ccsi.CountryCircleId = cci.CountryCircleId " +
                         "JOIN DmCircles s ON cci.CircleId = s.CirId " +
                         "JOIN DmCountries cn ON cci.CountryId = cn.CountryId " +
                         "WHERE cn.CountryName = ? AND s.CirNm = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, countryName);
            stmt.setString(2, stateName);
            rs = stmt.executeQuery();

            // Display cities
            while (rs.next()) {
                cities.add(rs.getString("CityNm"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    %>

    <ul>
        <%
            if (cities.isEmpty()) {
        %>
            <li>No cities found</li>
        <%
            } else {
                for (String city : cities) {
        %>
                    <li><%= city %></li>
        <%
                }
            }
        %>
    </ul>
</body>
</html>
