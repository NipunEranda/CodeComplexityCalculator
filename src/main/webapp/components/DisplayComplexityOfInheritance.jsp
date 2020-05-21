<%@page import=" Model.*"%>
<%@page import=" Service.inheritance_complexity.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Inheritance</title>
</head>
<body>

	<%
		ArrayList<CustomFile> main = (ArrayList) session.getAttribute("fileList");
	%>
	<div class="main-container">

		<%
			for (CustomFile file : main) {
				int[] sum = new int[4];
		%>
		<center>
			<p>
				<%
					out.print("inheritance complexity of ");
				%>
				<b>
				<%
					out.print(file.getFileName());
				%>
				</b>
			</p>
		</center>
		<br /> <br />
		<table border="1" style="border-collapse: collapse;" class="table">
			 <thead class="thead-dark">
			<tr>
				<th scope="col">Line No</th>
				<th scope="col">Program Statement</th>
				<th scope="col">Direct</th>
				<th scope="col">InDirect</th>
				<th scope="col">Total</th>
				<th scope="col">Ci</th>
			</tr>
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
							int col5 = 0;
							int col6 = 0;
							
				%>
				<td>
					<%
						out.print(line.getLineNumber());
					%>
				</td>
				<td class="lineSet">
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
						out.print(line.getFinalValue());
					%>
				</td>
				
			</tr>
			<%
				}
			%>
			<tr>
				<td> -- </td>
				<td>-- total --</td>
				<td>
					<%
						out.print(file.getInheritance().getSum()[0]);
					%>
				</td>
				<td>
					<%
						out.print(file.getInheritance().getSum()[1]);
					%>
				</td>
				<td>
					<%
						out.print(file.getInheritance().getSum()[2]);
					%>
				</td>
				
				
				<td>
					<%
						out.print(file.getInheritance().getFinalValue());
					%>
				</td>
			</tr>
		</table>
		<br />
		<%
			}
		%>
	</div>
	<div>
		<br />
		<center>
			<div class="btn btn-primary"
				onclick="window.location.href = 'InheritanceWeightChange.jsp';">Change
				Weights</div>
		</center>
	</div>
</body>
</html>