<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>File Upload</title>
<link rel="stylesheet" href="css/fileUpload.css">
<script src="js/uploadfile.js"></script>
</head>
<body>
	<%@ include file="components/header.jsp"%>
	<div class="main-container">
		<center>
			<div class="inner-container">
				<div class="container">
					<label>Do you want to know how much your program is
						complex.<br />Drop your program and I will calculate it for you.
					</label><br /> <br /> <label>Available Languages</label><br /> <br /> <label
						style="font-size: 12px;">&#x1F5F9 JAVA</label><br /> <label
						style="font-size: 12px;">&#x1F5F9 C++</label><br />
				</div>
				<br />
				<div class="upload">
					<form action="UploadServlet" method="post" enctype="multipart/form-data" name="form1" id="form1">
						<label>Browse <input name="file" type="file" id="file" multiple
							style="display: none;" onChange="uploadOnChange(this)">
						</label>
						<div class="browse-button" style="margin-bottom: 50px;">
							<input type="text" id="filename" /><input type="submit" name="submit"
								value="Proceed" />
						</div>
					</form>
				</div>
			</div>
		</center>
	</div>
</body>
</html>
