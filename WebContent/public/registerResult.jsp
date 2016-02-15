<%@ page language="java" contentType="text/html; charset=gb2312"%>


<link rel="stylesheet" href="../css/register.css">
	<div style="background:url(../images/topbg.jpg) top repeat-x; margin:0;">
		<img src="../images/head.jpg" class="image"><br>
		<div id="wapper">
			<div class="infomation">
				<p>
					<%
						String success=request.getParameter("success");
						String uid=request.getParameter("uid");
						if(success.equals("true"))
							out.print("注册成功请登录，请牢记您的用户id："+uid);					
						else
							out.print("注册失败");
					%>
				</p>
				<p><a href="../index.html">返回首页</a></p>		
			</div>
		</div>
	</div>
