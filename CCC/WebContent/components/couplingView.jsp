<%@page import="com.ccc.services.Main"%>
<%@page import="com.ccc.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coupling</title>
<style type="text/css">

table tr td{
	font-size: 12px;
}

</style>
</head>
<body>

	<%
		ArrayList<CustomFile> main = (ArrayList) session.getAttribute("fileList");
	%>
	<div>

		<%
			for (CustomFile file : main) {
				int[] sum = new int[13];
		%>
		<center>
			<h4 class="bg-primary text-light p-2 ">

				<%
					out.print(file.getFileName());
				%>
			</h4>
		</center>
		<table border="1" class="table table-fixed table-bordered table-striped" style="border-collapse: collapse;width: 100%;font-size: 14px;">
		<table border="1" style="border-collapse: collapse;width: 100%;font-size: 14px;" class="table">
			 <thead class="thead-dark">
				<th>Line No</th>
				<th>Program Statement</th>
				<th>Nr</th>
				<!--col1 A recursive call -->
				<th>Nmcms</th>
				<!--col2 A regular method calling another regular method in the same file done-->
				<th>Nmcmd</th>
				<!--col3 A regular method calling another regular method in a different file done-->
				<th>Nmcrms</th>
				<!--col4 A regular method calling a recursive method in the same file done-->
				<th>Nmcrmd</th>
				<!--col5 A regular method calling a recursive method in a different file done-->
				<th>Nrmcrms</th>
				<!--col6 A recursive method calling another recursive method in the same file done-->
				<th>Nrmcrmd</th>
				<!--col7 A recursive method calling another recursive method in a different file done-->
				<th>Nrmcms</th>
				<!--col8 A recursive method calling a regular method in the same file done-->
				<th>Nrmcmd</th>
				<!--col9 A recursive method calling a regular method in a different file done-->
				<th>Nmrgvs</th>
				<!--col10 A regular method referencing a global variable in the same file done-->
				<th>Nmrgvd</th>
				<!--col11 A regular method referencing a global variable in a different file done-->
				<th>Nrmrgvs</th>
				<!--col2 A recursive method referencing a global variable in the same file -->
				<th>Nrmrgvd</th>
				<!--col13 A recursive method referencing a global variable in a different file -->
				<th>Ccp</th>
				<!--col14 total -->
			</thead>
			<%
				int count = 0;
					for (Line line : file.getLineSet()) {
			%>
			<tr>
				<%
					int col1 = 0;
							int col2 = 0;
							int col3 = 0;
							int col4 = 0;
							int col5 = 0;
							int col6 = 0;
							int col7 = 0;
							int col8 = 0;
							int col9 = 0;
							int col10 = 0;
							int col11 = 0;
							int col12 = 0;
							int col13 = 0;
							int col14 = 0;
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
						out.print(line.getSum()[0]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[1]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[2]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[3]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[4]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[5]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[6]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[7]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[8]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[9]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[10]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[11]);
					%>
				</td>
				<td>
					<%
						out.print(line.getSum()[12]);
					%>
				</td>
				<td>
					<%
						out.print(line.getFinalValue());
					%>
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td></td>
				<td></td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[0]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[1]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[2]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[3]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[4]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[5]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[6]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[7]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[8]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[9]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[10]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[11]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getSum()[12]);
					%>
				</td>
				<td>
					<%
						out.print(file.getCoupling().getFinalValue());
					%>
				</td>
			</tr>
		</table>
		<br />
		<%
			}
		%>
	</div>
	<div>
		<br />
		<center>
			<div class="btn btn-primary"
				onclick="window.location.href = 'components/couplingWeightChanger.jsp';">Change
				Weights</div>
		</center>
	</div>
</body>
</html>