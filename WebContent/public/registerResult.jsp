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
							out.print("ע��ɹ����¼�����μ������û�id��"+uid);					
						else
							out.print("ע��ʧ��");
					%>
				</p>
				<p><a href="../index.html">������ҳ</a></p>		
			</div>
		</div>
	</div>
