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
������<%=question.getqDate().getYear()%>��<%=question.getqDate().getMonth()%>��<%=question.getqDate().getDay()%>��
¥��<br><br>

<%
ArrayList<Answer> answers=Answer.getAnswerByQid(qid);
for(int i=0;i<answers.size();i++)
{
	%>
	<%=answers.get(i).getAid()%> 
	<%=answers.get(i).getaAuthor()%>
	������
	<%=answers.get(i).getaDate().getYear()%>��
	<%=answers.get(i).getaDate().getMonth()%>��
	<%=answers.get(i).getaDate().getDay()%>��
	<%
	if(i==0) out.print("ɳ��");
	else if(i==1) out.print("���");
	else if(i==2) out.print("�ذ�");
	else out.print((i+2)+"¥");
	%>
	<a href="../MessageBoardServlet?task=deleteAnswer&aid=<%=answers.get(i).getAid()%>&qid=<%=qid%>">ɾ��</a>
	<br>
	<%=answers.get(i).getAnswer()%>
	<br><br>
	<% 
}
%>

<a href="manageMsgBrd.jsp">����</a>
<a href="../public/login.jsp">������ҳ</a>