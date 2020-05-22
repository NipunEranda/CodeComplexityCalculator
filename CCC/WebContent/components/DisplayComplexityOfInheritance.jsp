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
<style type="text/css">

table tr td{
	font-size: 13px;
	text-align: center;
}

table tr .content{
	text-align: left;
}

</style>
</head>
<body>

	<%
		ArrayList<CustomFile> mainI = (ArrayList) session.getAttribute("fileList");
	%>
	<div class="mainI-container">

		<%
			for (CustomFile fileI : mainI) {
				int[] sum = new int[5];
		%>
		<center>
			<h4 class="bg-primary text-light p-2 ">

				<%
					out.print(fileI.getFileName());
				%>
			</h4>
		</center>
		<table border="1" class="table table-fixed table-bordered table-striped" style="border-collapse: collapse;width: 100%;font-size: 14px;">
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
				<td class="content">
					<%
						out.print(line.getLineContent());
					%>
				</td>
				<td>
					<%
						out.print(line.getInheritancesum()[0]);
					%>
				</td>
				<td>
					<%
						out.print(line.getInheritancesum()[1]);
					%>
				</td>
				
				<td>
					<%
						out.print(line.getInheritancesum()[2]);
					%>
				</td>
				<td>
					<%
						out.print(line.getInheritanceFinalValue());
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
		<center>
			<div class="btn btn-primary"
				onclick="window.location.href = 'components/InheritanceWeightChange.jsp';">Change
				Weights</div>
		</center>
	</div>
</body>
</html>