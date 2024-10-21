<%--<%@page import="java.sql.Connection"%>--%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import = "java.sql.*"%>

<!DOCTYPE html>
<html>
    <head>
        <title>TechBlog</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <!--Bootstrap CSS--> 
<!--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">-->

        <link rel="stylesheet" href="/web/css/style.css">
    </head>
    <body>
<!--    
        <% 
        Connection conn = ConnectionProvider.getConnection();
        %>
        <h1><%=conn%></h1>
        <button class="btn btn-danger">hello</button>-->
         
        
        <!-- navbar -->
         <%--<%@ include file="navbar.jsp"%>--%>
         
         <!--header-->
         <%@ include file="header.jsp"%>
         
         <!-- banner -->
          <div class="container-fluid p-0 m-0">
                <div class="bg-dark p-5 rounded-lg text-white">
                    <h3 class="display-3">Welcome to TechBlog</h3>
                    
                    <p>Welcome to Technical blog,  here you can learn about programming, web development, and many more.</p>
                    <button class="btn btn-outline-light btn-lg">Start! its Free</button>
                    <a href="login.jsp" class="btn btn-outline-light btn-lg">Login</a>
                </div>
          </div><br>
          
          <button id="fetch">fetch</button>
          <h2>Select Location</h2>
    <label for="country">Country:</label>
    <select id="country">
        <option value="">Select Country</option>
    </select>

    <label for="state">State:</label>
    <select id="state" disabled>
        <option value="">Select State</option>
    </select>

    <label for="city">City:</label>
    <select id="city" disabled>
        <option value="">Select City</option>
    </select>

          
           <div id="result"></div>

          
        <!-- cards -->
         <div class="container">
            <div class="row mb-2">
                <div class="col-md-4">
                    <div class="card ">
                        <div class="card-body">
                            <h5 class="card-title">Java Programming</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="#" class="btn btn-primary">Read More</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card ">
                        <div class="card-body">
                            <h5 class="card-title">Java Programming</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="#" class="btn btn-primary">Read More</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card ">
                        <div class="card-body">
                            <h5 class="card-title">Java Programming</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="#" class="btn btn-primary">Read More</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <div class="card ">
                        <div class="card-body">
                            <h5 class="card-title">Java Programming</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="#" class="btn btn-primary">Read More</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card ">
                        <div class="card-body">
                            <h5 class="card-title">Java Programming</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="#" class="btn btn-primary">Read More</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card ">
                        <div class="card-body">
                            <h5 class="card-title">Java Programming</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="#" class="btn btn-primary">Read More</a>
                        </div>
                    </div>
                </div>
         </div>

<!--         Bootstrap JS and Popper.js 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.6/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

         jquery
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>-->
        
        <!--js-->
        <script src="js/script.js" type="text/javascript"></script>
        <script>
//            $('#fetch').on('click',function(){
//                console.log("working");
//                
//            })
// $('#fetch').on('click', function() {
//            console.log("Button clicked, sending AJAX request");
//
//            // Send AJAX request to the servlet
//            $.ajax({
//                url: 'CountryServlet', // The URL of your servlet
//                type: 'GET', // HTTP method
//                success: function(response) {
//                    console.log(response)
//                    // On success, show the response message
//                    $('#result').html(response);
//                },
//                error: function(xhr, status, error) {
//                    // Handle error
//                    console.error("Error: " + error);
//                    $('#result').html("Failed to fetch data: " + error);
//                }
//            });
//        });
$(document).ready(function() {
            // Load countries on page load
            console.log("loaded")
            $.ajax({
    url: 'CountryServlet',
    type: 'GET',
    success: function(data) {
        var countries = data; // No need to parse if data is already a JSON object
countries.forEach(function(country) {
    console.log(country);
    $('#country').append(`<option value="${country.CountryId}">${country.CountryName}</option>`);
});

    },
    error: function(xhr, status, error) {
        console.error("AJAX Error: " + status + ": " + error);
    }
});

            // Load states when a country is selected
            $('#country').change(function() {
                var countryId = $(this).val();
                $('#state').prop('disabled', !countryId);
                $('#state').empty().append('<option value="">Select State</option>');

                if (countryId) {
                    $.ajax({
                        url: 'StateServlet',
                        type: 'GET',
                        data: { countryId: countryId },
                        success: function(data) {
                            var states = JSON.parse(data);
                            states.forEach(function(state) {
                                $('#state').append(`<option value="${state.CirId}">${state.CirNm}</option>`);
                            });
                        }
                    });
                }
            });

            // Load cities when a state is selected
            $('#state').change(function() {
                var stateId = $(this).val();
                $('#city').prop('disabled', !stateId);
                $('#city').empty().append('<option value="">Select City</option>');

                if (stateId) {
                    $.ajax({
                        url: 'CityServlet',
                        type: 'GET',
                        data: { stateId: stateId },
                        success: function(data) {
                            var cities = JSON.parse(data);
                            cities.forEach(function(city) {
                                $('#city').append(`<option value="${city.CityId}">${city.CityNm}</option>`);
                            });
                        }
                    });
                }
            });
        });

        </script>
    </body>
</html>


<!--<!DOCTYPE html>
<html>
<head>
    <title>My Web Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     Bootstrap CSS 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
     Main Content 
    <div class="container mt-4">
        <button class="btn btn-danger">Submit</button>
    </div>

     Bootstrap JS and Popper.js 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.6/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>-->
