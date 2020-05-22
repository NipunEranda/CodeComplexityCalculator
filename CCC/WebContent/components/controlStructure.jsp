<%@page import="com.ccc.model.CustomFile"%>
<%@page import="com.ccc.model.Line"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Control Structure</title>

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
		ArrayList<CustomFile> maincs = (ArrayList) session.getAttribute("fileList");
	%>
	<div>

		<%
			for (CustomFile filecs : maincs) {
		%>
		<center>
			<h4 class="bg-primary text-light p-2">

				<%
					out.print(filecs.getFileName());
				%>
			</h4>
		</center>

		<%
			ArrayList wtcs = (ArrayList) filecs.getControlStructure().getWtcsList();
				ArrayList nc = (ArrayList) filecs.getControlStructure().getNcList();
				ArrayList ccspps = (ArrayList) filecs.getControlStructure().getCcsppsList();
				ArrayList ccs = (ArrayList) filecs.getControlStructure().getCsList();
				ArrayList statements = (ArrayList) filecs.getControlStructure().getStatement();
				int weights[] = filecs.getControlStructure().getWeights();
				int total = 0;
		%>

		<table class="table table-fixed table-bordered table-striped"
			style="border-collapse: collapse;">
			<thead class="thead-dark">
				<tr>
					<th>Line</th>
					<th>Program Statement</th>
					<th>Wtcs</th>
					<th>NC</th>
					<th>Ccspps</th>
					<th>Ccs</th>
				</tr>
			</thead>
			<tbody>


				<%
					for (int i = 0; i < filecs.getLineSet().size(); i++) {
							out.print("<tr><td>" + i + "</td>");
							out.print("<td class='content'>" + statements.get(i) + "</td>");
							out.print("<td>" + wtcs.get(i) + "</td>");
							out.print("<td>" + nc.get(i) + "</td>");
							out.print("<td>" + ccspps.get(i) + "</td>");
							out.print("<td>" + ccs.get(i) + "</td></tr>");
							total += Integer.parseInt(ccs.get(i).toString());
						}
				%>
				<tr>
					<td></td>
					<td>Total</td>
					<td></td>
					<td></td>
					<td></td>
					<td><%=total%></td>
				</tr>
			</tbody>
		</table>

		<%
			}
		%>


		<form class="form mb-2 float-left"
			action="components/controlStructureEditWeights.jsp">
			<center>
				<input type="submit" class="btn btn-primary" value="Change Weights">
			</center>
		</form>

	</div>

</body>
</html>