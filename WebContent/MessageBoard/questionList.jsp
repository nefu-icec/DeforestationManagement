<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.Question"%>
<%@page import="java.util.ArrayList"%>

<%
ArrayList<Question> questionList=Question.getQuestion(null);
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
					<p>���԰�</p>
				</div>
				<div class="tablecontent">
					<table id="table">
						<tr>
							<th>����</th>
							<th>����</th>
							<th>ʱ��</th>
							<th>�ش�</th>
						</tr>
							<%
								for(int i=0;i<questionList.size();i++)
								{
							%>
						    <tr <%if(i%2==0) out.print("class='alt'");%>>
							<td><a href="answerList.jsp?qid=<%=questionList.get(i).getQid()%>"><%=questionList.get(i).getQuestion()%></a> </td>
							<td><%=questionList.get(i).getqAuthor()%> </td>
							<td><%=questionList.get(i).getqDate().getStringDate()%></td>
							<td></td>
						</tr>
							<%
						}
						%>
						<tr>
							<td><a href="../MessageBoard/question.jsp">����</a></td>
							<td><a href="../public/login.jsp">������ҳ</a></td>
							<td></td>	
							<td></td>
						</tr>
					</table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>