<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <%@ include file="components/header.jsp"%>
        <button onclick="window.location.replace('fileUpload.jsp');">FileUpload</button>
        <button onclick="window.location.replace('rawInput.jsp');">Raw Input</button>
    </body>
</html>
