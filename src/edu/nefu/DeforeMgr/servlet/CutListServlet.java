package edu.nefu.DeforeMgr.servlet;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.util.LB_XB;

@SuppressWarnings("serial")
@WebServlet("/CutListServlet")
public class CutListServlet extends HttpServlet 
{
	@SuppressWarnings("unused")
	private ServletConfig config; 

	final public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}
	//处理GET请求
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html; charset=gb2312");
		String task=request.getParameter("task");
		switch (task)
		{
		case "add":
			add(request, response);
			break;
		case "submit":
			submit(request,response);
			break;
		case "clear":
			clear(request,response);
			break;
		case "delete":
			delete(request, response);
//		case "cut":
//			cut(request,response);
		default:
			break;
		}
	}

	//响应POST请求
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		out.println("The method of the HTML form must be GET.");
    }
	
	@SuppressWarnings("unchecked")
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String slb=request.getParameter("lb");
		String sxb=request.getParameter("xb");
		int lb=Integer.parseInt(slb);
		int xb=Integer.parseInt(sxb);
		ArrayList<LB_XB> cutList=new ArrayList<LB_XB>();
		HttpSession session = request.getSession(); 
		PrintWriter out = response.getWriter();
		if(session.getAttribute("cutList")==null)
			session.setAttribute("cutList", cutList);
		else
			cutList=(ArrayList<LB_XB>)session.getAttribute("cutList");
		//判断所选林班lb，小班xb是否在session中
		boolean flag=false;
		for(int i=0;i<cutList.size();i++)
			if(cutList.get(i).getLb()==lb&&cutList.get(i).getXb()==xb)
			{
				flag=true;
				break;
			}
		if(flag==true)
			out.print("已存在所选林班号："+lb+"，小班号："+xb+"的小班，请不要重复添加<br>");
		else
		{
			cutList.add(new LB_XB(lb, xb));
			out.print("林班号："+lb+"，小班号："+xb+"的小班已添加<br>");
		}
		session.setAttribute("cutList", cutList);
		response.setHeader("refresh","1;url=plan/cutList.jsp") ;
	}
	@SuppressWarnings("unchecked")
	private void submit(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String list="";
		HttpSession session = request.getSession(); 
		PrintWriter out = response.getWriter();
		ArrayList<LB_XB> cutList=new ArrayList<LB_XB>();
		if(session.getAttribute("cutList")==null)
			out.print("不存在下达采伐计划列表！<br>");
		else
			cutList=(ArrayList<LB_XB>)session.getAttribute("cutList");
		if(!cutList.isEmpty())
		{
			for(int i=0;i<cutList.size();i++)
				list+=cutList.get(i).getLb()+"-"+cutList.get(i).getXb()+",";
			list=list.substring(0,list.length()-1);
			try 
			{
				ConnectDatabase connectDatabase=new ConnectDatabase();
				String insert="insert into cutlist(list) value('"+list+"')";
				connectDatabase.exeUpdate(insert);
				connectDatabase.close();
				cutList.clear();
				session.setAttribute("cutList", cutList);
				out.print("计划已下达");
			} 
			catch (Exception e)
			{
				out.print("插入失败");
				e.printStackTrace();
			}
		}
		else
			out.print("采伐列表空！");
		response.setHeader("Refresh", "1;URL='plan/cutList.jsp'");
	}
	
	@SuppressWarnings("unchecked")
	private void clear(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); 
		ArrayList<LB_XB> cutList=new ArrayList<LB_XB>();
		if(session.getAttribute("cutList")==null)
			out.print("不存在下达采伐计划列表！<br>");
		else
			cutList=(ArrayList<LB_XB>)session.getAttribute("cutList");
		cutList.clear();
		session.setAttribute("cutList", cutList);
		out.print("clear");
		response.sendRedirect("plan/cutList.jsp");
	}
	
	@SuppressWarnings("unchecked")
	private void delete(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String slb=request.getParameter("lb");
		String sxb=request.getParameter("xb");
		int lb=Integer.parseInt(slb);
		int xb=Integer.parseInt(sxb);
		HttpSession session = request.getSession(); 
		ArrayList<LB_XB> cutList=(ArrayList<LB_XB>)session.getAttribute("cutList");
		for(int i=0;i<cutList.size();i++)
			if(cutList.get(i).getLb()==lb&&cutList.get(i).getXb()==xb)
				cutList.remove(i);
		session.setAttribute("cutList", cutList);
		response.sendRedirect("plan/cutList.jsp");
	}
	/*
	private void cut(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String slb=request.getParameter("lb");
		String sxb=request.getParameter("xb");
		int lb=Integer.parseInt(slb);
		int xb=Integer.parseInt(sxb);
		String pageNumber=request.getParameter("pageNumber");
		String number=request.getParameter("number");
		String sGetMethod=request.getParameter("sGetMethod");
		try 
		{
			String updateCutList="update cutlist set cut=1 where lb="+lb+" and xb="+xb;
			CutList cutList=CutList.getCutListById(Integer.parseInt(id));
			int [] listId=CutList.getListId(cutList.getList());
			String [] updateCheckData=new String[listId.length];
			for(int i=0;i<listId.length;i++)
				updateCheckData[i]="update checkdata set cut=1 where id="+listId[i];
			ConnectDatabase connectDatabase;
			connectDatabase = new ConnectDatabase();
			connectDatabase.exeUpdate(updateCutList);
			for(int i=0;i<updateCheckData.length;i++)
				connectDatabase.exeUpdate(updateCheckData[i]);
			connectDatabase.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		String url="plan/allCutList.jsp?&pageNumber="+pageNumber+"&number="+number+"&sGetMethod="+sGetMethod;
		response.sendRedirect(url);
	}
	*/
}
