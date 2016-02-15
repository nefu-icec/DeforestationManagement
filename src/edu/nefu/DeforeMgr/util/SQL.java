package edu.nefu.DeforeMgr.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.nefu.DeforeMgr.bean.CheckList;
import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.CutList;

public class SQL 
{
	public static void updateByTXT(String path,String fileName) throws ClassNotFoundException, SQLException
	{
		String filePath=path+fileName;
		TextReader reader=new TextReader(filePath);
		ConnectDatabase connectDatabase=new ConnectDatabase();
		List<String> res=reader.readTxt();
		for(int i=0;i<res.size();i++)
			connectDatabase.exeUpdate(res.get(i));
	}
	
	/**
	 * 通过文本导入数据，生成更新的update语句
	 * @param path 文本在服务器上的路径
	 * @param fileName 文本文件名称
	 * @param checkListId 验收计划id号
	 * @return 更新的update语句
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<String> createUpdate(String path,String fileName,int checkListId) throws ClassNotFoundException, SQLException
	{
		TextReader reader=new TextReader(path+fileName);
		ArrayList<LB_XB> lbxbs=reader.getLbxbs();
		CheckList checkList=CheckList.getCheckListById(checkListId);
		ArrayList<LB_XB> checkLbxbs=CutList.getListLBXB(checkList.getList());
		ArrayList<LB_XB> updateLbxbs=new ArrayList<LB_XB>();
		for(int i=0;i<lbxbs.size();i++)
		{
			boolean flag=false;
			for(int j=0;j<checkLbxbs.size();j++)
				if(lbxbs.get(i).getLb()==checkLbxbs.get(j).getLb()&&lbxbs.get(i).getXb()==checkLbxbs.get(j).getXb())
					flag=true;
			if(flag==true)
				updateLbxbs.add(lbxbs.get(i));
		}	
		reader=new TextReader(path+fileName);
		ArrayList<String> updates=reader.readByLBXB(updateLbxbs);
		for(int i=0;i<updates.size();i++)
			System.out.println(updates.get(i));
		return updates;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		createUpdate("d:/", "test.txt",11);
	}
}
