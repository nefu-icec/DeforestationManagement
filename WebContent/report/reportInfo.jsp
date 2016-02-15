<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<%@ page import="edu.nefu.DeforeMgr.util.*"%>
<%@ page import="edu.nefu.DeforeMgr.report.*" %>
<%@ page import="edu.nefu.DeforeMgr.bean.ReportInfo"%>

<%
int lb=Integer.parseInt(request.getParameter("lb"));
int xb=Integer.parseInt(request.getParameter("xb"));
LB_XB [] lbxbs=LB_XB.getReportLbAndXb();
ReportInfo reportInfo=Information.getReportInfo(lb,xb);
%>

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
		        	<span class="STYLE4">报表详细信息</span>
		        </td>
		        <td width="281" background="../images/tab_05.gif">
			        <table border="0" align="right" cellpadding="0" cellspacing="0">
			            <tr>
			              <td width="60">
				              <table width="87%" border="0" cellpadding="0" cellspacing="0">
				                  <tr>
				                    <td class="STYLE1">
					                    <div align="center"><input type="checkbox" name="checkbox62" value="checkbox" /></div>
				                    </td>
				                    <td class="STYLE1"><div align="center">全选</div></td>
				                  </tr>
				              </table>
			              </td>
			              <td width="60">
				              <table width="90%" border="0" cellpadding="0" cellspacing="0">
				                  <tr>
				                    <td class="STYLE1"><div align="center"><img src="../images/001.gif" width="14" height="14" /></div></td>
				                    <td class="STYLE1"><div align="center">新增</div></td>
				                  </tr>
				              </table>
			              </td>
			              <td width="60">
				              <table width="90%" border="0" cellpadding="0" cellspacing="0">
				                  <tr>
				                    <td class="STYLE1"><div align="center"><img src="../images/114.gif" width="14" height="14" /></div></td>
				                    <td class="STYLE1"><div align="center">修改</div></td>
				                  </tr>
				              </table>
			              </td>
			              <td width="52">
				              <table width="88%" border="0" cellpadding="0" cellspacing="0">
				                  <tr>
				                    <td class="STYLE1"><div align="center"><img src="../images/083.gif" width="14" height="14" /></div></td>
				                    <td class="STYLE1"><div align="center">删除</div></td>
				                  </tr>
				              </table>
			              </td>
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
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">id号</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">林班</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">小班</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">调查单位</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">调查员</div></td>
				          </tr>    
				          <tr>
					        <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getId()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getLb()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getXb()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getDcdw()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getDcy()%></div></td>  
						  </tr>
				          <tr>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">林种区</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">土地所有权</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">林木使用权</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">分类类型</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">林种</div></td>
				          </tr>  
				          <tr>
					        <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getLzq()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getZ_dq()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getZ_lq()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getZ_fllx()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getZ_lz()%></div></td>  
						  </tr>
				          <tr>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">地类</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">起源</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">地区和类别</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">树种</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1"></div></td>
				          </tr>  
				          <tr>
					        <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getZ_dl()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getZ_qy()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getRegion()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getTree()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"></div></td>  
						  </tr>
				          <tr>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">上报采伐蓄积</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">验收采伐蓄积</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">绝对误差</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">相对误差</div></td>
				            <td width="20%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1"></div></td>
				          </tr>  
				          <tr>
					        <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getReportVolume()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getVolume()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getAbsoluteError()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=reportInfo.getRelativeError()%></div></td>
						    <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"></div></td>  
						  </tr>
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