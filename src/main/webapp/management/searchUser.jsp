<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div class="mainIndex">
  	<jsp:include page='/head.jsp' flush="true" ></jsp:include>
    <form action="/news/servlet/UserServlet?type=search" id="myform" method="POST">
    	<table align="center" border="1" style="margin: 50px auto">
    		<tbody>
    			<tr>
    				<td></td>
    				<td>查询条件</td>
    			</tr>
    			<tr>
    				<td align="right">用户类型:</td>
    				<td>
    					<select name="userType">
							<option value="all">全部</option>
							<option value="user">用户</option>
							<option value="newsAuthor">新闻发布员</option>
							<option value="administrator">管理员</option>
						</select>
    				</td>
    			</tr>
    			<tr>
    				<td align="right">用户名称:</td>
    				<td align="left">
    					<input type="text" name="userName" id="userName"><span id="namespan"></span>
    				</td>
    			</tr>
    			<tr>
					<td align="right">帐号可用性:</td>
					<td align="left">
						<select name="isUse" id="select">
							<option value="all">全部</option>
							<option value="use">可用</option>
							<option value="stop">停用</option>
						</select>					
					<span id="passwordspan"></span></td>
				</tr>
				<tr>
					<td align="right">注册日期:</td>
					<td align="left">介于<input type="date" name="lowDate">与<input type="date" name="upDate">之间</td>
				</tr>
				<tr>
					<td align="center" colspan='2'><input type="submit" value="查询" /></td>
				</tr>
				
    		</tbody>
    	</table>
    </form>
    <jsp:include page='/foot.jsp' flush="true" ></jsp:include>
  </div>
  </body>
</html>
