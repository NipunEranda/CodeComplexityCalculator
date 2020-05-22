package com.ccc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.model.Inheritance;
import com.ccc.services.InheritanceServiceImp;

@WebServlet("/UpdateInheritanceWeight")
public class UpdateInheritanceWeight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInheritanceWeight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("update") != null) {
			if(request.getParameter("update").equalsIgnoreCase("true")) {
				
				int W_direct = Integer.parseInt(request.getParameter("c1"));
				int W_indirect = Integer.parseInt(request.getParameter("c2"));
				int W_total = Integer.parseInt(request.getParameter("c3"));
				int ci = Integer.parseInt(request.getParameter("c4"));
			
			Inheritance.setCustomWeights(W_direct, W_indirect, W_total, ci);
			
			InheritanceServiceImp.process3();
			response.sendRedirect("resultpage.jsp?edited=inheritance");
			}
		}
		
		if (request.getParameter("default") != null) {

			if (request.getParameter("default").equalsIgnoreCase("true")) {
				Inheritance.setDefaultWeights();
				InheritanceServiceImp.process3();
				response.sendRedirect("components/InheritanceWeightChange.jsp");
			}

	}
	}}