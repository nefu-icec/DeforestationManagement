package edu.nefu.DeforeMgr.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nefu.DeforeMgr.util.LB_XB;

@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public AjaxServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "getXb":
			getXb(request,response);
			break;
		default:
			break;
		}
	}

	private void getXb(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String data="";
		try 
		{
			ArrayList<String> xbs=LB_XB.getXB(request.getParameter("lb"));
			for(String xb:xbs)
				data+=xb+",";
			data=data.substring(0, data.length()-1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		PrintWriter out=response.getWriter();
		out.print(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

}
