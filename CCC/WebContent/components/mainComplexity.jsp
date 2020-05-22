<%@page import="com.ccc.model.Line"%>
<%@page import="com.ccc.model.CustomFile"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main Complexity</title>

<%--	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	--%>
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
		ArrayList<CustomFile> programList = (ArrayList) session.getAttribute("fileList");
	%>

	<div>

		<%
			for (CustomFile file : programList) {
		%>

		<br>
		<center>
			<h4 class="bg-primary text-light p-2">
				<%
					out.print(file.getFileName());
				%>
			</h4>
		</center>

		<%
			ArrayList Ccs = (ArrayList) file.getControlStructure().getCsList();

				int totCs = 0;
				int totCv = 0;
				int totCm = 0;
				int totCcp = 0;
				int totCi = 0;
				int totCcs = 0;
		%>


		<table border="1"
			class="table table-fixed table-bordered table-striped"
			style="border-collapse: collapse; width: 100%; font-size: 14px;">
			<thead class="thead-dark">
				<tr>
					<th>Line</th>
					<th>Program Statement</th>
					<th>Cs</th>
					<th>Cv</th>
					<th>Cm</th>
					<th>Ccp</th>
					<th>Ci</th>
					<th>Ccs</th>
					<th>TCps</th>
				</tr>
			</thead>
			<tbody>

				<%
					int count = 0;
						for (Line line : file.getLineSet()) {

							out.print("<tr><td>" + line.getLineNumber() + "</td>");
							out.print("<td class='content'>" + line.getLineContent() + "</td>");
							out.print("<td></td>");
							out.print("<td></td>");
							out.print("<td>" + line.getMethodFinalValue() + "</td>");
							out.print("<td>" + line.getCouplingFinalValue() + "</td>");
							out.print("<td>" + line.getInheritanceFinalValue() + "</td>");
							out.print("<td>" + Ccs.get(line.getLineNumber() - 1) + "</td>");
							out.print("<td>" + (line.getCouplingFinalValue() + line.getFinalValue() + Integer.parseInt(Ccs.get(line.getLineNumber()-1).toString())) + "</td></tr>");

							totCcs += Integer.parseInt(Ccs.get(line.getLineNumber() - 1).toString());

						}
				%>

				<tr class="font-weight-bold">
					<td></td>
					<td>Total</td>
					<td></td>
					<td></td>
					<td><%= file.getMethod().getFinalValue() %></td>
					<td><%= file.getCoupling().getFinalValue() %></td>
					<td><%= file.getInheritance().getFinalValue() %></td>
					<td><%=totCcs%></td>
					<td><% out.print(file.getMethod().getFinalValue() + file.getCoupling().getFinalValue() + file.getInheritance().getFinalValue() + totCcs);%></td>
				</tr>


			</tbody>
		</table>

		<%
			}
		%>


	</div>

</body>
</html>