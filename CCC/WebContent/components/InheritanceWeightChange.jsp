<%@page import="com.ccc.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.ccc.services.*"%>
<!DOCTYPE html>
<head>
<title>Change complexity values</title>
<link rel="stylesheet" href="../css/main.css">
</head>
<body>
<%@ include file="header.jsp"%>
	<div class="container">

		<div style="text-align: center">
			<h3>Weights related to the Inheritance</h3>
		</div>
		<form action="../UpdateInheritanceWeight?update=true" method="post">
			<table class="table table-striped" style="margin-top: 30px;">
				<thead>
					<tr>
						<th scope="col">Inherited Pattern</th>
						<th scope="col">Weight</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">A class with no inheritance (direct)</th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Inheritance.getWeights()[0]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (directly) from one
							user-defined class</th>
						<td><input type="number" name="c2" id="c2"
							value="<%out.print(Inheritance.getWeights()[0]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (directly) from two
							user-defined classes</th>
						<td><input type="number" name="c3" id="c3"
							value="<%out.print(Inheritance.getWeights()[0]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (directly) from three
							user-defined classes</th>
						<td><input type="number" name="c4" id="c4"
							value="<%out.print(Inheritance.getWeights()[0]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (directly) from more than
							three user-defined classes</th>
						<td><input type="number" name="c5" id="c5"
							value="<%out.print(Inheritance.getWeights()[0]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class with no inheritance (indirect)</th>
						<td><input type="number" name="c6" id="c6"
							value="<%out.print(Inheritance.getWeights()[1]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (indirectly) from one
							user-defined class</th>
						<td><input type="number" name="c7" id="c7"
							value="<%out.print(Inheritance.getWeights()[1]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (indirectly) from two
							user-defined classes</th>
						<td><input type="number" name="c8" id="c8"
							value="<%out.print(Inheritance.getWeights()[1]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (indirectly) from three
							user-defined classes</th>
						<td><input type="number" name="c9" id="c9"
							value="<%out.print(Inheritance.getWeights()[1]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (indirectly) from more
							than three user-defined classes</th>
						<td><input type="number" name="c10" id="c10"
							value="<%out.print(Inheritance.getWeights()[1]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
				</tbody>
			</table>
			<Button class="btn btn-primary" style="float: right">Save</Button>
		</form>
		<form action="../UpdateInheritanceWeight?default=true" method="post"
			style="margin-bottom: 20px;">
			<button class="btn btn-secondary">Set Default</button>
		</form>
	</div>
</body>
</html>