package com.ccc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.ccc.model.ControlStructure;
import com.ccc.model.CustomFile;
import com.ccc.services.ControlStructureService;
import com.ccc.services.ControlStructureServiceImp;

@WebServlet("/UpdateCSWeights")
public class UpdateCSWeights extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			if(request.getParameter("weightTYpe").equals("update")) {
				System.out.println("Weight updated in UI");
				int wCondition = Integer.parseInt(request.getParameter("weightC"));
				int wIteration = Integer.parseInt(request.getParameter("weightI"));
				int wSwitch = Integer.parseInt(request.getParameter("weightSwitch"));
				int wCase = Integer.parseInt(request.getParameter("weightCase"));
//				ArrayList<CustomFile> fileList = (ArrayList) request.getAttribute("fileList");

				ControlStructure.setCustomWeight(wCondition, wIteration, wSwitch, wCase);
				ControlStructureServiceImp.process3();
				response.sendRedirect("resultpage.jsp");
				
			}else if(request.getParameter("weightTYpe").equals("default")) {
				System.out.println("Weight set Default in UI");
				ControlStructure.setDefaultWeights();
				response.sendRedirect("components/controlStructureEditWeights.jsp");
			}
			
				

		}catch (Exception e) {
			// TODO: handle exception
			ControlStructure.setDefaultWeights();
			ControlStructureServiceImp.process3();
			response.sendRedirect("components/controlStructureEditWeights.jsp");
			e.getMessage();	
		}

	}

}