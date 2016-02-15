package edu.nefu.DeforeMgr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.News;
import edu.nefu.DeforeMgr.bean.User;
import edu.nefu.DeforeMgr.util.MyDate;

@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private String task;   
	
    public NewsServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task) 
		{
		case "delete":
			delete(request,response);
			break;

		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task) {
		case "add":
			add(request,response);
			break;
		case "modify":
			modify(request,response);
			break;
		default:
			break;
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		request.setCharacterEncoding("gb2312");
		HttpSession session=request.getSession(); 
		String title=request.getParameter("title");
		title=new String(title.getBytes( "ISO8859-1"), "GB2312"); 
		String content=request.getParameter("content");
		content=new String(content.getBytes( "ISO8859-1"), "GB2312"); 
		String ndate=MyDate.getNowDate();
		User user=(User)session.getAttribute("user");
		String author=user.getUname();
		new News(ndate,content,title,author).insertNews();
		response.sendRedirect("news/manageNews.jsp");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String id=request.getParameter("id");
		String delete="delete from news where id="+id;
		try
		{
			ConnectDatabase connectDatabase = new ConnectDatabase();
			connectDatabase.exeUpdate(delete);
			connectDatabase.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		response.sendRedirect("news/manageNews.jsp");
	}
	
	private void modify(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		request.setCharacterEncoding("gb2312");
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		title=new String(title.getBytes( "ISO8859-1"), "GB2312"); 
		String content=request.getParameter("content");
		content=new String(content.getBytes( "ISO8859-1"), "GB2312"); 
		String update="update news set title='"+title+"',content='"+content+"' where id="+id;
		try
		{
			ConnectDatabase connectDatabase = new ConnectDatabase();
			connectDatabase.exeUpdate(update);
			connectDatabase.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		response.sendRedirect("news/manageNews.jsp");
	}
}
