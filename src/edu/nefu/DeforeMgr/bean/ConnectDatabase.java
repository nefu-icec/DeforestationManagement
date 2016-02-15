package edu.nefu.DeforeMgr.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectDatabase 
{
	Connection connection=null;
	Statement statement=null;
	
	public ConnectDatabase() throws ClassNotFoundException, SQLException 
	{
		try
		{
//			Context context=new InitialContext();
//			DataSource ds=(DataSource)context.lookup("java://comp/env/jdbc/deforestation");
//			connection=ds.getConnection();
//			statement=connection.createStatement();
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://127.0.0.1:3306/deforestation?user=root&password=";
			connection=DriverManager.getConnection(url);
			statement=connection.createStatement();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public ResultSet exeQuery(String sql)
	{
		ResultSet rs=null;
		try {
			rs=statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;	
	}
	public int exeUpdate(String sql)
	{
		int flag=0;
		try {
			flag=statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public void close()
	{
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}