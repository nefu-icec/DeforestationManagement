<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
 <jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />

<html>
	<head>
		<link rel="stylesheet" rev="stylesheet" href="../css/tab.css" type="text/css">
		<script type="text/javascript" src="../javascript/tab.js"></script>
	</head>
	<body>
	<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td height="30">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30" /></td>
		        <td width="1101" background="../images/tab_05.gif"><img src="images/311.gif" width="16" height="16" /> 
		        	<span class="STYLE4">管理滚动图片</span>
		        </td>
		        <td width="281" background="../images/tab_05.gif">
			        <table border="0" align="right" cellpadding="0" cellspacing="0">
			            <tr>
			            </tr>
			        </table>
		        </td>
		        <td width="14"><img src="../images/tab_07.gif" width="14" height="30" /></td>
		      </tr>
		    </table>
	    </td>
	  </tr>
	  <tr>
	    <td>
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
		        <td bgcolor="#f3ffe3">
			        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"  onmouseout="changeback()">
				          <tr>
				            
				            <td width="25%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">发布时间</div></td>
				            <td width="12.5%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">文件名</div></td>
				            <td width="50%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">预览</div></td>
				            <td width="12.5%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">管理</div></td>
				          </tr>
				          <%
				          ResultSet rs1=connectDatabase.exeQuery("select itime,ifile from newsImg order by itime desc");
				          while(rs1.next())
				          {%>
				          <tr>
					          
					            <td height="50" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=rs1.getString("itime")%></div></td>
					            <td height="50" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=rs1.getString("ifile")%></div></td>
					            <td height="50" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><img style="margin: 22px;" width="268" height="190" src="../newsImg/<%=rs1.getString("iFile") %>"/></div></td>
					            <td height="50" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><a href="deleteNewsImg.jsp?iTime=<%=rs1.getString("iTime")%>&iFile=<%=rs1.getString("iFile") %>">删除</a></div></td>
					            
			               </tr>
			               <%
				            }
			               %>
			        </table>
		        </td>
		        <td width="9" background="../images/tab_16.gif">&nbsp;</td>
		      </tr>
		    </table>
	    </td>
	  </tr>
	  <tr>
	    <td height="29">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="15" height="29"><img src="../images/tab_20.gif" width="15" height="29" /></td>
		        <td background="../images/tab_21.gif">
			        <table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td width="25%" height="29" nowrap="nowrap"><span class="STYLE1">共120条纪录，当前第1/10页，每页10条纪录</span></td>
			            <td width="75%" valign="top" class="STYLE1">
				            <div align="right">
					              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
					                <tr>
					                  <td width="62" height="22" valign="middle"><div align="right"><img src="../images/first.gif" width="37" height="15" /></div></td>
					                  <td width="50" height="22" valign="middle"><div align="right"><img src="../images/back.gif" width="43" height="15" /></div></td>
					                  <td width="54" height="22" valign="middle"><div align="right"><img src="../images/next.gif" width="43" height="15" /></div></td>
					                  <td width="49" height="22" valign="middle"><div align="right"><img src="../images/last.gif" width="37" height="15" /></div></td>
					                  <td width="59" height="22" valign="middle"><div align="right">转到第</div></td>
					                  <td width="25" height="22" valign="middle">
						                  <span class="STYLE7">
						                    <input name="textfield" type="text" class="STYLE1" style="height:10px; width:25px;" size="5" />
						                  </span>
					                  </td>
					                  <td width="23" height="22" valign="middle">页</td>
					                  <td width="30" height="22" valign="middle"><img src="../images/go.gif" width="37" height="15" /></td>
					                </tr>
					              </table>
				            </div>
			            </td>
			          </tr>
			        </table>
		        </td>
		        <td width="14"><img src="../images/tab_22.gif" width="14" height="29" /></td>
		      </tr>
		    </table>
	    </td>
	  </tr>
	</table>	
	</body>
</html>