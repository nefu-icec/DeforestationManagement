package edu.nefu.DeforeMgr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nefu.DeforeMgr.bean.SearchCheckDataItem;

@WebServlet("/PlanServlet")
public class PlanServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
	private SearchCheckDataItem search;
       
    public PlanServlet() 
    {
        super();
        search=new SearchCheckDataItem();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "searchCheckDatas":
			searchCheckDatas(request,response);
			break;
		default:
			break;
		}
	}
	
	private void setSearchBySession(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		if(session.getAttribute("SearchCheckDataItem")==null)
			session.setAttribute("SearchCheckDataItem", this.search);
		else
			this.search=(SearchCheckDataItem)session.getAttribute("SearchCheckDataItem");
	}
	
	private void witeSearchToSession(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		session.setAttribute("SearchCheckDataItem", this.search);
	}
	
	private void searchCheckDatas(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		setSearchBySession(request);
		request.setCharacterEncoding("gb2312");
		boolean empty=true;
		if(!request.getParameter("lb").equals(""))
		{
			search.setLb(Integer.parseInt(request.getParameter("lb"))); 
			empty=false;
		}
		else 
			search.setLb(0);
		if(!request.getParameter("xb").equals(""))
		{
			search.setXb(Integer.parseInt(request.getParameter("xb")));
			empty=false;
		}
		else
			search.setXb(0);
		if(!request.getParameter("dcdw").equals(""))
			empty=false;
		search.setDcdw(new String(request.getParameter("dcdw").getBytes("ISO-8859-1"), "gb2312"));
		if(!request.getParameter("id").equals(""))
		{
			search.setId(Integer.parseInt(request.getParameter("id")));
			empty=false;
		}
		else
			search.setId(0);
		if(empty)
			search=new SearchCheckDataItem();
		witeSearchToSession(request);
		request.getSession().setAttribute("SearchCheckDataItem", search);
		response.sendRedirect("plan/issueCutPlan.jsp");
	}
}