package edu.nefu.DeforeMgr.util;

import java.sql.SQLException;

import java.util.ArrayList;

import edu.nefu.DeforeMgr.bean.CheckData;
import edu.nefu.DeforeMgr.bean.CheckList;
import edu.nefu.DeforeMgr.bean.CutList;
import edu.nefu.DeforeMgr.bean.News;

/**
 * 页码类
 * @author lm2343635
 *  添加新的打表类：
  1、添加类型指示静态对象		eg：public static int CHECKDATA=0;
  2、设置set、get方法
  3、setPageCountAndItemCount、setStartAndEnd方法中添加case
  使用指南：
  1、使用构造方法Page(String pageName, int pageNumber, int number, int arrayType)初始化
  2、设置页面跳转参数
	 if(request.getParameter("pageNumber")!=null)
	 	p.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
	 if(request.getParameter("number")!=null)
	 	p.setNumber(Integer.parseInt(request.getParameter("number")));
  3、设置打表对象		eg：p.setCheckDatas(checkDatas);
  4、计算		eg：p.calculate();
  5、设置session传值给pageInfo.jsp		eg：session.setAttribute("page", p);
  6、设置打表循环中start和end值		eg： for(int i=p.getStart();i<p.getEnd();i++)
  7、打表完成后调用pageInfo.jsp：<jsp:include page='../public/pageInfo.jsp'></jsp:include>
 */
public class Page 
{
	private String pageName;
	private int pageNumber;
	private int number;
	private int arrayType;
	private int itemCount;
	private int pageCount;
	private int start;
	private int end;
	private int previous;
	private int next;
	
	private ArrayList<CheckData> checkDatas;
	private ArrayList<CutList> cutLists;
	private ArrayList<CheckList> checkLists;
	private ArrayList<News> newsList;
	
	public static int CHECKDATA=0;
	public static int CUTLIST=1;
	public static int CHECKLIST=2;
	public static int NEWS=3;
	
	public ArrayList<CheckData> getCheckDatas() {
		return checkDatas;
	}
    
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPrevious() {
		return previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public void setCheckDatas(ArrayList<CheckData> checkDatas) {
		this.checkDatas = checkDatas;
	}

	public ArrayList<CutList> getCutLists() {
		return cutLists;
	}

	public void setCutLists(ArrayList<CutList> cutLists) {
		this.cutLists = cutLists;
	}

	public ArrayList<CheckList> getCheckLists() {
		return checkLists;
	}

	public void setCheckLists(ArrayList<CheckList> checkLists) {
		this.checkLists = checkLists;
	}

	public ArrayList<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(ArrayList<News> newsList) {
		this.newsList = newsList;
	}

	public Page(String pageName, int pageNumber, int number, int arrayType) 
	{
		super();
		this.pageName = pageName;
		this.pageNumber = pageNumber;
		this.number = number;
		this.arrayType = arrayType;
	}
	
	public void calculate()
	{
		setPageCountAndItemCount();
		setPreviousAndNext();
		setStartAndEnd();
	}

	private void setPageCountAndItemCount()
	{
		switch (arrayType) 
		{
		case 0:
			itemCount=checkDatas.size();
			pageCount=checkDatas.size()/number+1;
			if(checkDatas.size()%number==0)
				pageCount--;
			break;
		case 1:
			itemCount=cutLists.size();
			pageCount=cutLists.size()/number+1;
			if(cutLists.size()%number==0)
				pageCount--;
			break;
		case 2:
			itemCount=checkLists.size();
			pageCount=checkLists.size()/number+1;
			if(checkLists.size()%number==0)
				pageCount--;
			break;
		case 3:
			itemCount=newsList.size();
			pageCount=newsList.size()/number+1;
			if(newsList.size()%number==0)
				pageCount--;
			break;
		default:
			break;
		}
	}
	
	private void setStartAndEnd()
	{
		start=number*(pageNumber-1);
		end=number*(pageNumber-1)+number;
		switch (arrayType) 
		{
		case 0:
			if(end+1>checkDatas.size())//打表结尾超过总数，是结尾等于总数
				end=checkDatas.size();
			break;
		case 1:
			if(end+1>cutLists.size())
				end=cutLists.size();
			break;
		case 2:
			if(end+1>checkLists.size())
				end=checkLists.size();
			break;
		case 3:
			if(end+1>newsList.size())
				end=newsList.size();
			break;
		default:
			break;
		}
	}
	
	private void setPreviousAndNext()
	{
		previous=pageNumber-1;
		next=pageNumber+1;
		if(previous<1)
			previous=pageCount;
		if(next>pageCount)
			next=1;
	}
	
	public String getPageInfoLink()
	{
		String link="<jsp:include page='../public/pageInfo.jsp'></jsp:include>";
		return link;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Page page=new Page("issueCutPlan.jsp",1, 10,CHECKDATA);
		ArrayList<CheckData> checkDatas=CheckData.getCheckDatas("");
		page.setCheckDatas(checkDatas);
		page.calculate();
		System.out.println(page.pageCount);
		System.out.println(page.itemCount);
		System.out.println(page.previous);
		System.out.println(page.next);
		System.out.println(page.start);
		System.out.println(page.end);
		System.out.println(page.getPageInfoLink());
	}
}
