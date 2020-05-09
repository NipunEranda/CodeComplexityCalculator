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
		<form action="../UpdateCouplingWeight" method="post">
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
						<td><input type="number" name="Wr" id="Wr" value="<% out.print(fileList.get(0).getCoupling().getWr()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling another regular
							method in the same file</th>
						<td><input type="number" name="Wmcms" id="Wmcms" value="<% out.print(fileList.get(0).getCoupling().getWmcms()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling another regular
							method in a different file</th>
						<td><input type="number" name="Wmcmd" id="Wmcmd" value="<% out.print(fileList.get(0).getCoupling().getWmcmd()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling a recursive method
							in the same file</th>
						<td><input type="number" name="Wmcrms" id="Wmcrms" value="<% out.print(fileList.get(0).getCoupling().getWmcrms()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method calling a recursive method
							in a different file</th>
						<td><input type="number" name="Wmcrmd" id="Wmcrmd" value="<% out.print(fileList.get(0).getCoupling().getWmcrmd()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling another recursive
							method in the same file</th>
						<td><input type="number" name="Wrmcrms" id="Wrmcrms" value="<% out.print(fileList.get(0).getCoupling().getWrmcrms()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling another recursive
							method in a different file</th>
						<td><input type="number" name="Wrmcrmd" id="Wrmcrmd" value="<% out.print(fileList.get(0).getCoupling().getWrmcrmd()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling a regular method
							in the same file</th>
						<td><input type="number" name="Wrmcms" id="Wrmcms" value="<% out.print(fileList.get(0).getCoupling().getWrmcms()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method calling a regular method
							in a different file</th>
						<td><input type="number" name="Wrmcmd" id="Wrmcmd" value="<% out.print(fileList.get(0).getCoupling().getWrmcmd()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method referencing a global
							variable in the same file</th>
						<td><input type="number" name="Wmrgvs" id="Wmrgvs" value="<% out.print(fileList.get(0).getCoupling().getWmrgvs()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A regular method referencing a global
							variable in a different file</th>
						<td><input type="number" name="Wmrgvd" id="Wmrgvd" value="<% out.print(fileList.get(0).getCoupling().getWmrgvd()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method referencing a global
							variable in the same file</th>
						<td><input type="number" name="Wrmrgvs" id="Wrmrgvs" value="<% out.print(fileList.get(0).getCoupling().getWrmrgvs()); %>"/></td>
					</tr>
					<tr>
						<th scope="row">A recursive method referencing a global
							variable in a different file</th>
						<td><input type="number" name="Wrmrgvd" id="Wrmrgvd" value="<% out.print(fileList.get(0).getCoupling().getWrmrgvd()); %>"/></td>
					</tr>
				</tbody>
			</table>
			<Button class="btn btn-primary" style="float: right">Save</Button>
		</form>
	</div>
</body>
</html>