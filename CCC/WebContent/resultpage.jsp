<%@page import="com.ccc.services.Main"%>
<%@page import="com.ccc.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ccc.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Result Page</title>
<link rel="stylesheet" href="css/fileUpload.css">
<script src="js/uploadfile.js"></script>
</head>
<body>

	<%@ include file="components/header.jsp"%>
	<%
		ArrayList<CustomFile> main = (ArrayList) request.getAttribute("mainObject");
	%>
	<div class="main-container">

		<%
			for (CustomFile file : main) {
		%>
		<center>
			<h3>
				<%
					out.print(file.getFileName());
				%>
			</h3>
		</center>
		<br />
		<br />
		<table border = "1">
			<tr>
				<th>Line No</th>
				<th>Program Statement</th>
				<th>Nr</th>
				<th>Nmcms</th>
				<th>Nmcmd</th>
				<th>Nmcrms</th>
				<th>Nmcrmd</th>
				<th>Nrmcrms</th>
				<th>Nrmcrmd</th>
				<th>Nrmcms</th>
				<th>Nrmcmd</th>
				<th>Nmrgvs</th>
				<th>Nmrgvd</th>
				<th>Nrmrgvs</th>
				<th>Nrmrgvd</th>
				<th>Ccp</th>
			</tr>
			<% for(Line line : file.getLineSet()){ %>
				<tr>
				
				<td><% out.print(line.getLineNumber()); %></td>
				<td><% out.print(line.getLineContent()); %></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr>
			<% } %>
		</table>
		<%
			}
		%>
	</div>

</body>
</html>