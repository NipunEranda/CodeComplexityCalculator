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
<link rel="stylesheet" href="css/fileUpload.css">
<script src="js/uploadfile.js"></script>
</head>
<body>

	<%@ include file="components/header.jsp"%>
	<%
		ArrayList<CustomFile> main = (ArrayList) request.getAttribute("mainObject");
	%>
	<div class="main-container">

		<%
			for (CustomFile file : main) {
		%>
		<center>
			<h3>
				<%
					out.print(file.getFileName());
				%>
			</h3>
		</center>
		<br /> <br />
		<table border="1">
			<tr>
				<th>Line No</th>
				<th>Program Statement</th>
				<th>Nr</th>
				<!-- A recursive call -->
				<th>Nmcms</th>
				<!-- A regular method calling another regular method in the same file done-->
				<th>Nmcmd</th>
				<!-- A regular method calling another regular method in a different file done-->
				<th>Nmcrms</th>
				<!-- A regular method calling a recursive method in the same file done-->
				<th>Nmcrmd</th>
				<!-- A regular method calling a recursive method in a different file done-->
				<th>Nrmcrms</th>
				<!-- A recursive method calling another recursive method in the same file done-->
				<th>Nrmcrmd</th>
				<!-- A recursive method calling another recursive method in a different file done-->
				<th>Nrmcms</th>
				<!-- A recursive method calling a regular method in the same file done-->
				<th>Nrmcmd</th>
				<!-- A recursive method calling a regular method in a different file done-->
				<th>Nmrgvs</th>
				<!-- A regular method referencing a global variable in the same file done-->
				<th>Nmrgvd</th>
				<!-- A regular method referencing a global variable in a different file done-->
				<th>Nrmrgvs</th>
				<!-- A recursive method referencing a global variable in the same file -->
				<th>Nrmrgvd</th>
				<!-- A recursive method referencing a global variable in a different file -->
				<th>Ccp</th>
				<!-- total -->
			</tr>
			<%
				int count = 0;
					for (Line line : file.getLineSet()) {
			%>
			<tr>
				<%
					int col1;
					int col2;
					int col3;
					int col4;
					int col5;
					int col6;
					int col7;
					int col8;
					int col9;
					int col10;
					int col11;
					int col12;
					int col13;
				%>
				<td>
					<%
						out.print(line.getLineNumber());
					%>
				</td>
				<td>
					<%
						out.print(line.getLineContent());
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getRecursiveMethodCalls()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getRegularInRegularMethods()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getRegularInRegularMethods_DF()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getRecursiveInRegularMethods()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getRecursiveInRegularMethods_DF()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getRecursiveInRecursiveMethods()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getRecursiveInRecursiveMethods_DF()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getRegularInRecursiveMethods()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getRegularInRecursiveMethods_DF()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getGlobalVariableListInReg()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getGlobalVariableListInReg_DF()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getGlobalVariableListInRec()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td>
					<%
						count = 0;
								for (Line regLine : file.getCoupling().getGlobalVariableListInRec_DF()) {
									if (regLine.getLineNumber() == line.getLineNumber()) {
										count++;
									}
								}
								out.print(count);
					%>
				</td>
				<td></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			}
		%>
	</div>

</body>
</html>