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
<link rel="stylesheet" href="css/fileUpload.css">
<script src="js/uploadfile.js"></script>
</head>
<body>
	<%
		ArrayList<CustomFile> mainv = (ArrayList) session.getAttribute("fileList");
	%>
	<div>

		<%
			for (CustomFile file : mainv) {
				int[] sum = new int[3];
		%>
		<center>
			<h3>
				<%
					out.print(file.getFileName());
				%>
			</h3>
		</center>
		<br /> <br />
		<table border="1" style="border-collapse: collapse;" class="table">
			 <thead class="thead-dark">
				<th>Line No</th>
				<th>Program Statement</th>
				<th>Wvs</th>
				<!--col1 A recursive call -->
				<th>Npdtv</th>
				<!--col2 A regular method calling another regular method in the same file done-->
				<th>Nedtv</th>
				<!--col3 A regular method calling another regular method in a different file done-->
				<!--col4 A regular method calling a recursive method in the same file done-->
				<th>Cs</th>
				<!--col14 total -->
			</thead>
			<%
				int count = 0;
					for (Line line : file.getLineSet()) {
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
				<td>
					<%
						out.print(line.getLineContent());
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[0]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[1]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[2]);
					%>
				</td>
				
				<td>
				<% out.print(line.getFinalValue()); %>
				</td>
			</tr>
			<%
				}
			%>
			<tr>
			<td></td>
			<td></td>
			<td><% out.print(file.getCoupling().getSum()[0]); %></td>
			<td><% out.print(file.getCoupling().getSum()[1]); %></td>
			<td><% out.print(file.getCoupling().getSum()[2]); %></td>
		
			<td><% out.print(file.getCoupling().getFinalValue()); %></td>
			</tr>
		</table>
		<%
		
	
			}
		%>
	</div>

</body>
</html>