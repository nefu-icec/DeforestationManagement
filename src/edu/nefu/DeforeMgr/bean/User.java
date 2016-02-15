package edu.nefu.DeforeMgr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User 
{
	private int uid;
	private String uname;
	private String password;
	private String privilege;
	private String email;
	
	public static final int ALL_EXCEPT_ADMIN=-2;
	public static final int ALL=-1;
	public static final int ADMIN=0;
	public static final int FORESTRY=1;
	public static final int CUTER=2;
	public static final int CHECKER=3;
	public static final int GUSET=4;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User() {
		super();
	}
	
	public User(int uid, String uname, String password, String privilege,String email)
	{
		super();
		this.uid = uid;
		this.uname = uname;
		this.password = password;
		this.privilege = privilege;
		this.email = email;
	}
	
	public User(String uname, String password, String email) 
	{
		super();
		this.uname = uname;
		this.password = password;
		this.email = email;
	}
	
	public User(int uid, String password,String privilege) 
	{
		super();
		this.uid = uid;
		this.password = password;
		this.privilege = privilege;
	}
	
	public static ArrayList<User> getUser(String where)
	{
		ArrayList<User> users=new ArrayList<User>();
		if(where==null)
			where="";
		String select="select * from user"+where;
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			while(rs.next())
				users.add(new User(Integer.parseInt(rs.getString("uid")),
						                       rs.getString("uname"),
						                       rs.getString("password"),
						                       rs.getString("privilege"),
						                       rs.getString("email")));
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return users;
	}
	
	public static User getUserById(int uid)
	{
		String where=" where uid="+uid;
		ArrayList<User> users=getUser(where);
		User user=null;
		if(!users.isEmpty())
			user=users.get(0);
		return user;
	}
	
	public static User getUserByName(String uname)
	{
		String where=" where uname='"+uname+"'";
		ArrayList<User> users=getUser(where);
		User user=null;
		if(!users.isEmpty())
			user=users.get(0);
		return user;
	}
	
	public static User getUserByEmail(String email)
	{
		String where=" where email='"+email+"'";
		ArrayList<User> users=getUser(where);
		User user=null;
		if(!users.isEmpty())
			user=users.get(0);
		return user;
	}
	
	public boolean modifyPassword(String password,String oldPassword)
	{
		boolean success=false;
		try
		{
			String select="select password from user where uid="+uid;
			ConnectDatabase connectDatabase=new ConnectDatabase();
			String selectPassword="";
			ResultSet rs=connectDatabase.exeQuery(select);
			if(rs.next())
				selectPassword=rs.getString("password");
			if(selectPassword.equals(oldPassword))
			{
				this.password=password;
				String update="update user set password='"+password+"' where uid="+uid;
				int count=connectDatabase.exeUpdate(update);
				if(count>0)
					success=true;
			}
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean modifyInfo()
	{
		boolean success=false;
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			String update="update user set uname='"+uname+"',email='"+email+"' where uid="+uid;
			int count=connectDatabase.exeUpdate(update);
			if(count>0)
				success=true;
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean insertUser()
	{
		boolean success=false;
		String insert="insert into user(uid,password,privilege) values("+uid+",'"+password+"','"+privilege+"')";
		try
		{
			int count=0;
			ConnectDatabase connectDatabase=new ConnectDatabase();
			count=connectDatabase.exeUpdate(insert);
			connectDatabase.close();
			if(count>0)
				success=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean guestRegister()
	{
		boolean success=false;
		String select="select uid from user where uid>=all(select uid from user where privilege='guest' order by uid desc)";
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			int uid=0;
			if(rs.next())
				uid=Integer.parseInt(rs.getString("uid"));
			if(uid!=0)
			{
				this.uid=uid+1;
				privilege="guest";
				success=insertUser();
			}
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return success;
	}
	
	public static String getMaxUID(int uerType) throws ClassNotFoundException, SQLException
	{
		String type=getType(uerType);
		String select="select * from user where privilege='"+type+"' order by uid desc";
		ConnectDatabase connectDatabase=new ConnectDatabase();
		ResultSet rs=connectDatabase.exeQuery(select);
		String uid=null;
		if(rs.next())
			uid=rs.getString("uid");
		else
		{
			switch (uerType) 
			{
			case 1:
				uid="10000000";
				break;
			case 2:
				uid="20000000";
				break;
			case 3:
				uid="30000000";
				break;
			case 4:
				uid="40000000";
				break;
			default:
				break;
			}
		}
		connectDatabase.close();
		return uid;
	}
	
	public static ArrayList<User> getUserByType(int uerType) throws SQLException, ClassNotFoundException
	{
		String type=getType(uerType);
		ArrayList<User> users=new ArrayList<User>();
		String select="select * from user";
		if(type.equals("admin"))
			select+=" where privilege!='admin'";
		if(!type.equals("all")&&!type.equals("admin"))
			select+=" where privilege='"+type+"'";
		ConnectDatabase connectDatabase=new ConnectDatabase();
		ResultSet rs=connectDatabase.exeQuery(select);
		while(rs.next())
		{
			String uid=rs.getString("uid");
			String uname=rs.getString("uname");
			String password=rs.getString("password");
			String email=rs.getString("email");
			String privilege=rs.getString("privilege");
			users.add(new User(Integer.parseInt(uid), uname, password, privilege, email));
		}
		connectDatabase.close();
		return users;
	}
	
	public static String getType(int uerType)
	{
		String type="";
		switch (uerType)
		{
		case -2:
			type="all_except_admin";
		case -1:
			type="all";
		case 0:
			type="admin";
			break;
		case 1:
			type="forestry";
			break;
		case 2:
			type="cuter";
			break;
		case 3:
			type="checker";
			break;
		case 4:
			type="guest";
			break;
		default:
			break;
		}
		return type;
	}
}
