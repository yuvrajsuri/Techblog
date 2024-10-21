<%-- 
    Document   : login
    Created on : Sep 21, 2024, 10:24:03 PM
    Author     : vicky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <!-- navbar -->
        <%--<%@ include file="navbar.jsp"%>--%>
        
        <!--header-->
        <%@ include file="header.jsp"%>
        <main class="d-flex align-items-center" style="height: 80vh;">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 offset-md-4">
                        <div class="card">
                            <div class="card-header">
                                <h2>Login here</h2>
                            </div>
                            <div class="card-body">
                                <form>
                                    <div class="mb-3">
                                        <label for="username" class="form-label">Username</label>
                                        <input name="username" id="username" type="text" class="form-control" required>
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
                                    <!-- <div class="mb-3 form-check">
                                      <input type="checkbox" class="form-check-input" id="exampleCheck1">
                                    </div> -->
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
    </script>
</html>
