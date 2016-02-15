package edu.nefu.DeforeMgr.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nefu.DeforeMgr.bean.CarouselFigure;
import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.User;
import edu.nefu.DeforeMgr.util.FileName;
import edu.nefu.DeforeMgr.util.MyDate;

@WebServlet("/CarouselFigureServlet")
public class CarouselFigureServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    private String task;
    
    public CarouselFigureServlet() 
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
		case "upload":
			upload(request,response);
			break;
		case "newsLink":
			addNewsLink(request,response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		CarouselFigure figure=CarouselFigure.getFigureById(id);
		String fileName=figure.getName();
		String delete="delete from carouselfigure where id="+id;
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			connectDatabase.exeUpdate(delete);
			connectDatabase.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		ServletContext application=this.getServletContext();   
		String path=application.getRealPath("/")+"carouselFigure\\";
		File file=new File(path+fileName);
		if(file.exists())
			file.delete();
		response.sendRedirect("news/manageFigure.jsp");
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		String fileName=request.getParameter("fileName");
		ServletContext application=this.getServletContext();   
		String path=application.getRealPath("/")+"carouselFigure\\";
		//用当前时间命名文件
		String format=FileName.getFormat(fileName);
		String newFileName=MyDate.getNowTime()+"."+format;	
		FileName.modifyFileName(path, fileName,newFileName);
		User user=(User)session.getAttribute("user");
		String author=user.getUname();
		String date=MyDate.getNowDate();
		new CarouselFigure(newFileName, date, author).insertFigure();
		response.sendRedirect("news/manageFigure.jsp");
	}

	private void addNewsLink(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String snid=request.getParameter("nid");
		String sfid=request.getParameter("fid");
		int nid=Integer.parseInt(snid);
		int fid=Integer.parseInt(sfid);
		String update="update carouselfigure set nid="+nid+" where id="+fid;
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
		response.sendRedirect("news/manageFigure.jsp");
	}
}
