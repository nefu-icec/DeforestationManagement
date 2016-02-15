package edu.nefu.DeforeMgr.servlet;

import java.io.IOException;

import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private String loginMethod;
    private String loginInfo;
    private String password;
    private String task;
    
    public LoginServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "quit":
			quit(request, response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "login":
			login(request, response);
			break;
		default:
			break;
		}
	}
	
	private void login(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		request.setCharacterEncoding("gb2312");
		loginMethod=request.getParameter("loginMethod");
		loginInfo=new String(request.getParameter("loginInfo").getBytes("ISO-8859-1"),"gb2312");
		password=request.getParameter("password");
		if(isUserPasswordRight(loginInfo, password, loginMethod))
		{
			registerSession(request,response);
			response.sendRedirect("public/login.jsp");
		}	
		else
			response.sendRedirect("public/loginError.jsp");
	}
	
	private static boolean isUserPasswordRight(String loginInfo,String password,String loginMethod)
	{
		boolean right=false;
		String select="select * from user";
		switch (loginMethod) 
		{
		case "id":
			select+=" where uid='"+loginInfo+"'";
			break;
		case "name":
			select+=" where uname='"+loginInfo+"'";
			break;
		case "email":
			select+=" where email='"+loginInfo+"'";
			break;
		default:
			break;
		}
		select+=" and password='"+password+"'";
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			if(rs.next())
				right=true;
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return right;
	}
	
	private void registerSession(HttpServletRequest request,HttpServletResponse response) 
	{
		User user=new User();
		switch (loginMethod) 
		{
		case "id":
			user=User.getUserById(Integer.parseInt(loginInfo));
			break;
		case "name":
			user=User.getUserByName(loginInfo);
			break;
		case "email":
			user=User.getUserByEmail(loginInfo);
			break;
		default:
			break;
		}
		HttpSession session=request.getSession(); 
		session.setAttribute("user", user);
	}
	
	private void quit(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
//		User user=new User();
//		if(session.getAttribute("user")!=null)
//			user=(User)session.getAttribute("user");
		session.removeAttribute("user");
		response.sendRedirect("index.html");
	}
}
