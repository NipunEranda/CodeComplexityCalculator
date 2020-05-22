<html>
<head>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
	<div class="topic" style="width: 98.9%;">
		<div class="row">
			<div class="col" style="background-color: rgba(0, 0, 0, 0.7);"></div>
			<div class="col" style="border-right: none; padding-left: 0px;background-color: rgba(0, 0, 0, 0.7);">
				<img class="logo"
					src="${pageContext.request.contextPath}/images/logo.jpg" />
			</div>
			<div class="col-4" style="border-left: none;background-color: rgba(0, 0, 0, 0.7);">
				<div class="maintopic">
					<b>Code Complexity Calculator</b>
				</div>
				<div class="sub-topic">POWERED BY SLIIT</div>
			</div>
			<div class="col"
				style="border-style: solid; border-color: black; border-width: 0.2px;background-color: rgba(0, 0, 0, 0.7);"></div>
		</div>
	</div>

	<div class="nav_bar" style="width: 98.9%;">
		<div class="row" style="text-align: center;">
			<div class="col" id="home"
				onclick="window.location.href = '${pageContext.request.contextPath}/index.jsp';">Home</div>
			<div class="col" id="analytics">ANALYTICS</div>
			<div class="col" id="guide">GUIDE</div>
			<div class="col" id="about">ABOUT</div>
		</div>
	</div>
</body>
</html>
