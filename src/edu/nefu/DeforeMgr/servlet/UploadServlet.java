package edu.nefu.DeforeMgr.servlet;

import java.io.*;
import javax.servlet.*;
import com.jspsmart.upload.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	private String task;
       
    public UploadServlet() 
    {
        super();
    }
    
	final public void init(ServletConfig config) throws ServletException 
	{
		this.config = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		task=request.getParameter("task");
		switch (task) 
		{
		case "checkFile":
			uploadCheckFile(request,response);
			break;
		case "figure":
			uploadFigure(request,response);
		default:
			break;
		}
	}
	
	private void uploadCheckFile(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String id=request.getParameter("id");
		String fileName=upload(request, response, "/sql/");
		response.sendRedirect("InputCheckServlet?task=importCheckFile&fileName="+fileName+"&id="+id);
	}
	
	private void uploadFigure(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String fileName=upload(request, response,"/carouselFigure/");
		response.sendRedirect("CarouselFigureServlet?task=upload&fileName="+fileName);
	}
	
	private String upload(HttpServletRequest request,HttpServletResponse response,String path) 
	{
		int count=0;
		String fileName="";
		SmartUpload mySmartUpload = new SmartUpload();
		try 
		{
			mySmartUpload.initialize(config,request,response);
			mySmartUpload.upload();
			count=mySmartUpload.save(path);
			fileName=mySmartUpload.getFiles().getFile(0).getFileName();
			response.getWriter().print(count + " success "+fileName);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return fileName;
	}
}
