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
 * ��Android�ͻ���ͨ�ŵķ������
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
	 * ��Android�ͻ�������Ҫ���յ��ְ�ź�С���
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
	 * ��Android�ͻ������͸�����Ϣ
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
		String serverIP=addr.getHostAddress().toString();//��ñ���IP
		builder.createUpdate(serverIP);
		String xml=ReadFromFile.readFileByChars(path+"/xml/"+nowTime+".xml");
		response.getWriter().print(xml);
		if(targetFile.exists())
			targetFile.delete();
	}

	/**
	 * ����Android�ͻ��˴��͵���������
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
			System.out.println("����ʧ��");
		}
		File file=new File(path);
		if(file.exists())
		{
			XmlTool tool=new XmlTool(path);
			ArrayList<AndroidCheckdataBuffer> lists=tool.readCheckData();//��ȡAndroid���͵�����
			UnCheckList unCheckList=new UnCheckList();
			for(AndroidCheckdataBuffer data:lists)
				if(unCheckList.inUnCheckedList(data.getLb(), data.getXb(),data.getCheckListId()))//�ж�Android���͵�checkdata�Ƿ���δ���յ�С����
					data.insertOrUpdate();
			AndroidCheckdataBuffer.scanBuffer();//ɨ�軺�������Ƿ��о���һ�������ܸ���CheckList
			file.delete();//ɾ��xml�ļ�����ɸ���
		}
	}
}
