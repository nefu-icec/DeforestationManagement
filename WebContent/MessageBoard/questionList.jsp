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
				》》 您的位置：
				<a href="../public/login.jsp">主页</a> 》
				</p>
			</div>
			<div class="content">
				<div class="name">
					<p>留言板</p>
				</div>
				<div class="tablecontent">
					<table id="table">
						<tr>
							<th>问题</th>
							<th>作者</th>
							<th>时间</th>
							<th>回答</th>
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
							<td><a href="../MessageBoard/question.jsp">提问</a></td>
							<td><a href="../public/login.jsp">返回主页</a></td>
							<td></td>	
							<td></td>
						</tr>
					</table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>