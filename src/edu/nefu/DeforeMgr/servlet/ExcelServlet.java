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

import edu.nefu.DeforeMgr.bean.CheckData;
import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.report.Report;
import edu.nefu.DeforeMgr.util.LB_XB;
import edu.nefu.DeforeMgr.util.MyDate;

@WebServlet("/ExcelServlet")
public class ExcelServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public ExcelServlet()
    {
        super();
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
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
		case "selectAll":
			selectAll(request,response);
			break;
		case "clear":
			clear(request,response);
			break;
		case "create":
			create(request, response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "create":
			create(request,response);
			break;

		default:
			break;
		}
		
    }
	
	@SuppressWarnings("unchecked")
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String slb=request.getParameter("lb");
		String sxb=request.getParameter("xb");
		int lb=Integer.parseInt(slb);
		int xb=Integer.parseInt(sxb);
		ArrayList<LB_XB> excelList=new ArrayList<LB_XB>();
		HttpSession session=request.getSession(); 
		if(session.getAttribute("excelList")==null)
			session.setAttribute("excelList", excelList);
		else
			excelList=(ArrayList<LB_XB>)session.getAttribute("excelList");
		//判断所选林班lb，小班xb是否在session中
		boolean flag=false;
		for(int i=0;i<excelList.size();i++)
			if(excelList.get(i).getLb()==lb&&excelList.get(i).getXb()==xb)
			{
				flag=true;
				break;
			}
		if(flag==false)
			excelList.add(new LB_XB(lb, xb));
		session.setAttribute("excelList", excelList);
		response.sendRedirect("report/createReport.jsp");
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String slb=request.getParameter("lb");
		String sxb=request.getParameter("xb");
		int lb=Integer.parseInt(slb);
		int xb=Integer.parseInt(sxb);
		HttpSession session=request.getSession(); 
		ArrayList<LB_XB> excelList=getExcelSession(request);
		for(int i=0;i<excelList.size();i++)
			if(excelList.get(i).getLb()==lb&&excelList.get(i).getXb()==xb)
				excelList.remove(i);
		session.setAttribute("excelList", excelList);
		response.sendRedirect("report/createReport.jsp");
	}
	
	private void clear(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		HttpSession session=request.getSession(); 
		ArrayList<LB_XB> excelList=getExcelSession(request);
		excelList.clear();
		session.setAttribute("excelList", excelList);
		response.sendRedirect("report/createReport.jsp");
	}
	
	@SuppressWarnings("unchecked")
	private void selectAll(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		ArrayList<LB_XB> excelList=new ArrayList<LB_XB>();
		HttpSession session=request.getSession(); 
		if(session.getAttribute("excelList")==null)
			session.setAttribute("excelList", excelList);
		else
			excelList=(ArrayList<LB_XB>)session.getAttribute("excelList");
		String where=" where cut=1 and checked=1 and volume>0";
		ArrayList<CheckData> checkDatas;
		try 
		{
			checkDatas = CheckData.getCheckDatas(where);
			for(int i=0;i<checkDatas.size();i++)
				excelList.add(new LB_XB(checkDatas.get(i).getLb(),checkDatas.get(i).getXb()));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		session.setAttribute("excelList", excelList);
		response.sendRedirect("report/createReport.jsp");
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		PrintWriter out = response.getWriter();
		String path=request.getSession().getServletContext().getRealPath("/");
		path+="\\excel";
		try 
		{
			ArrayList<LB_XB> excelList=getExcelSession(request);
			LB_XB [] lbxbs=new LB_XB[excelList.size()];
			for(int i=0;i<excelList.size();i++)
				lbxbs[i]=excelList.get(i);
			
			String fileName=Report.createReport(path, "报表", lbxbs);
			ConnectDatabase connectDatabase=new ConnectDatabase();
			connectDatabase.exeUpdate("insert into reportexcel(rName,date) values('"+fileName+"','"+MyDate.getNowDate()+"')");
			connectDatabase.close();
			out.print(path+"\\"+fileName);
			response.sendRedirect("report/download.jsp?fileName="+fileName);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}
	
	public static boolean isThisXBExist(int lb,int xb,HttpServletRequest request)
	{
		boolean exsit=false;
		ArrayList<LB_XB> excelList=getExcelSession(request);
		for(int i=0;i<excelList.size();i++)
			if(excelList.get(i).getLb()==lb&&excelList.get(i).getXb()==xb)
				exsit=true;
		return exsit;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<LB_XB> getExcelSession(HttpServletRequest request)
	{
		ArrayList<LB_XB> excelList=new ArrayList<LB_XB>();
		HttpSession session=request.getSession(); 
		if(session.getAttribute("excelList")!=null)
			excelList=(ArrayList<LB_XB>)session.getAttribute("excelList");
		return excelList;
	}

}
