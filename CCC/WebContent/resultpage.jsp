<%@page import="com.ccc.services.Main"%>
<%@page import="com.ccc.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ccc.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Result Page</title>
<link rel="stylesheet" href="css/resultPage.css">
<script src="js/resultPage.js"></script>
</head>
<body>
	<%@ include file="components/header.jsp"%>
	<div id="resultContainer">
		<div id="coupling" class="coupling">
			<center>
				<h2>Coupling factor</h2>
				<br />
				<%@ include file="components/couplingView.jsp"%>
		</div>
		<div id="size" class="size">
			<center>
				<h1>Size factor</h1>
			</center>
			<br />
		</div>
		<div id="variable" class="variable">
			<center>
				<h1>Variable factor</h1>
			</center>
			<br />
		</div>
		<div id="method" class="method">
			<center>
				<h1>Method factor</h1>
			</center>
			<br />
		</div>
		<div id="inheritance" class="inheritance">
			<center>
				<h1>Inheritance factor</h1>
			</center>
			<br />
		</div>
		<div id="controlStructure" class="controlStructure">
			<center>
				<h1>Control Structure factor</h1>
			</center>
			<br />
		</div>
		<br />
		<Button id="previous" style="float: left;" onclick="getPrevious()">Previous</Button>
		<Button id="next" style="float: right;" onclick="getNext()">Next</Button>
		<br />
	</div>

</body>
</html>