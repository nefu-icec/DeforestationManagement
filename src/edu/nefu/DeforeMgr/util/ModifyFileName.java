package edu.nefu.DeforeMgr.util;

import java.io.File;
import java.util.ArrayList;

public class ModifyFileName
{
	
	public static String getFormat(String fileName)
	{
		int index = fileName.lastIndexOf(".");
	    String format = fileName.substring(index+1);
		return format;
	}
	
	public static ArrayList<File> getFiles(String path,String format)
	{
		File dir=new File(path);
		File[] files=dir.listFiles();
		ArrayList<File> fileList=new ArrayList<File>();
		for(int i=0;i<files.length;i++)
			if(format.equals(getFormat(files[i].getName())))
				fileList.add(files[i]);
		return fileList;
	}
	
	public static boolean modifyFileName(String path,String fileName,String newFileName)
	{
		File oldFile = new File(path+fileName);
		String rootPath = oldFile.getParent();
		File newFile = new File(rootPath + File.separator + newFileName);
		if (oldFile.renameTo(newFile)) 
			return true;
		else 
			return false;
	}
	
	public static int modifyFiles(String path,String format,String fileName,int start)
	{
		ArrayList<File> files=getFiles(path,format);
		for(int i=0;i<files.size();i++)
			modifyFileName(path,files.get(i).getName(),fileName+(start+i)+"."+format);
		return files.size();
	}
	
	public static void main(String[] args) 
	{
		modifyFileName("D:/新建文件夹/", "HunanTV.Lu.Zhen.Chuan.Qi.Ep21.HDTV.720p.x264-CHDTV.mkv", "陆贞传奇21.mkv");
	}
	
	
}
