package com.ccc.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.model.CustomFile;
import com.ccc.model.Line;
import com.ccc.services.Main;

/**
 * Servlet implementation class RawInputServlet
 */
@WebServlet("/RawInputServlet")
public class RawInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean status = false;
		Main main = new Main();
		String code = request.getParameter("code");
		CustomFile file = new CustomFile("raw.java");
		file.setIsRaw(true);
		ArrayList<Line> lineSet = new ArrayList<Line>();
		int lastIndex = 0;

		String[] lines = code.split("\\n");

		for (int i = 0; i < lines.length; i++) {
			lineSet.add(new Line(i + 1, lines[i]));
			lastIndex = i+1;
		}

		try {
			if (lineSet != null) {
				file.setLineSet(lineSet);
				file.setLastIndex(lastIndex);
				main.getFileList().add(file);
				status = main.run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(status) {
			HttpSession session = request.getSession();
			session.setAttribute("fileList", main.getFileList());
			response.sendRedirect("resultpage.jsp");
		}else {
			response.sendRedirect("index.jsp");
		}
	}

}
