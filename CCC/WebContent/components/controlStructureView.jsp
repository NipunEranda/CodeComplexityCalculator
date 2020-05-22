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
</head>
<body>

<%
	ArrayList<CustomFile> maincs = (ArrayList) session.getAttribute("fileList");
%>
	<div>
	
		<center><h3>
		<%
			
			for(CustomFile filecs : maincs){
				
				out.print(filecs.getFileName());
				
				
		%>
		</h3></center><br><br>
		
		<%
		
				ArrayList wtcs =  (ArrayList)filecs.getControlStructure().getWtcsList();
				ArrayList nc =  (ArrayList)filecs.getControlStructure().getNcList();
				ArrayList ccspps =  (ArrayList)filecs.getControlStructure().getCcsppsList();
				ArrayList ccs =  (ArrayList)filecs.getControlStructure().getCsList();	
				ArrayList statements =  (ArrayList)filecs.getControlStructure().getStatement();
				int weights[] = filecs.getControlStructure().getWeights();
		
			
		%>
		
		<table border="1" style="border-collapse: collapse;" class="table">
			 <thead class="thead-dark">
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
							for(int i=0;i<filecs.getLineSet().size();i++){
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
		
			<form class="form mb-2 float-left" action="components/controlStructureWeightChanger.jsp" >
				<input type="submit" class="btn btn-secondary" value="Change Weights"> 
			</form>
		
	
	</div>

</body>
</html>