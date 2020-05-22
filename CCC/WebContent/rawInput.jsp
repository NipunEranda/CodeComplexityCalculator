<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="components/header.jsp"%>
<meta charset="ISO-8859-1">
<title>Raw Input</title>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #00376c;
	font-family: century gothic;
	font-size: 25px;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li

 

a


:hover


:not

 

(
.active

 

)
{
background-color


:

 

#ffd700


;
}
.active {
	background-color: #ffd700;
}
</style>
</head>
<body background="images/backgroundCover.jpeg"
	style=" background-size: cover;" />

	<div class="container" style="height: 500px; margin-top: 25px;background-color: rgba(0, 0, 0, 0.7);color: white; padding: 30px;margin-bottom: 30px;">

		<form action="RawInputServlet" class="form" method="POST"
			style="font-family: century gothic;">

			<div class="form-group" style="font-size: 20px; font-weight: normal;">
				<label for="complexity">Language type:</label> <select
					class="custom-select" name="type">
					<option value="0">Select one</option>
					<option value="1">Java</option>
					<option value="2">C++</option>
				</select>
			</div>


			<div class="form-group" style="font-size: 20px; font-weight: normal;">
				<label for="code">Paste your code here</label>
				<textarea class="form-control" rows="10" id="code" name="code"></textarea>
			</div>

			<button type="submit" class="btn btn-lg" style="background-color: darkorange">Submit</button>
		</form>
	</div>


</body>
</html>