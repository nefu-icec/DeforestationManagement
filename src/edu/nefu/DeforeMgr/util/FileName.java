package edu.nefu.DeforeMgr.util;

import java.io.File;

public class FileName 
{
	public static int modifyFileName(String path,String fileName,String newFileName)
	{
		File oldFile = new File(path+fileName);
		String rootPath = oldFile.getParent();
		File newFile = new File(rootPath + File.separator + newFileName);
		if (oldFile.renameTo(newFile)) 
			return 1;
		else 
			return 0;
	}
	
	public static String getFormat(String fileName)
	{
		int index = fileName.lastIndexOf(".");
	    String format = fileName.substring(index + 1);
		return format;
	}
	
	public static void main(String[] args) 
	{
		System.out.println(getFormat("aa.aa.s-cc.txt"));
	}
}