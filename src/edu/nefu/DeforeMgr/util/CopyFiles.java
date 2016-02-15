package edu.nefu.DeforeMgr.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 文件批量复制
 * @author lm2343635
 *
 */
public class CopyFiles 
{
	private String oldPath;
	private String newPath;
	private ArrayList<String> fileList;
	
	public CopyFiles(String oldPath, String newPath, ArrayList<String> fileList)
	{
		super();
		this.oldPath = oldPath;
		this.newPath = newPath;
		this.fileList = fileList;
	}
	
	/**
	 * 判断文件是否在要求复制的列表中
	 * @param file 文件
	 * @return
	 */
	private boolean inFileList(File file)
	{
		for(String name:fileList)
			if(file.getName().equals(name))
				return true;
		return false;
	}

	/**
	 * 复制文件
	 */
	public void copy()
	{
		File [] sourceFile=(new File(oldPath)).listFiles();
		if(!new File(newPath).exists())
			(new File(newPath)).mkdirs();
		for (int i=0;i<sourceFile.length;i++) 
		{
			if(inFileList(sourceFile[i]))
				try 
				{
					copyFile(sourceFile[i],new File(newPath+"/"+sourceFile[i].getName()));
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 复制文件从源文件到目标文件
	 * @param sourceFile 源文件
	 * @param targetFile 目标文件
	 * @throws IOException
	 */
	 public static void copyFile(File sourceFile, File targetFile) throws IOException
	 {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try 
        {
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) 
                outBuff.write(b, 0, len);
            outBuff.flush();
        } 
        finally 
        {
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

}
