<%-- 
    Document   : Error
    Created on : 7 Oct, 2024, 1:20:05 PM
    Author     : yuvraj.suri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sorry! something went wrong</title>
    </head>
    <body>
        <%@ include file="header.jsp"%>
        <div class="container-fluid text-center p-5 mt-4">
            <i class="fa-solid fa-circle-exclamation fa-4x mb-2"></i>
            <h3>Sorry! something went wrong......</h3>
            <a href="index.jsp"><button class="btn btn-primary btn-lg mt-4">HOME</button></a>
        </div>
    </body>
</html>
