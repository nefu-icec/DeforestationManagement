<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.Answer"%>
<%@page import="edu.nefu.DeforeMgr.bean.Question"%>
<%@page import="java.util.ArrayList"%>

问题id号 问题 作者 时间 回答 删除问题<br>
<%
ArrayList<Question> questionList=Question.getQuestion(null);
for(int i=0;i<questionList.size();i++)
{
	%>
	<%=questionList.get(i).getQid()%>
	<a href="manageAnswer.jsp?qid=<%=questionList.get(i).getQid()%>"><%=questionList.get(i).getQuestion()%></a> 
	<%=questionList.get(i).getqAuthor()%> 
	<%=questionList.get(i).getqDate().getStringDate()%> 
	<a href="../MessageBoardServlet?task=deleteQuestion&qid=<%=questionList.get(i).getQid()%>">删除</a>
	<br>
	<%
}
%>

<a href="../public/login.jsp">返回主页</a>