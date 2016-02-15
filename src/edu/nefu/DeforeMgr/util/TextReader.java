package edu.nefu.DeforeMgr.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextReader 
{
	public static final String ar=".sql";
	public static final String tbName="checkdata";
	private FileReader fr=null;
	private FileWriter fw=null;
	private BufferedReader br=null;
	private BufferedWriter bw=null;
	private String textLine=null;
	private String path=null;
	private ArrayList<String> result=null;
	
	public TextReader(String filePath) {
		// TODO Auto-generated constructor stub
		try {
			fr=new FileReader(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br=new BufferedReader(fr);
		this.path=filePath;
		result=new ArrayList<String>();
	}
	
	public ArrayList<String> readTxt()
	{
		String lb="",xb="";
		try 
		{
			if((textLine=br.readLine())!=null){
				String[] titles=textLine.split(",");
				while((textLine=br.readLine())!=null){
					String[] values=textLine.split(",");
					if(titles.length==values.length){
						String tsql="update "+tbName+" set ";
						for(int i=0;i<values.length;i++){
							if(titles[i].equals("lb")){
								lb=values[i];
							}
							else if(titles[i].equals("xb")){
								xb=values[i];
							}
							else{
								tsql=tsql+titles[i]+"='"+values[i]+"',";
							}
						}
						tsql=tsql.substring(0, tsql.length()-1);
						tsql=tsql+" where lb="+lb+" and xb="+xb+"";
						result.add(tsql);
					}
				}
				String temp=path;
				String a=path.substring(path.length()-4, path.length());
				path=path.replace(a, ar);
				System.out.println(path);
				writeSQL(path, result);
				path=temp;
			}
			else{
				System.out.println("文件内容有误");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> readByLBXB(ArrayList<LB_XB> lbxbs)
	{
		String lb="",xb="";
		try 
		{
			if((textLine=br.readLine())!=null)
			{
				String[] titles=textLine.split(",");
				while((textLine=br.readLine())!=null)
				{
					String[] values=textLine.split(",");
					if(titles.length==values.length)
					{
						String tsql="update "+tbName+" set ";
						for(int i=0;i<values.length;i++)
						{
							if(titles[i].equals("lb"))
								lb=values[i];
							else if(titles[i].equals("xb"))
								xb=values[i];
							else
								tsql=tsql+titles[i]+"='"+values[i]+"',";
						}
						tsql=tsql+"checkDate='"+MyDate.getNowDate()+"' where lb="+lb+" and xb="+xb+"";
						//判断是否在验收列表中
						boolean flag=false;
						for(int i=0;i<lbxbs.size();i++)
							if(lbxbs.get(i).getLb()==Integer.parseInt(lb)&&lbxbs.get(i).getXb()==Integer.parseInt(xb))
								flag=true;
						if(flag==true)
							result.add(tsql);
					}
				}
			}
			else{
				System.out.println("文件内容有误");
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<LB_XB> getLbxbs()
	{
		ArrayList<LB_XB> lbxbs=new ArrayList<LB_XB>();
		String lb="",xb="";
		try 
		{
			if((textLine=br.readLine())!=null)
			{
				String[] titles=textLine.split(",");
				while((textLine=br.readLine())!=null)
				{
					String[] values=textLine.split(",");
					if(titles.length==values.length)
					{
						for(int i=0;i<values.length;i++)
						{
							if(titles[i].equals("lb"))
								lb=values[i];
							else if(titles[i].equals("xb"))
								xb=values[i];
						}
					}
					lbxbs.add(new LB_XB(Integer.parseInt(lb), Integer.parseInt(xb)));
				}
			}
			else
				System.out.println("文件内容有误");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return lbxbs;
	}
	
	public void writeSQL(String path, List<String> data){
		File file=new File(path);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fw=new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bw=new BufferedWriter(fw);
		try {
			for(int i=0;i<data.size();i++){
				bw.write(data.get(i));
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeTxtReader(){
		if(fr!=null){
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(fw!=null){
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(br!=null){
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(bw!=null){
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		TextReader reader=new TextReader("D:\\test.txt");
		List<String> res=reader.readTxt();
		for(int i=0;i<res.size();i++)
			System.out.println(res.get(i));
	}

}
