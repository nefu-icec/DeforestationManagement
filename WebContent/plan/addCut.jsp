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
//�ж���ѡС��id�Ƿ���session��
boolean flag=false;
for(int i=0;i<cutList.size();i++)
	if(cutList.get(i)==Integer.parseInt(id))
	{
		flag=true;
		break;
	}
if(flag==true)
	out.print("�Ѵ�����ѡid�ţ�"+id+"���벻Ҫ�ظ����<br>");
else
{
	cutList.add(Integer.parseInt(id));
	out.print("id��Ϊ"+id+"��С�������<br>");
}
session.setAttribute("cutList", cutList);
response.setHeader("refresh","1;url=cutList.jsp") ;
%>