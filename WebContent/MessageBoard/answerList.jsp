<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.Answer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nefu.DeforeMgr.bean.Question"%>

<%
String sqid=request.getParameter("qid");
int qid=Integer.parseInt(sqid);
Question question=Question.getQuestionById(qid);
%>

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="../MessageBoard/MsgBrdList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p><%=question.getQuestion()%> </p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>
							<%=question.getqAuthor()%>
							������<%=question.getqDate().getYear()%>��<%=question.getqDate().getMonth()%>��<%=question.getqDate().getDay()%>��
							¥��
						</th>
					</tr>
					<%
						ArrayList<Answer> answers=Answer.getAnswerByQid(qid);
						for(int i=0;i<answers.size();i++)
						{
					%>
					<tr <%if(i%2==0) out.print("class='alt'");%>>
						<td>
							<%=answers.get(i).getaAuthor()%>������
							<%=answers.get(i).getaDate().getYear()%>��
							<%=answers.get(i).getaDate().getMonth()%>��
							<%=answers.get(i).getaDate().getDay()%>��
							<%
							if(i==0) out.print("ɳ��");
							else if(i==1) out.print("���");
							else if(i==2) out.print("�ذ�");
							else out.print((i+2)+"¥");
							%>
						</td>
						</tr>
						<tr>
							<td><%=answers.get(i).getAnswer()%></td>
						</tr>
							<%
								}
							%>
							<form action="../MessageBoardServlet?task=answer&qid=<%=question.getQid()%>" method="post">
								<tr>
									<td>�ش�����</td>
								</tr>
								<tr>
									<td><textarea name="answer" rows="7" cols="100">�ڴ���ش�</textarea></td>
								</tr>
								<tr>
									<td><input type="submit" value="�ش�"></td>
								</tr>
								<tr>
									<td><input type="reset" value="��д"></td>
								</tr>
								<tr>
									<td><a href="questionList.jsp">����</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="../public/login.jsp">������ҳ</a></td>
								</tr>
						</form>
				</table>
			</div>
		</div>
	</div>
</div>
<div><jsp:include page="../public/foot.jsp"></jsp:include></div>
