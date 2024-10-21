<%-- 
    Document   : register
    Created on : Sep 22, 2024, 12:27:58 AM
    Author     : vicky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--header-->
        <%@ include file="header.jsp"%>
        
        <main class="d-flex align-items-center p-5" style="background-color: gray;">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <div class="card">
                            <div class="card-header">
                                <h2>Register here</h2>
                            </div>
                            <div class="card-body">
                                <form action="RegisterServlet" method="POST" id="myform">
                                    <div class="mb-3">
                                        <label for="username" class="form-label">Username</label><br>
                                        <input name="username" 
                                        id="username"
                                        type="text" 
                                        class="form-control"
                                        required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label">Email</label><br>
                                        <input name="email" 
                                        id="email"
                                        type="email" 
                                        class="form-control"
                                        required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="password" class="form-label">Password</label>
                                        <div class="input-group">
                                            <input name="password" id="password" type="password" class="form-control" required>
                                            <div class="input-group-append">
                                                <span class="input-group-text">
                                                    <input type="checkbox" id="togglePassword">
                                                    <label for="togglePassword">&nbsp;&nbsp;Show password</label>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="gender" class="form-label">Select Gender</label><br>
                                        <input type="radio" id="gender" name="gender" value="male">Male
                                        <input type="radio" id="gender" name="gender" value="Female">Female
                                    </div>
                                    <div class="form-group">
                                        <textarea name="about" class="form-control" rows="3" placeholder="Enter something about  yourself"></textarea>
                                    </div>
                                    <div class="mb-3 form-check">
                                      <input name="check" type="checkbox" class="form-check-input" id="exampleCheck1">
                                      <label class="form-check-label" for="exampleCheck1">agree terms and conditions</label>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                  </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        
    </body>
    <script>
        document.getElementById('togglePassword').addEventListener('change', function (e) {
            const passwordInput = document.getElementById('password');
            if (e.target.checked) {
                passwordInput.type = 'text';
            } else {
                passwordInput.type = 'password';
            }
        });
        
        $(document).ready(function(){
            console.log("loaded");

            $("#myform").on('submit',function(e){
                e.preventDefault();

                let formData = new FormData(this);
                $.ajax({
                    type: "POST",
                    url:"RegisterServlet",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(data,textStatus,jqXHR){
                        console.log(data);
                        if(data.trim() == "done"){
                            alert("Registration Successfull");
                            window.location.href = "login.jsp";
                        }else{
                            alert("Registration Failed: "+data);
                        }
                    },
                    error: function(jqXHR,textStatus,errorThrown){
                        console.log(jqXHR);
                        alert("Registeration Failed");
                    }
                });
            })
        })
    </script>
</html>
