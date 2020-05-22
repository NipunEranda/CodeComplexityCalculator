<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Home Page</title>
<link rel="stylesheet" href="css/fileUpload.css">
<script src="js/uploadfile.js"></script>
<style>
.upload {
	background-color: lightgrey;
}

.upload:hover {
	color: white;
	background-color: darkorange;
	transition-duration: 0.3s;
}

.raw {
	background-color: lightgrey;
}

.raw:hover {
	color: white;
	background-color: darkorange;
	transition-duration: 0.3s;
}
</style>
</head>
<body background="images/backgroundCover.jpeg"
	style="background-size: cover;" />
<%@ include file="components/header.jsp"%>
<div class="main-container">
	<center>
		<div class="inner-container">
			<div class="container">

				<label>Welcome to the Code Comlexity Calculator </label>

			</div>
			<br />
			<div>
				<button class="btn upload" style="width: 40%;"
					onclick="window.location.replace('fileUpload.jsp');">FileUpload</button>
				<button class="btn raw" style="width: 40%;"
					onclick="window.location.replace('rawInput.jsp');">Raw
					Input</button>

			</div>

		</div>
	</center>
</div>
</body>
</html>
