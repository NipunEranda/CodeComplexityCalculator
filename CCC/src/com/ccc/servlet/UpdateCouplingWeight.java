package com.ccc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.model.Coupling;
import com.ccc.services.CouplingServiceImp;

@WebServlet("/UpdateCouplingWeight")
public class UpdateCouplingWeight extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("update") != null) {

			if (request.getParameter("update").equalsIgnoreCase("true")) {

				int Wr = Integer.parseInt(request.getParameter("c1"));
				int Wmcms = Integer.parseInt(request.getParameter("c2"));
				int Wmcmd = Integer.parseInt(request.getParameter("c3"));
				int Wmcrms = Integer.parseInt(request.getParameter("c4"));
				int Wmcrmd = Integer.parseInt(request.getParameter("c5"));
				int Wrmcrms = Integer.parseInt(request.getParameter("c6"));
				int Wrmcrmd = Integer.parseInt(request.getParameter("c7"));
				int Wrmcms = Integer.parseInt(request.getParameter("c8"));
				int Wrmcmd = Integer.parseInt(request.getParameter("c9"));
				int Wmrgvs = Integer.parseInt(request.getParameter("c10"));
				int Wmrgvd = Integer.parseInt(request.getParameter("c11"));
				int Wrmrgvs = Integer.parseInt(request.getParameter("c12"));
				int Wrmrgvd = Integer.parseInt(request.getParameter("c13"));

				Coupling.setCustomWeights(Wr, Wmcms, Wmcmd, Wmcrms, Wmcrmd, Wrmcrms, Wrmcrmd, Wrmcms, Wrmcmd, Wmrgvs,
						Wmrgvd, Wrmrgvs, Wrmrgvd);

				CouplingServiceImp.process3();
				response.sendRedirect("resultpage.jsp");
			}

		}

		if (request.getParameter("default") != null) {

			if (request.getParameter("default").equalsIgnoreCase("true")) {
				Coupling.setDefaultWeights();
				CouplingServiceImp.process3();
				response.sendRedirect("components/couplingWeightChanger.jsp");
			}

		}
	}

}
