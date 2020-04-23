<%-- 
    Document   : index
    Created on : Feb 24, 2020, 10:09:11 AM
    Author     : nadeera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--<link rel="stylesheet" href="css/login.css">-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script>
            function toFileUpload(){
                window.location.replace("fileupload.jsp");
            }
        </script>
    </head>
    <body>
        <%@ include file="components/header.jsp"%>
        <button onclick="toFileUpload();">FileUpload</button>
    </body>
</html>
