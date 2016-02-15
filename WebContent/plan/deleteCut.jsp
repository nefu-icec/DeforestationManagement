<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<%
String deleteId=request.getParameter("deleteId");
ArrayList<Integer> cutList=(ArrayList<Integer>)session.getAttribute("cutList");
for(int i=0;i<cutList.size();i++)
	if(cutList.get(i)==Integer.parseInt(deleteId))
	{
		cutList.remove(i);
		break;
	}
session.setAttribute("cutLis", cutList);
response.sendRedirect("cutList.jsp");
%>