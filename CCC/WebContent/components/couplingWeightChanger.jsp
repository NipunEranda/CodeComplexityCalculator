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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>

	<%
		ArrayList<CustomFile> fileList = (ArrayList) session.getAttribute("fileList");
	%>

	<div class="container">

		<div style="text-align: center">
			<h3>Weights related to the coupling factor</h3>
		</div>
		<form action="../UpdateCouplingWeight?update=true" method="post">
			<table class="table table-striped" style="margin-top: 30px;">
				<thead>
					<tr>
						<th scope="col">Coupling Type</th>
						<th scope="col">Weight</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">A recursive call</th>
						<td><input type="number" name="c1" id="c1" value="<% out.print(Coupling.getWeights()[0]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling another regular
							method in the same file</th>
						<td><input type="number" name="c2" id="c2" value="<% out.print(Coupling.getWeights()[1]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling another regular
							method in a different file</th>
						<td><input type="number" name="c3" id="c3" value="<% out.print(Coupling.getWeights()[2]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling a recursive method
							in the same file</th>
						<td><input type="number" name="c4" id="c4" value="<% out.print(Coupling.getWeights()[3]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling a recursive method
							in a different file</th>
						<td><input type="number" name="c5" id="c5" value="<% out.print(Coupling.getWeights()[4]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling another recursive
							method in the same file</th>
						<td><input type="number" name="c6" id="c6" value="<% out.print(Coupling.getWeights()[5]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling another recursive
							method in a different file</th>
						<td><input type="number" name="c7" id="c7" value="<% out.print(Coupling.getWeights()[6]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling a regular method
							in the same file</th>
						<td><input type="number" name="c8" id="c8" value="<% out.print(Coupling.getWeights()[7]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling a regular method
							in a different file</th>
						<td><input type="number" name="c9" id="c9" value="<% out.print(Coupling.getWeights()[8]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method referencing a global
							variable in the same file</th>
						<td><input type="number" name="c10" id="c10" value="<% out.print(Coupling.getWeights()[9]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method referencing a global
							variable in a different file</th>
						<td><input type="number" name="c11" id="c11" value="<% out.print(Coupling.getWeights()[10]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method referencing a global
							variable in the same file</th>
						<td><input type="number" name="c12" id="c12" value="<% out.print(Coupling.getWeights()[11]); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method referencing a global
							variable in a different file</th>
						<td><input type="number" name="c13" id="c13" value="<% out.print(Coupling.getWeights()[12]); %>"/></td>
					</tr>
				</tbody>
			</table>
			<Button class="btn btn-primary" style="float: right">Save</Button>
		</form>
		<form action="../UpdateCouplingWeight?default=true" method="post" style="margin-bottom: 20px;">
		<button class="btn btn-secondary">Set Default</button>
		</form>
	</div>
</body>
</html>