<%@page import=" Model.*"%>
<%@page import=" Service.inheritance_complexity.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
			<h3>
				<%
					out.print(file.getFileName());
				%>
			</h3>
		</center>
		<br /> <br />
		<table border="1" style="border-collapse: collapse;">
			<tr>
				<th>Line No</th>
				<th>Program Statement</th>
				<th>Direct</th>
				<th>InDirect</th>
				<th>Total</th>
				<th>Ci</th>
			</tr>
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
				<td></td>
				<td></td>
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
				onclick="window.location.href = 'InheritanceWeightChanger.jsp';">Change
				Weights</div>
		</center>
	</div>
</body>
</html>