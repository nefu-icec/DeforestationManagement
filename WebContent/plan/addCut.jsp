<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>
<%
String id=request.getParameter("id");
ArrayList<Integer> cutList=new ArrayList<Integer>();

if(session.getAttribute("cutList")==null)
	session.setAttribute("cutList", cutList);
else
	cutList=(ArrayList<Integer>)session.getAttribute("cutList");
//判断所选小班id是否在session中
boolean flag=false;
for(int i=0;i<cutList.size();i++)
	if(cutList.get(i)==Integer.parseInt(id))
	{
		flag=true;
		break;
	}
if(flag==true)
	out.print("已存在所选id号："+id+"，请不要重复添加<br>");
else
{
	cutList.add(Integer.parseInt(id));
	out.print("id号为"+id+"的小班已添加<br>");
}
session.setAttribute("cutList", cutList);
response.setHeader("refresh","1;url=cutList.jsp") ;
%>