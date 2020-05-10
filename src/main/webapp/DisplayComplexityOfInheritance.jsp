<%@page import="Model.StatementLine"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Complexity of Inheritance </title>

	<style>
			ul {
				list-style-type: none;
				margin: 0;
				padding: 0;
				overflow: hidden;
				background-color: #00376c;
				font-family: century gothic;
				font-size: 25px;
			}
			
			li {
				float: left;
			}
			
			li a {
				display: block;
				color: white;
				text-align: center;
				padding: 14px 16px;
				text-decoration: none;
			}
			
			li a:hover:not (.active ) {
				background-color: #ffd700;
			}
			
			.active {
				background-color: #ffd700;
			}
</style>
	
</head>
		<body>
		
		
		<div class="container" style="overflow: auto;min-height: 500px"> 
		
			<div align="center">
	
				
				<h1 class="display-1"
					style="center: 2%; margin-left: 100px; font-family: century gothic; font-size: 50px;margin-top: 10px">
					<font color="#00376c">Complexity of Inheritance</font>
				</h1>
				<br> 
	
			</div>
			
			 <table class="table" style=" font-family: century gothic" cellpadding="20px" cellspacing="20px" align="center" border="2"  >
             <thead>
              <tr>
                    <th style="font=weight:bold;">Line Number</th>
                    <th style="font=weight:bold;">Statement</th>
                    <th style="font=weight:bold;">Direct</th>
                    <th style="font=weight:bold;">Indirect</th>
                    <th style="font=weight:bold;">Total</th>
                    <th style="font=weight:bold;">Ci</th>
              </tr>
              </thead>
               
		
		<%
		
		int x = 1;
		int Com = 0;
		
		String[] codeLines = (String[])session.getAttribute("codeLines");
		ArrayList<StatementLine> StatementListCi = (ArrayList<StatementLine>)session.getAttribute("Ci");
		ArrayList<StatementLine> StatementListCii = (ArrayList<StatementLine>)session.getAttribute("Cii");
		
		
		
		
	     while(x<= codeLines.length){
	    	 StatementLine ci = StatementListCi.get(x-1) ;
	    	 StatementLine cii = StatementListCii.get(x-1) ;
	    	
	    	
	    	 
		%>
		
		<tr>
			<td><%=x %></td>
			<td><%=codeLines[x-1].replace(" ", "&nbsp;") %></td>
			<td><%=ci.getComplexity()%></td>
			<td><%=cii.getComplexity() %></td>
			<td><%=ci.getComplexity() + cii.getComplexity() %></td>
			<td><%=Com %></td>
		</tr>
		
		<%
		
			x++;
		
	     }
		%>
		 </table>    
		</div>
		
		
		
		</body>
</html>