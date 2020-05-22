<%@page import="com.sun.javafx.scene.traversal.WeightedClosestCorner"%>
<%@page import="com.ccc.model.Line"%>
<%@page import="com.ccc.model.CustomFile"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Control Structure</title>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" 
crossorigin="anonymous">

</head>
<body>

<%
	ArrayList<CustomFile> main = (ArrayList) session.getAttribute("fileList");
%>
	<div class="main-container">
	
		<center><h3>
		<%
			
			for(CustomFile file : main){
				
				out.print(file.getFileName());
				
				
		%>
		</h3></center><br><br>
		
		<%
		
				ArrayList wtcs =  (ArrayList)file.getControlStructure().getWtcsList();
				ArrayList nc =  (ArrayList)file.getControlStructure().getNcList();
				ArrayList ccspps =  (ArrayList)file.getControlStructure().getCcsppsList();
				ArrayList ccs =  (ArrayList)file.getControlStructure().getCsList();	
				ArrayList statements =  (ArrayList)file.getControlStructure().getStatement();
				int weights[] = file.getControlStructure().getWeights();
		
			
		%>
		
		<table class="table table-fixed table-bordered table-striped" style="border-collapse: collapse;">
					<thead>
						<tr>
							<th>Line</th>
							<th>Program Statement</th>
							<th>Wtcs</th>
							<th>NC</th>
							<th>Ccspps</th>
							<th>Ccs</th>
						</tr>
					</thead>
					<tbody>
					
					 
						<%
							for(int i=0;i<file.getLineSet().size();i++){
								out.print("<tr><td>"+i+"</td>");
								out.print("<td>"+statements.get(i)+"</td>");
								out.print("<td>"+wtcs.get(i)+"</td>");
								out.print("<td>"+nc.get(i)+"</td>");
								out.print("<td>"+ccspps.get(i)+"</td>");
								out.print("<td>"+ccs.get(i)+"</td></tr>");
							}
						}
						%>
		
					</tbody>
			</table>
		
			<form class="form mb-2 float-left" action="components/controlStructureEditWeights.jsp" >
				<input type="submit" class="btn btn-secondary" value="Change Weights"> 
			</form>
		
	
	</div>

</body>
</html>