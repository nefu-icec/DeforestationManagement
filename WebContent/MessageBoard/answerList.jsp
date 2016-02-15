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
			》》 您的位置：
			<a href="../public/login.jsp">主页</a> 》
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
							发表于<%=question.getqDate().getYear()%>年<%=question.getqDate().getMonth()%>月<%=question.getqDate().getDay()%>日
							楼主
						</th>
					</tr>
					<%
						ArrayList<Answer> answers=Answer.getAnswerByQid(qid);
						for(int i=0;i<answers.size();i++)
						{
					%>
					<tr <%if(i%2==0) out.print("class='alt'");%>>
						<td>
							<%=answers.get(i).getaAuthor()%>发表于
							<%=answers.get(i).getaDate().getYear()%>年
							<%=answers.get(i).getaDate().getMonth()%>月
							<%=answers.get(i).getaDate().getDay()%>日
							<%
							if(i==0) out.print("沙发");
							else if(i==1) out.print("板凳");
							else if(i==2) out.print("地板");
							else out.print((i+2)+"楼");
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
									<td>回答问题</td>
								</tr>
								<tr>
									<td><textarea name="answer" rows="7" cols="100">在此填回答</textarea></td>
								</tr>
								<tr>
									<td><input type="submit" value="回答"></td>
								</tr>
								<tr>
									<td><input type="reset" value="重写"></td>
								</tr>
								<tr>
									<td><a href="questionList.jsp">返回</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="../public/login.jsp">返回主页</a></td>
								</tr>
						</form>
				</table>
			</div>
		</div>
	</div>
</div>
<div><jsp:include page="../public/foot.jsp"></jsp:include></div>
