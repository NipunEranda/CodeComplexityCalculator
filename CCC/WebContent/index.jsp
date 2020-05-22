<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Home Page</title>
<link rel="stylesheet" href="css/fileUpload.css">
<script src="js/uploadfile.js"></script>

</head>
<body>
	<%@ include file="components/header.jsp"%>
	<div class="main-container">
		<center>
			<div class="inner-container">
				<div class="container">
				
					<label>Welcome to the Code Comlexity Calculator
					</label>
					
				</div>
				<br />
				<div>
        <button onclick="window.location.replace('fileUpload.jsp');">FileUpload</button>
        <button onclick="window.location.replace('rawInput.jsp');">Raw Input</button>
					
				</div>
				
			</div>
		</center>
	</div>
</body>
</html>