package edu.nefu.DeforeMgr.servlet;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.User;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private String task;
    
    public UserServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task) 
		{
		case "changeType":
			changeType(request,response);
			break;
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
		switch (task)
		{
		case "info":
			modifyInfo(request,response);
			break;
		case "password":
			modifyPassword(request,response);
			break;
		case "register":
			guestRegister(request,response);
			break;
		case "addUser":
			addUser(request,response);
			break;
		default:
			break;
		}
	}

	private void modifyPassword(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		String password=request.getParameter("password");
		String oldPassword=request.getParameter("oldPassword");
		User user=User.getUserById(id);
		boolean success=user.modifyPassword(password, oldPassword);
		session.setAttribute("user", user);
		response.sendRedirect("manageInfo/modifyResult.jsp?success="+success);
	}

	private void modifyInfo(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		request.setCharacterEncoding("gb2312");
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		String uname=request.getParameter("uname");
		uname=new String(uname.getBytes( "ISO8859-1"), "GB2312"); 
		String email=request.getParameter("email");
		User user=User.getUserById(id);
		user.setUname(uname);
		user.setEmail(email);
		boolean success=user.modifyInfo();
		session.setAttribute("user", user);
		response.sendRedirect("manageInfo/modifyResult.jsp?success="+success);
	}
	
	private void guestRegister(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		request.setCharacterEncoding("gb2312");
		String password=request.getParameter("password");
		String uname=request.getParameter("uname");
		uname=new String(uname.getBytes( "ISO8859-1"), "GB2312"); 
		String email=request.getParameter("email");
		User user=new User(uname, password, email);
		boolean success=user.guestRegister();
		response.sendRedirect("public/registerResult.jsp?uid="+user.getUid()+"&success="+success);
	}
	
	private void addUser(HttpServletRequest request,HttpServletResponse response) 
	{
		String privilege = request.getParameter("privilege");
		int type = Integer.parseInt(privilege);
		String password="123";
		if(request.getParameter("password")!=null)
			password = request.getParameter("password");
		try
		{
			String maxUid = User.getMaxUID(type);
			new User(Integer.parseInt(maxUid) + 1, password,User.getType(type)).insertUser();
			response.sendRedirect("manageUser/manageUser.jsp");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

	private void changeType(HttpServletRequest request,HttpServletResponse response) 
	{
		String type=request.getParameter("type");
		try
		{
			ArrayList<User> users=User.getUserByType(Integer.parseInt(type));
			request.getSession().setAttribute("users", users);
			response.sendRedirect("manageUser/manageUser.jsp?type="+type);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) 
	{
		String uid=request.getParameter("uid");
		String delete="delete from user where uid="+uid;
		try
		{
			ConnectDatabase connectDatabase = new ConnectDatabase();
			connectDatabase.exeUpdate(delete);
			response.sendRedirect("manageUser/manageUser.jsp");
			connectDatabase.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
