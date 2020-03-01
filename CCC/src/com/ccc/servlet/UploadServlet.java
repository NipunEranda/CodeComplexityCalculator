package com.ccc.servlet;

import com.ccc.*;
import com.ccc.services.Main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private final String UPLOAD_DIRECTORY = Main.WEBCONTENTDIR + "uploads/";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(ServletFileUpload.isMultipartContent(request))
		{
			try
			{
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for(FileItem item : multiparts)
				{
					if(!item.isFormField())
					{
						String name = new File(item.getName()).getName();
						if(!(name.contains("java") || name.contains("cpp"))) {
							System.out.println("Upload UnSuccessful. Wrong File Format");
						}else {
							item.write(new File(UPLOAD_DIRECTORY + name));
							Main.run(name);
							//request.setAttribute("message", "File uploaded successfully.");
							//System.out.println("Upload Successful");
						}
						
					}
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				request.setAttribute("message", "File upload failed due to : " + ex);
				System.out.println("Upload UnSuccessful");
			}
		}
		else
		{
			request.setAttribute("message", "Sorry this servlet only handles file upload request.");
		}
		request.getRequestDispatcher("/fileupload.jsp").forward(request, response);
		
	}

}
