<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Home Page</title>
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
<body>




	<div class="container" style="height: 500px">

		<form action="RawInputServlet" class="form" method="POST"
			style="font-family: century gothic;">

			<div class="form-group" style="font-size: 20px; font-weight: normal;">
				<label for="complexity">Complexity Type:</label> <select
					class="custom-select" name="type">


					<option value="3">By Inheritance</option>

				</select>
			</div>


			<div class="form-group" style="font-size: 25px; font-weight: normal;">
				<label for="code">Paste your code here</label>
				<textarea class="form-control" rows="10" id="code" name="code"></textarea>
			</div>

			<br>

			<button type="submit" class="btn btn-primary btn-lg">Submit</button>
		</form>
	</div>


</body>
</html>