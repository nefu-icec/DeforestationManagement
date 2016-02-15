<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.Answer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nefu.DeforeMgr.bean.Question"%>

<%
String sqid=request.getParameter("qid");
int qid=Integer.parseInt(sqid);
Question question=Question.getQuestionById(qid);
%>
<%=question.getQuestion()%> 
<%=question.getqAuthor()%>
发表于<%=question.getqDate().getYear()%>年<%=question.getqDate().getMonth()%>月<%=question.getqDate().getDay()%>日
楼主<br><br>

<%
ArrayList<Answer> answers=Answer.getAnswerByQid(qid);
for(int i=0;i<answers.size();i++)
{
	%>
	<%=answers.get(i).getAid()%> 
	<%=answers.get(i).getaAuthor()%>
	发表于
	<%=answers.get(i).getaDate().getYear()%>年
	<%=answers.get(i).getaDate().getMonth()%>月
	<%=answers.get(i).getaDate().getDay()%>日
	<%
	if(i==0) out.print("沙发");
	else if(i==1) out.print("板凳");
	else if(i==2) out.print("地板");
	else out.print((i+2)+"楼");
	%>
	<a href="../MessageBoardServlet?task=deleteAnswer&aid=<%=answers.get(i).getAid()%>&qid=<%=qid%>">删除</a>
	<br>
	<%=answers.get(i).getAnswer()%>
	<br><br>
	<% 
}
%>

<a href="manageMsgBrd.jsp">返回</a>
<a href="../public/login.jsp">返回主页</a>