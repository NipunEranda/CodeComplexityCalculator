<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>File Upload</title>
<%@ include file="components/header.jsp"%>
<link rel="stylesheet" href="css/fileUpload.css">
<script src="js/uploadfile.js"></script>
<style>
#home {
	background-color: darkorange;
	color: black;
}

#home:hover {
	background-color: gold;
	color: black;
}
</style>
</head>
<body background="images/backgroundCover.jpeg"
	style=" background-size: cover;" />
<div class="main-container">
	<center>
		<div class="inner-container">
			<div class="container">
				<label>Do you want to know how much your program is complex.<br />Drop
					your program and I will calculate it for you.
				</label><br /> <br /> <label>Available Languages</label><br /> <br /> <label
					style="font-size: 12px;">&#x1F5F9 JAVA</label><br /> <label
					style="font-size: 12px;">&#x1F5F9 C++</label><br />
			</div>
			<br />
			<div class="upload">
				<form action="UploadServlet" method="post"
					enctype="multipart/form-data" name="uploadForm" id="uploadForm" onsubmit="return fileCheck();">
					<label class="browse-button">Browse <input name="file"
						type="file" id="file" multiple style="display: none;"
						onChange="uploadOnChange(this)">
					</label>
					<div class="proceed" style="margin-bottom: 50px;">
						<div style="width: 100%;">
							<input type="text" id="filename" name="filename"/><input
								type="submit" name="submit" value="Proceed" id="submitBtn"/>
						</div>
					</div>
					<div id="alertError" class="alert alert-danger"  style="width:88%;padding: 2px;" ></div>
				</form>
			</div>
		</div>
	</center>
</div>
</body>
</html>
