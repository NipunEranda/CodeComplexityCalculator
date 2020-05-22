<%@page import=" com.ccc.model.*"%>
<%@page import=" com.ccc.services.*"%>
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
		ArrayList<CustomFile> mainI = (ArrayList) session.getAttribute("fileList");
	%>
	<div>

		<%
			for (CustomFile fileI : mainI) {
				int[] sum = new int[4];
		%>
		<center>
			<h3>
				<%
					out.print(fileI.getFileName());
				%>
			</h3>
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
					for (Line line : fileI.getLineSet()) {
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
						out.print(fileI.getInheritance().getSum()[0]);
					%>
				</td>
				<td>
					<%
						out.print(fileI.getInheritance().getSum()[1]);
					%>
				</td>
				<td>
					<%
						out.print(fileI.getInheritance().getSum()[2]);
					%>
				</td>
				
				
				<td>
					<%
						out.print(fileI.getInheritance().getFinalValue());
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
				onclick="window.location.href = 'components/InheritanceWeightChanger.jsp';">Change
				Weights</div>
		</center>
	</div>
</body>
</html>