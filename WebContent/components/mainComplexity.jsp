<%@page import="com.ccc.model.Line"%>
<%@page import="com.ccc.model.CustomFile"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main Complexity</title>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" 
crossorigin="anonymous">

<%--	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	--%>

</head>
<body>

<% 
	ArrayList<CustomFile> programList = (ArrayList) session.getAttribute("fileList");
		
%>

<div class="main-container">
	
		<%	
			for(CustomFile file : programList){
		%>
		
		<br>
		<center><h4 class="bg-primary text-light p-2">
		<%	
				out.print(file.getFileName());
		%>
		</h4></center>
		
		<%

				ArrayList Ccs =  (ArrayList)file.getControlStructure().getCsList();	
		
				int totCs = 0;
				int totCv = 0;
				int totCm = 0;
				int totCcp = 0;
				int totCi = 0;
				int totCcs = 0;
				
		%>


			<table class=" table-fixed table table-bordered table-striped" >
					<thead>
						<tr>
							<th>Line</th>
							<th>Program Statement</th>
							<th>Cs</th>
							<th>Cv</th>
							<th>Cm</th>
							<th>Ccp</th>
							<th>Ci</th>
							<th>Ccs</th>
							<th>TCps</th>
						</tr>
					</thead>
					<tbody>
					
					
						<%
							int count = 0;
							for(Line line : file.getLineSet()){
								
								out.print("<tr><td>"+line.getLineNumber()+"</td>");
								out.print("<td>"+line.getLineContent()+"</td>");
								out.print("<td></td>");
								out.print("<td></td>");
								out.print("<td></td>");
								out.print("<td></td>");
								out.print("<td></td>");
								out.print("<td>"+Ccs.get(line.getLineNumber()-1)+"</td>");
								out.print("<td></td></tr>");
								
								
								
								totCcs += Integer.parseInt(Ccs.get(line.getLineNumber()-1).toString());
								
							}			
						%>
					
						<tr class="font-weight-bold"><td></td><td>Total</td><td></td><td></td><td></td><td></td><td></td><td><%= totCcs %></td><td></td></tr>
						
		
					</tbody>
			</table>
			
			<%
				}
			%>
		
			
	</div>

</body>
</html>