package edu.nefu.DeforeMgr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 读文件
 * @author lm2343635
 *
 */
public class ReadFromFile
{
	/**
	 *  以字符为单位读取文件，常用于读文本，数字等类型的文件
	 * @param fileName 文件名
	 * @return 文件内容字符串
	 */
    public static String readFileByChars(String fileName) 
    {
    	String s="";
        File file = new File(fileName);
        Reader reader = null;
        try 
        {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) 
                if (((char) tempchar) != '\r') 
                    s+=(char) tempchar;
            reader.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return s;
    }

    public static void main(String[] args) 
    {
        String fileName = "D:/1.xml";
        System.out.println(ReadFromFile.readFileByChars(fileName));
    }
}