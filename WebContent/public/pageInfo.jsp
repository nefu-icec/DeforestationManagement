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

��<%=itemCount%>����¼����ǰ��<%=pageNumber%>/<%=pageCount%>ҳ��ÿҳ<%=number%>����¼	
<a href="<%=pageName%>?number=<%=number%>&pageNumber=<%=previous%>">��һҳ</a>
<a href="<%=pageName%>?number=<%=number%>&pageNumber=<%=next%>">��һҳ</a>
��ת����
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
ҳ��
ÿҳ��ʾ
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
��

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