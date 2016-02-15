package edu.nefu.DeforeMgr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.CutList;
import edu.nefu.DeforeMgr.util.LB_XB;
import edu.nefu.DeforeMgr.util.MyRandom;

@WebServlet("/RandomCheckServlet")
public class RandomCheckServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public RandomCheckServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "add":
			add(request,response);
			break;
		case "delete":
			delete(request,response);
			break;
		case "clear":
			clear(request,response);
			break;
		case "submit":
			submit(request,response);
			break;
		default:
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}
	
	@SuppressWarnings("unchecked")
	private void add(HttpServletRequest request, HttpServletResponse response)
	{
		int id=Integer.parseInt(request.getParameter("id"));
		try 
		{
			CutList cutList=CutList.getCutListById(id);
			ArrayList<CutList> cutLists = new ArrayList<CutList>();
			cutLists.add(cutList);
			HttpSession session = request.getSession();
			if(session.getAttribute("checkRandomList")==null)
				session.setAttribute("checkRandomList", cutLists);
			else
			{
				cutLists =(ArrayList<CutList>)session.getAttribute("checkRandomList");
				boolean flag=false;
				for(CutList list:cutLists)
					if(list.getId()==id)
						flag=true;
				if(flag==false)
					cutLists.add(cutList);
				session.setAttribute("checkRandomList", cutLists);
			}
			response.sendRedirect("plan/checkListRandom.jsp");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		ArrayList<CutList> cutLists=(ArrayList<CutList>)session.getAttribute("checkRandomList");
		for(int i=0;i<cutLists.size();i++)
			if(cutLists.get(i).getId()==id)
				cutLists.remove(i);
		session.setAttribute("checkRandomList", cutLists);
		response.sendRedirect("plan/checkListRandom.jsp");
	}
	
	@SuppressWarnings("unchecked")
	private void clear(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); 
		ArrayList<CutList> cutLists=new ArrayList<CutList>();
		if(session.getAttribute("checkRandomList")==null)
			out.print("不存在下达采伐计划列表！<br>");
		else
			cutLists=(ArrayList<CutList>)session.getAttribute("checkRandomList");
		cutLists.clear();
		session.setAttribute("checkRandomList", cutLists);
		out.print("clear");
		response.sendRedirect("plan/checkListRandom.jsp");
	}
	
	@SuppressWarnings("unchecked")
	private void submit(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		double percentage = Integer.parseInt(request.getParameter("percentage"))/100.0;
		HttpSession session = request.getSession(); 
		PrintWriter out = response.getWriter();
		ArrayList<CutList> cutLists=new ArrayList<CutList>();
		if(session.getAttribute("checkRandomList")==null)
			out.print("不存在验收计划列表！<br>");
		else
		{
			cutLists=(ArrayList<CutList>)session.getAttribute("checkRandomList");
			int listSize=cutLists.size();
			int need=(int)Math.ceil(listSize*percentage);//向上取整
			int [] rands=MyRandom.getRandom(need-1, listSize);
			String buffer="";
			for(int i=0;i<rands.length;i++)
			{	
				ArrayList<LB_XB> lbxbs=CutList.getListLBXB(cutLists.get(rands[i]).getList());
				for(LB_XB lbxb:lbxbs)
					buffer+=lbxb.getLb()+"-"+lbxb.getXb()+",";
			}
			buffer=buffer.substring(0, buffer.length()-1);
			String insert="insert into checklist(list,checked) values('"+buffer+"',0)";
			try 
			{
				ConnectDatabase connectDatabase = new ConnectDatabase();
				connectDatabase.exeUpdate(insert);
				connectDatabase.close();
				cutLists.clear();
				session.setAttribute("checkRandomList", cutLists);
				out.print("success");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				out.print("fail");
			}
		}
		response.setHeader("Refresh", "1;URL='plan/checkListRandom.jsp'");	
	}
}
