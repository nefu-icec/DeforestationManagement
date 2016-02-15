package edu.nefu.DeforeMgr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nefu.DeforeMgr.bean.AndroidCheckdataBuffer;
import edu.nefu.DeforeMgr.bean.UnCheckList;
import edu.nefu.DeforeMgr.util.CopyFiles;
import edu.nefu.DeforeMgr.util.MyDate;
import edu.nefu.DeforeMgr.util.ReadFromFile;
import edu.nefu.DeforeMgr.util.XmlTool;

/**
 * 与Android客户端通信的服务程序
 * @author lm2343635
 *
 */
@WebServlet("/AndroidServiceServlet")
public class AndroidServiceServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public AndroidServiceServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "getCheckList":
			pushCheckListToAndroid(request,response);
			break;
		case "update":
			update(request,response);
		default:
			break;
		}
	}        

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task) 
		{
		case "postCheckData":
			getCheckDataFromAndroid(request,response);
			break;

		default:
			break;
		}
	}

	/**
	 * 向Android客户端推送要验收的林班号和小班号
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void pushCheckListToAndroid(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String path=getServletConfig().getServletContext().getRealPath("/");
		String nowTime=MyDate.getNowTime();
		File sourceFile=new File(path+"/xml/AndroidPushCheckListTemplate.xml");
		File targetFile=new File(path+"/xml/"+nowTime+".xml");
		CopyFiles.copyFile(sourceFile, targetFile);
		XmlTool builder=new XmlTool(path+"/xml/"+nowTime+".xml");
		builder.createPushCheckList();
		String xml=ReadFromFile.readFileByChars(path+"/xml/"+nowTime+".xml");
		response.getWriter().print(xml);
		if(targetFile.exists())
			targetFile.delete();
	}

	/**
	 * 向Android客户端推送更新信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String path=getServletConfig().getServletContext().getRealPath("/");
		String nowTime=MyDate.getNowTime();
		File sourceFile=new File(path+"/xml/AndroidUpdateTemplate.xml");
		File targetFile=new File(path+"/xml/"+nowTime+".xml");
		CopyFiles.copyFile(sourceFile, targetFile);
		XmlTool builder=new XmlTool(path+"/xml/"+nowTime+".xml");
		InetAddress addr = InetAddress.getLocalHost();
		String serverIP=addr.getHostAddress().toString();//获得本机IP
		builder.createUpdate(serverIP);
		String xml=ReadFromFile.readFileByChars(path+"/xml/"+nowTime+".xml");
		response.getWriter().print(xml);
		if(targetFile.exists())
			targetFile.delete();
	}

	/**
	 * 接收Android客户端传送的验收数据
	 * @param request
	 * @param response
	 */
	private void getCheckDataFromAndroid(HttpServletRequest request,HttpServletResponse response) 
	{
		String xml=request.getParameter("xml");
		String nowTime=MyDate.getNowTime();
		System.out.println(nowTime+" success");
		String rootPath=getServletConfig().getServletContext().getRealPath("/");
		String fileName=nowTime+".xml";
		String path=rootPath+"xml\\"+fileName;
		try 
		{
			FileOutputStream outputStream=new FileOutputStream(path);
			outputStream.write(xml.getBytes());
			outputStream.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("接收失败");
		}
		File file=new File(path);
		if(file.exists())
		{
			XmlTool tool=new XmlTool(path);
			ArrayList<AndroidCheckdataBuffer> lists=tool.readCheckData();//读取Android传送的数据
			UnCheckList unCheckList=new UnCheckList();
			for(AndroidCheckdataBuffer data:lists)
				if(unCheckList.inUnCheckedList(data.getLb(), data.getXb(),data.getCheckListId()))//判断Android传送的checkdata是否在未验收的小班中
					data.insertOrUpdate();
			AndroidCheckdataBuffer.scanBuffer();//扫描缓冲区，是否有具有一组数据能更新CheckList
			file.delete();//删除xml文件，完成更新
		}
	}
}
