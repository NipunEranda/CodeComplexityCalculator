<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.ccc.services.Main"%>
<%@page import="com.ccc.model.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coupling Weight Changer</title>
<link rel="stylesheet" href="../css/main.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<%
		ArrayList<CustomFile> fileList = (ArrayList) session.getAttribute("fileList");
	%>

	<div class="container" style="margin-top: 25px;">

		<div style="text-align: center">
			<h3>Weights related to the coupling factor</h3>
		</div>
		<form action="../UpdateCouplingWeight?update=true" method="post">
			<table class="table table-striped" style="margin-top: 30px;border-style: solid;border-width: 2px;">
				<thead>
					<tr>
						<th scope="col">Coupling Type</th>
						<th scope="col">Weight</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">A recursive call</th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Coupling.getWeights()[0]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling another regular
							method in the same file</th>
						<td><input type="number" name="c2" id="c2"
							value="<%out.print(Coupling.getWeights()[1]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling another regular
							method in a different file</th>
						<td><input type="number" name="c3" id="c3"
							value="<%out.print(Coupling.getWeights()[2]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling a recursive method
							in the same file</th>
						<td><input type="number" name="c4" id="c4"
							value="<%out.print(Coupling.getWeights()[3]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling a recursive method
							in a different file</th>
						<td><input type="number" name="c5" id="c5"
							value="<%out.print(Coupling.getWeights()[4]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling another recursive
							method in the same file</th>
						<td><input type="number" name="c6" id="c6"
							value="<%out.print(Coupling.getWeights()[5]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling another recursive
							method in a different file</th>
						<td><input type="number" name="c7" id="c7"
							value="<%out.print(Coupling.getWeights()[6]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling a regular method
							in the same file</th>
						<td><input type="number" name="c8" id="c8"
							value="<%out.print(Coupling.getWeights()[7]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling a regular method
							in a different file</th>
						<td><input type="number" name="c9" id="c9"
							value="<%out.print(Coupling.getWeights()[8]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A regular method referencing a global
							variable in the same file</th>
						<td><input type="number" name="c10" id="c10"
							value="<%out.print(Coupling.getWeights()[9]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A regular method referencing a global
							variable in a different file</th>
						<td><input type="number" name="c11" id="c11"
							value="<%out.print(Coupling.getWeights()[10]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A recursive method referencing a global
							variable in the same file</th>
						<td><input type="number" name="c12" id="c12"
							value="<%out.print(Coupling.getWeights()[11]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A recursive method referencing a global
							variable in a different file</th>
						<td><input type="number" name="c13" id="c13"
							value="<%out.print(Coupling.getWeights()[12]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
				</tbody>
			</table>
			<Button class="btn btn-primary" style="float: right">Save</Button>
		</form>
					<Button class="btn btn-danger" style="float: right; margin-right: 4px;" onclick="window.location.replace('../resultpage.jsp');">Back</Button>
		<form action="../UpdateCouplingWeight?default=true" method="post"
			style="margin-bottom: 20px;">
			<button class="btn btn-secondary">Set Default</button>
		</form>
	</div>
</body>
</html>