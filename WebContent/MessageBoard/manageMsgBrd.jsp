<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.Answer"%>
<%@page import="edu.nefu.DeforeMgr.bean.Question"%>
<%@page import="java.util.ArrayList"%>

����id�� ���� ���� ʱ�� �ش� ɾ������<br>
<%
ArrayList<Question> questionList=Question.getQuestion(null);
for(int i=0;i<questionList.size();i++)
{
	%>
	<%=questionList.get(i).getQid()%>
	<a href="manageAnswer.jsp?qid=<%=questionList.get(i).getQid()%>"><%=questionList.get(i).getQuestion()%></a> 
	<%=questionList.get(i).getqAuthor()%> 
	<%=questionList.get(i).getqDate().getStringDate()%> 
	<a href="../MessageBoardServlet?task=deleteQuestion&qid=<%=questionList.get(i).getQid()%>">ɾ��</a>
	<br>
	<%
}
%>

<a href="../public/login.jsp">������ҳ</a>