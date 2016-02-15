<%@page import="edu.nefu.DeforeMgr.util.Page"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>

<%
	Page p=(Page)session.getAttribute("page");
	int itemCount=p.getItemCount();
	int pageNumber=p.getPageNumber();
	int pageCount=p.getPageCount();
	int number=p.getNumber();
	int previous=p.getPrevious();
	int next=p.getNext();
	String pageName=p.getPageName();
%>

共<%=itemCount%>条记录，当前第<%=pageNumber%>/<%=pageCount%>页，每页<%=number%>条纪录	
<a href="<%=pageName%>?number=<%=number%>&pageNumber=<%=previous%>">上一页</a>
<a href="<%=pageName%>?number=<%=number%>&pageNumber=<%=next%>">下一页</a>
跳转到第
<select name="goto" id="goto" onchange="goTo()">
	<%
		for(int i=1;i<=pageCount;i++)
		{
	%>
		<option value="<%=i%>" <%if(i==pageNumber) out.print("selected");%>><%=i%></option>
	<%
		}
	%>
</select>
页，
每页显示
<select name="number" id="number" onchange="number()">
	<%
		for(int i=5;i<=15;i++)
		{
	%>
		<option value="<%=i%>" <%if(i==number) out.print("selected");%>><%=i%></option>
	<%
		}
	%>
</select>
个

<script>
	function goTo()
	{
		var goPageNumber=document.getElementById("goto");
		window.location.href="<%=pageName%>?number=<%=number%>&pageNumber="+goPageNumber.value;
	}
	function number()
	{
		var number=document.getElementById("number");
		window.location.href="<%=pageName%>?number="+number.value;
	}
</script>