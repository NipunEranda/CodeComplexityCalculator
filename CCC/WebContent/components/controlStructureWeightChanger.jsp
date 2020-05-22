<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.ccc.model.*"%>
<%@page import="com.ccc.services.Main"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit CS Weights</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div style="margin-top: 55px;"></div>

	<div class="container">
		<br>
		<div class="card shadow">
			<div class="card-header bg-secondary text-light">Weights
				related to the control structure factor</div>
			<div class="card-body">

				<%
					ArrayList<CustomFile> fileList = (ArrayList) session.getAttribute("fileList");
					int weights[] = ControlStructure.getWeights();
					System.out.println("weights : " + ControlStructure.getWeights());

					//	session.setAttribute("fileList", fileList);
				%>

				<form class="form-inline" action="../UpdateCSWeights" method="post">

					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Control Structure Type</th>
								<th>Weight</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>A conditional control structure such as an 'if' or
									'else-if' condition</td>
								<td><input type="number" autocomplete="2"
									class="form-control" value="<%=weights[0]%>" id="weightC"
									name="weightC"></td>
							</tr>
							<tr>
								<td>An iterative control structure such as a 'for',
									'while', or 'do-while' loop</td>
								<td><input type="number" autocomplete="3"
									class="form-control" value="<%=weights[1]%>" id="weightI"
									name="weightI"></td>
							</tr>
							<tr>
								<td>The 'switch' statement in a 'switch-case' control
									structure</td>
								<td><input type="number" autocomplete="2"
									class="form-control" value="<%=weights[2]%>"
									id="weightSwitch" name="weightSwitch"></td>
							</tr>

							<tr>
								<td>Each 'case' statement in a 'switch-case' control
									structure</td>
								<td><input type="number" autocomplete="1"
									class="form-control" value="<%=weights[3]%>" id="weightCase"
									name="weightCase"></td>
							</tr>
						</tbody>
					</table>

					<button type="submit" id="btnUpdate" name="weightTYpe"
						value="update" class="btn btn-primary float-right ml-2 mr-0">
						Save</button>
					<form action="../UpdateCSWeights" method="post">
						<button type="submit" id="btnDefault" name="weightTYpe"
							value="default" class="btn btn-light float-left ml-0">
							Set Default</button>
					</form>

				</form>



			</div>

		</div>


	</div>

</body>
</html>