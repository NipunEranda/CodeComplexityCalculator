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
<%@ include file="components/header.jsp"%>
<link rel="stylesheet" href="css/resultPage.css">
<script src="js/resultPage.js"></script>
<style>
#analytics {
	background-color: black;
	color: white;
}

#analytics:hover {
	background-color: lightGrey;
	color: black;
}
</style>
</head>
<body>
	<div id="resultContainer" class="container-fluid">
		<div class="row" style="text-align: center;">
			<div class="col btn btn-primary" id="previous"
				onclick="getPrevious()">Previous</div>
			<div class="col btn btn-primary" id="sizeBtn" style="background-color: lightGrey;color:black">Size</div>
			<div class="col btn btn-primary" id="variableBtn">Variable</div>
			<div class="col btn btn-primary" id="methodBtn">Method</div>
			<div class="col btn btn-primary" id="couplingBtn">Coupling</div>
			<div class="col btn btn-primary" id="controlStructureBtn">Control Structures</div>
			<div class="col btn btn-primary" id="inheritanceBtn">Inheritance</div>
			<div class="col btn btn-primary" id="finalResultBtn">Final Result</div>
			<div class="col btn btn-primary" id="next" onclick="getNext()">Next
				Factor</div>
		</div>
		<br/>
		<div id="coupling" class="coupling">
			<div>
				<h3>Code Complexity due to Coupling</h3>
				<br />
			</div>
			<center>
				<%@ include file="components/couplingView.jsp"%>
			</center>
		</div>
		<div id="size" class="size">
				<h3>Code Complexity due to size</h3>
			<br />
		</div>
		<div id="variable" class="variable">
				<h3>Code Complexity due to variables</h3>
			<br />
		</div>
		<div id="method" class="method">
				<h3>Code Complexity due to methods</h3>
			<br />
		</div>
		<div id="inheritance" class="inheritance">
				<h3>Code Complexity due to Inheritance</h3>
			<br />
		</div>
		<div id="controlStructure" class="controlStructure">
				<h3>Code Complexity due to Control Structures</h3>
			<br />
		</div>
		<div id="finalResult" class="finalResult">
			<center>
				<h1>Final Result</h1>
			</center>
			<br />
		</div>
		<br /> <br />
	</div>

</body>
</html>