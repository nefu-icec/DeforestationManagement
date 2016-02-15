package edu.nefu.DeforeMgr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * ���ļ�
 * @author lm2343635
 *
 */
public class ReadFromFile
{
	/**
	 *  ���ַ�Ϊ��λ��ȡ�ļ��������ڶ��ı������ֵ����͵��ļ�
	 * @param fileName �ļ���
	 * @return �ļ������ַ���
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