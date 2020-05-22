<%@page import="com.ccc.services.*"%>
<%@page import="com.ccc.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ccc.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Result Size Page</title>
<link rel="stylesheet" href="css/filesUpload.css">
<script src="js/uploadfiles.js"></script>
</head>
<body>

	<%
		ArrayList<CustomFile> mains = (ArrayList) session.getAttribute("fileList");
	%>
	<div>

		<%
			for (CustomFile files : mains) {
				int[] sum = new int[5];
		%>
		
		<center><h1>Size</h1></center>
		<center>
			<h3>
				<%
					out.print(files.getFileName());
				%>
			</h3>
		</center>
		<br /> <br />
		
		<table border="1">
			<tr>
				<th>Line No</th>
				<th>Program Statement</th>
				<th>Nkw</th>
				<!--col1 A recursive call -->
				<th>Nid</th>
				<!--col2 A regular method calling another regular method in the same files done-->
				<th>Nop</th>
				
				<th>Nnv</th>
				<!--col3 A regular method calling another regular method in a different files done-->
				<th>Nsl</th>
				<!--col4 A regular method calling a recursive method in the same files done-->
				<th>Cs</th>
				<!--col14 total -->
			</tr>
			<%
				int count = 0;
					for (Line line : files.getLineSet()) {
			%>
			<tr>
				<%
					int col1 = 0;
					int col2 = 0;
					int col3 = 0;
					int col4 = 0;
					int col5 = 0;
					int col6 = 0;
				
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
					<%
						out.print(line.getSum()[3]);
					%>
				</td>
				
				
				
				<td>
				<% out.print(line.getFinalValue()); %>
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
			<td><% out.print(files.getSize().getSum()[0]); %></td>
			<td><% out.print(files.getSize().getSum()[1]); %></td>
			<td><% out.print(files.getSize().getSum()[2]); %></td>
			<td><% out.print(files.getSize().getSum()[3]); %></td>
			<td><% out.print(files.getSize().getSum()[4]); %></td>
			<td><% out.print(files.getSize().getFinalValue()); %></td>
			</tr>
		</table>
		<%
		
	
			}
		%>
	</div>

</body>
</html>