<%@page import="com.ccc.services.*"%>
<%@page import="com.ccc.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ccc.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Result Variables Page</title>
<link rel="stylesheet" href="css/filemUpload.css">
<style>
table tr td{
	font-size: 13px;
	text-align: center;
}

table tr .content{
	text-align: left;
}
</style>
<script src="js/uploadfilem.js"></script>
</head>
<body>
	<%
		ArrayList<CustomFile> mainm = (ArrayList) session.getAttribute("fileList");
	%>
	<div>

		<%
			for (CustomFile filem : mainm) {
				int[] sum = new int[13];
		%>
		<center>
			<h4 class="bg-primary text-light p-2 ">

				<%
					out.print(filem.getFileName());
				%>
			</h4>
		</center>
		<table border="1" class="table table-fixed table-bordered table-striped" style="border-collapse: collapse;width: 100%;font-size: 13.5px;">
			 <thead class="thead-dark">
				<th>Line No</th>
				<th>Program Statement</th>
				<th>Wmrt</th>
				<!--col1 A recursive call -->
				<th>Npdtv</th>
				<!--col2 A regular method calling another regular method in the same filem done-->
				<th>Nedtv</th>
				<!--col3 A regular method calling another regular method in a different filem done-->
				<!--col4 A regular method calling a recursive method in the same filem done-->
				<th>CM</th>
				<!--col14 total -->
			</thead>
			<%
				int count = 0;
					for (Line line : filem.getLineSet()) {
			%>
			<tr>
				<%
					int col1 = 0;
					int col2 = 0;
					int col3 = 0;
					int col4 = 0;
				
				%>
				<td>
					<%
						out.print(line.getLineNumber());
					%>
				</td>
				<td class="content">
					<%
						out.print(line.getLineContent());
					%>
				</td>
				<td>
					<%
						out.print(line.getMethodsum()[0]);
					%>
				</td>
				<td>
					<%
						out.print(line.getMethodsum()[1]);
					%>
				</td>
				<td>
					<%
						out.print(line.getMethodsum()[2]);
					%>
				</td>
						
				
				
				<td>
				<% out.print(line.getMethodFinalValue()); %>
				</td>
			</tr>
			<%
				}
			%>
			<tr>
			<td></td>
			<td></td>
			<td><% out.print(filem.getMethod().getSum()[0]); %></td>
			<td><% out.print(filem.getMethod().getSum()[1]); %></td>
			<td><% out.print(filem.getMethod().getSum()[2]); %></td>
			<td><% out.print(filem.getMethod().getFinalValue()); %></td>
			</tr>
		</table>
		<%
		
	
			}
		%>
	</div>

</body>
</html>
