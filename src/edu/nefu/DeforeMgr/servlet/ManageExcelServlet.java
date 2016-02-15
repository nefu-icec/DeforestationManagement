package edu.nefu.DeforeMgr.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.ReportExcel;

@WebServlet("/ManageExcelServlet")
public class ManageExcelServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
	
    public ManageExcelServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task) 
		{
		case "download":
			download(request,response);
			break;
		case "delete":
			delete(request,response);
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}

	private void download(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String sId=request.getParameter("id");
		int id=Integer.parseInt(sId);
		ServletContext application=this.getServletContext();   
		String path=application.getRealPath("/")+"excel\\";
		String fileName=getFileName(id);
		File file=new File(path+fileName);
		if(file.exists())
			response.sendRedirect("report/download.jsp?fileName="+fileName);
		else
			response.sendRedirect("report/manageReport.jsp");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String sId=request.getParameter("id");
		int id=Integer.parseInt(sId);
		String fileName=getFileName(id);
		ServletContext application=this.getServletContext();   
		String path=application.getRealPath("/")+"excel\\";
		File file=new File(path+fileName);
		if(file.exists())
			file.delete();
		String delete="delete from reportexcel where id="+id;
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			connectDatabase.exeUpdate(delete);
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		response.sendRedirect("report/manageReport.jsp");
	}
	
	private static String getFileName(int id)
	{
		ReportExcel reportExcel=ReportExcel.getReportExcelById(id);
		String fileName=reportExcel.getrName();
		return fileName;
	}
}
