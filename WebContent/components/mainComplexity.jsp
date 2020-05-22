<%@page import="com.ccc.model.CustomFile"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	ArrayList<CustomFile> main = (ArrayList) session.getAttribute("fileList");
		
%>

<div class="main-container">
	
		<center><h3 class="bg-light">
		<%
			
			for(CustomFile file : main){
				
				out.print(file.getFileName());
				
				
		%>
		</h3></center><br><br>
		
		<%
		
/* 				ArrayList Cs =  (ArrayList)file.getControlStructure().getWtcsList();
				ArrayList Cv =  (ArrayList)file.getControlStructure().getNcList();
				ArrayList Cm =  (ArrayList)file.getControlStructure().getCcsppsList();
				ArrayList Ccp =  (ArrayList)file.getControlStructure().getCsList();	
				ArrayList Ci =  (ArrayList)file.getControlStructure().getStatement();
				ArrayList Ccs =  (ArrayList)file.getControlStructure().getCsList();	
				ArrayList Tcps =  (ArrayList)file.getControlStructure().getStatement(); */
		
			}
		%>


			<table class=" table-fixed table table-bordered" >
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
						
<%-- 						<%
							if(program != null){
								for(int i=0; i<program.size();i++){
									out.print("<tr><td>"+i+"</td>");
									out.print("<td>"+ program.get(i) +"</td>");
									out.print("<td></td>");
									out.print("<td></td>");
									out.print("<td></td>");
									out.print("<td></td>");
									out.print("<td></td>");
									out.print("<td></td>");
									out.print("<td></td></tr>");
									
								}
							}
						
						%> --%>
		
					</tbody>
			</table>
			
			
	</div>

</body>
</html>