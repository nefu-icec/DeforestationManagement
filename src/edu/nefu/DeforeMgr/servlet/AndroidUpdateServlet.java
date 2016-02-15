package edu.nefu.DeforeMgr.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

import edu.nefu.DeforeMgr.bean.AndroidUpdate;
import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.util.CopyFiles;
import edu.nefu.DeforeMgr.util.ModifyFileName;
import edu.nefu.DeforeMgr.util.MyDate;

@WebServlet("/AndroidUpdateServlet")
public class AndroidUpdateServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	private String task;
       
    public AndroidUpdateServlet() 
    {
        super();
    }
    
	final public void init(ServletConfig config) throws ServletException 
	{
		this.config = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameter("task")!=null)
		{
			task=request.getParameter("task");
			switch (task)
			{
			case "downloadApk":
				downloadApk(request,response);
				break;
			case "delete":
				delete(request,response);
				break;
			default:
				break;
			}
		}
		else
			searchByDate(request,response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
	{
		String version=request.getParameter("version");
		try
		{
			//删除文件
			String apkName=AndroidUpdate.getNameByVersion(Integer.parseInt(version));
			String path=config.getServletContext().getRealPath("/");
			File file=new File(path+"apk/"+apkName);
			if(file.exists())
				file.delete();
			//删除数据库记录
			String delete="delete from androidupdate where version="+version;
			ConnectDatabase connectDatabase = new ConnectDatabase();
			connectDatabase.exeUpdate(delete);
			connectDatabase.close();
			response.sendRedirect("AndroidUpdate/checkHistory.jsp");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private void downloadApk(HttpServletRequest request,HttpServletResponse response) 
	{
		int version=Integer.parseInt(request.getParameter("version"));
		String apkName;
		try 
		{
			apkName = AndroidUpdate.getNameByVersion(version);
			response.sendRedirect("AndroidUpdate/download.jsp?fileName="+apkName);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "uploadApk":
			uploadApk(request,response);
			break;
		default:
			break;
		}
	}

	private void uploadApk(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String apkName=upload(request, response, "/apk/");
		String updateTime=MyDate.getNowTime();
		String path=config.getServletContext().getRealPath("/");
		String newName=updateTime+apkName;
		ModifyFileName.modifyFileName(path+"apk/", apkName,newName);
		try 
		{
			new AndroidUpdate(newName, updateTime).insert();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("上传失败");
		}
		java.io.File sourceFile=new java.io.File(path+"/apk/"+newName);
		java.io.File targetFile=new java.io.File(path+"/apk/DeforestationManagementNewest.apk");
		if(targetFile.exists())
			targetFile.delete();
		if(sourceFile.exists())
			CopyFiles.copyFile(sourceFile, targetFile);
		response.sendRedirect("AndroidUpdate/checkHistory.jsp");
	}


	private void searchByDate(HttpServletRequest request,HttpServletResponse response) 
	{
		String startYear=request.getParameter("startYear");
		String startMonth =request.getParameter("startMonth");
		String startDay=request.getParameter("startDay");
		String endYear=request.getParameter("endYear");
		String endMonth=request.getParameter("endMonth");
		String endDay=request.getParameter("endDay");
		String startDate=startYear+"-"+startMonth+"-"+startDay+" 00:00:00";
		String endDate=endYear+"-"+endMonth+"-"+endDay+" 23:59:59";
		ArrayList<AndroidUpdate> updateList;
		try 
		{
			updateList = AndroidUpdate.getAndroidUpdateByDate(startDate, endDate);
			request.getSession().setAttribute("updateList", updateList);
			response.sendRedirect("AndroidUpdate/checkHistory.jsp?searchReturn=true");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}

	private String upload(HttpServletRequest request,HttpServletResponse response,String path) 
	{
		int count=0;
		String fileName="";
		SmartUpload mySmartUpload = new SmartUpload();
		try 
		{
			mySmartUpload.initialize(config,request,response);
			mySmartUpload.upload();
			count=mySmartUpload.save(path);
			fileName=mySmartUpload.getFiles().getFile(0).getFileName();
			System.out.println(count + " success "+fileName);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return fileName;
	}
}
