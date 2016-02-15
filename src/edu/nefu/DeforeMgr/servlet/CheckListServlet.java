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
import edu.nefu.DeforeMgr.util.LB_XB;

@WebServlet("/CheckListServlet")
public class CheckListServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public CheckListServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html; charset=gb2312");
		String task=request.getParameter("task");
		switch (task) 
		{
		case "add":
			add(request, response);
			break;
		case "delete":
			delete(request,response);
			break;
		case "clear":
			clear(request,response);
			break;
		case "submit":
			submit(request, response);
			break;
		case "check":
			check(request,response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String slb=request.getParameter("lb");
		String sxb=request.getParameter("xb");
		int lb=Integer.parseInt(slb);
		int xb=Integer.parseInt(sxb);
		PrintWriter out = response.getWriter();
		System.out.println(xb+" "+lb);
		
		HttpSession session = request.getSession(); 
		ArrayList<LB_XB> checkList=new ArrayList<LB_XB>();
		if(session.getAttribute("checkList")==null)
			session.setAttribute("checkList", checkList);
		else
			checkList=(ArrayList<LB_XB>)session.getAttribute("checkList");
		//判断所选小班id是否在session中
		boolean flag=false;
		for(int i=0;i<checkList.size();i++)
			if(checkList.get(i).getLb()==lb&&checkList.get(i).getXb()==xb)
			{
				flag=true;
				break;
			}
		if(flag==true)
			out.print("已存在所选林班号："+lb+"，小班号："+xb+"的小班，请不要重复添加<br>");
		else
		{
			checkList.add(new LB_XB(lb, xb));
			out.print("林班号："+lb+"，小班号："+xb+"的小班已添加<br>");
		}
		session.setAttribute("checkList", checkList);
		response.setHeader("refresh","1;url=plan/checkList.jsp") ;
	}
	
	@SuppressWarnings("unchecked")
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String slb=request.getParameter("lb");
		String sxb=request.getParameter("xb");
		int lb=Integer.parseInt(slb);
		int xb=Integer.parseInt(sxb);
		HttpSession session = request.getSession(); 
		ArrayList<LB_XB> checkList=(ArrayList<LB_XB>)session.getAttribute("checkList");
		for(int i=0;i<checkList.size();i++)
			if(checkList.get(i).getLb()==lb&&checkList.get(i).getXb()==xb)
				checkList.remove(i);
		session.setAttribute("checkList", checkList);
		response.sendRedirect("plan/checkList.jsp");
	}
	@SuppressWarnings("unchecked")
	private void submit(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String list="";
		HttpSession session = request.getSession(); 
		PrintWriter out = response.getWriter();
		ArrayList<LB_XB> checkList=new ArrayList<LB_XB>();
		if(session.getAttribute("checkList")==null)
			out.print("不存在验收计划列表！<br>");
		else
			checkList=(ArrayList<LB_XB>)session.getAttribute("checkList");
		if(!checkList.isEmpty())
		{
			for(int i=0;i<checkList.size();i++)
				list+=checkList.get(i).getLb()+"-"+checkList.get(i).getXb()+",";
			list=list.substring(0,list.length()-1);
			try 
			{
				ConnectDatabase connectDatabase=new ConnectDatabase();
				String insert="insert into checkList(list) value('"+list+"')";
				connectDatabase.exeUpdate(insert);
				connectDatabase.close();
				checkList.clear();
				session.setAttribute("checkList", checkList);
				out.print("计划已下达");
			} 
			catch (Exception e)
			{
				out.print("插入失败");
				e.printStackTrace();
			}
		}
		else
			out.print("验收列表空！");
		response.setHeader("Refresh", "1;URL='plan/checkList.jsp'");
	}
	@SuppressWarnings("unchecked")
	private void clear(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); 
		ArrayList<LB_XB> checkList=new ArrayList<LB_XB>();
		if(session.getAttribute("checkList")==null)
			out.print("不存在验收计划列表！<br>");
		else
			checkList=(ArrayList<LB_XB>)session.getAttribute("checkList");
		checkList.clear();
		session.setAttribute("checkList", checkList);
		out.print("clear");
		response.sendRedirect("plan/checkList.jsp");
	}
	
	private void check(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String id=request.getParameter("id");
		String pageNumber=request.getParameter("pageNumber");
		String number=request.getParameter("number");
		String sGetMethod=request.getParameter("sGetMethod");
		try
		{
			String updateCutList="update checklist set checked=1 where id="+id;
			ConnectDatabase connectDatabase=new ConnectDatabase();
			connectDatabase.exeUpdate(updateCutList);
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String url="plan/allCheckList.jsp?&pageNumber="+pageNumber+"&number="+number+"&sGetMethod="+sGetMethod;
		response.sendRedirect(url);
	}
}
