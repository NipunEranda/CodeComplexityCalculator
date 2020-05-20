<%@page import="Model.Inheritance"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Model.StatementLine"%>
    <%@page import="Service.inheritance_complexity.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Change complexity values </title>
</head>
<body>
<div class="container">

		<div style="text-align: center">
			<h3>Weights related to the Inheritance </h3>
		</div>
		<form action="../UpdateCouplingWeight?update=true" method="post">
			<table class="table table-striped" style="margin-top: 30px;">
				<thead>
					<tr>
						<th scope="col">Inherited Pattern</th>
						<th scope="col">Weight</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">A class with no inheritance (direct) </th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Inheritance.getWeights()[0]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (directly) from one user-defined class </th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Inheritance.getWeights()[0]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (directly) from two user-defined classes </th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Inheritance.getWeights()[0]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (directly) from three user-defined classes </th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Inheritance.getWeights()[0]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (directly) from more than three user-defined classes</th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Inheritance.getWeights()[0]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class with no inheritance (indirect) </th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Inheritance.getWeights()[1]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (indirectly) from one user-defined class </th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Inheritance.getWeights()[1]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (indirectly) from two user-defined classes </th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Inheritance.getWeights()[1]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (indirectly) from three user-defined classes </th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Inheritance.getWeights()[1]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
					<tr>
						<th scope="row">A class inheriting (indirectly) from more than three user-defined classes</th>
						<td><input type="number" name="c1" id="c1"
							value="<%out.print(Inheritance.getWeights()[1]);%>"
							style="float: right; text-align: center;" /></td>
					</tr>
				</tbody>
			</table>
			<Button class="btn btn-primary" style="float: right">Save</Button>
		</form>
		<form action="../UpdateInheritanceWeight?default=true" method="post" style="margin-bottom: 20px;">
		<button class="btn btn-secondary">Set Default</button>
		</form>
	</div>
</body>
</html>