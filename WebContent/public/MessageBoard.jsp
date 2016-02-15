<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.Question"%>
<%@page import="java.util.ArrayList"%>

<%
	ArrayList<Question> questionList=Question.getShowQuestion(6);
%>


<div id="news" >
<table id="table" style="width: 500px;">
	<%
		for(int i=0;i<questionList.size();i++)
		{
	%>
	<tr <%if(i%2==0) out.print("class='alt'");%>>
		<td>
			<a href="../MessageBoard/answerList.jsp?qid=<%=questionList.get(i).getQid()%>">
				<%
					if(questionList.get(i).getQuestion().length()>20)
						out.print(questionList.get(i).getQuestion().substring(0, 20)+"...");
					else
						out.print(questionList.get(i).getQuestion());
				%>
			</a> 
		</td>
		<td><%=questionList.get(i).getqDate().getStringDate()%></td>
		<td><%=questionList.get(i).getqAuthor()%></td>
	</tr>
	<%
		}
	%>
</table>
<a href="../MessageBoard/question.jsp" style="position:absolute; right:60px; bottom: 40px;">我要提问</a>
</div>
