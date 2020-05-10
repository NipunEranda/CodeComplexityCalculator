package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.inheritance_complexity.CalculateIInheritanceComplexity;
import Service.inheritance_complexity.CalculateInheritanceComplexity;

import Model.StatementLine;


public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public HomeServlet() {
        super();
        
		
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String code = request.getParameter("code");
		int type =  Integer.parseInt(request.getParameter("type"));
		
		PrintWriter out= response.getWriter();

	 	HttpSession session = request.getSession();
	 	int a; 
	 	
	 	String[] sizeCode = code.split("\\r?\\n");
	 	String[] tempStArray = code.split("\\r?\\n"); 
	 	// split on new lines
	 	String[] stArray;
		session.setAttribute("codeLines", code.split("\\r?\\n"));
		int x = tempStArray.length;
		int y;
		
	 	
		System.out.println("..Start..");
	 	for(y=0;y<x;y++) {
			 
			 out.println("Line " + (y+1) + "  : " + tempStArray[y]);
			 
		 }
	 	
	 	// removes comments in the code
	 	for(a=0;a<sizeCode.length;a++) {
	 		
	 		if(sizeCode[a].contains("//")) {
	 			sizeCode[a] = "";
	 		}
	 	}
	 	// removes print lines in the codes
	 	for(a=0;a<tempStArray.length;a++) {
	 		if(tempStArray[a].contains("System.out")) {
	 			tempStArray[a] = "Printing Statement";
	 		}
	 		if(tempStArray[a].contains("//")) {
	 			tempStArray[a] = "";
	 		}
	 	}
	 	
	 	 	
	 	stArray = tempStArray;
	 	System.out.println("..The Sysout removed from the code..");
		x = stArray.length;
		 for(y=0;y<x;y++) {
			 
			System.out.println("Line " + (y+1) + "  : " + stArray[y]);
			 
		 }
	
		 if(type ==3) {
			 System.out.println("..Inheritance..");
			 
			 int ci = CalculateInheritanceComplexity.CalculateCi(stArray);
			 int cii = CalculateIInheritanceComplexity.CalculateCii(stArray);
			 
			 ArrayList<StatementLine> inheritanceList = new ArrayList<>();
			 ArrayList<StatementLine> iinheritanceList = new ArrayList<>();
			 
			 for(y=0;y<stArray.length;y++) {
				 inheritanceList.add(new StatementLine(y+1, ci));
				 iinheritanceList.add(new StatementLine(y+1, cii));
				}
			 
			 for(y=0;y<inheritanceList.size();y++) {
				 if (stArray[inheritanceList.get(y).getLineNumber() -1].isEmpty()) {
					 inheritanceList.get(y).setComplexity(0);
					 iinheritanceList.get(y).setComplexity(0);
				 }
			 }
			 
			 for(y=0;y<inheritanceList.size();y++) {
				 if (stArray[inheritanceList.get(y).getLineNumber() -1].contains("}")) {
					 inheritanceList.get(y).setComplexity(0);
					 iinheritanceList.get(y).setComplexity(0);
				 }
			 }
			 
			 
			 session.setAttribute("Ci", inheritanceList);
			session.setAttribute("Cii", iinheritanceList);
			 
			 request.getRequestDispatcher("DisplayComplexityOfInheritance.jsp").forward(request, response);
		 }
	

		System.out.println("..Inheritance..");
						 
						 int ci = CalculateInheritanceComplexity.CalculateCi(stArray);
						 int cii = CalculateIInheritanceComplexity.CalculateCii(stArray);
						 
						 ArrayList<StatementLine> inheritanceList = new ArrayList<>();
						 ArrayList<StatementLine> iinheritanceList = new ArrayList<>();
						 
						 for(y=0;y<stArray.length;y++) {
							 inheritanceList.add(new StatementLine(y+1, ci));
							 iinheritanceList.add(new StatementLine(y+1, cii));
							}
						 
						 for(y=0;y<inheritanceList.size();y++) {
							 if (stArray[inheritanceList.get(y).getLineNumber() -1].isEmpty()) {
								 inheritanceList.get(y).setComplexity(0);
								 iinheritanceList.get(y).setComplexity(0);
							 }
						 }
						 
						 for(y=0;y<inheritanceList.size();y++) {
							 if (stArray[inheritanceList.get(y).getLineNumber() -1].contains("}")) {
								 inheritanceList.get(y).setComplexity(0);
								 iinheritanceList.get(y).setComplexity(0);
							 }
						 }

		

		 
		 
	}
	
	
	
}
