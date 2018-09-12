<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		a:link {
			color: #00f;
			text-decoration: none;
		}
		a:visited {
			text-decoration: none;
			color: #00f;
		}
		a:hover {
			text-decoration: none;
			color: #00f;
		}
		a:active {
			text-decoration: none;
			color: #00f;
		}
	</style>
  </head>
  
  <body>
  <div class="mainIndex">
  	<jsp:include page='/head.jsp' flush="true" ></jsp:include>
  	
  	<div style="width: 1050px; height: 500px;">
	 		<div class="leftDiv">
	 			<c:if test="${sessionScope.user.userType=='user' }">
	 				<ul style="margin: auto">
	 					<li><a href="/news/user/manager/changePassword.jsp">修改密码</a></li>
	 					<li><a href="/news/servlet/UserServlet?type=changePrivate1">修改个人信息</a></li>
	 				</ul>
	 			</c:if>
	 			<c:if test="${sessionScope.user.userType=='newsAuthor' }">
	 				<ul style="margin: auto">
	 					<li><a href="/news/user/manager/changePassword.jsp">修改密码</a></li>
	 					<li><a href="/news/servlet/UserServlet?type=changePrivate1">修改个人信息</a><br><br></li>
	 					<li><a href="/news/news/manager/addNews.jsp">添加新闻</a></li>
	 					<li><a href="/news/servlet/NewsServlet?type=manageNews">管理新闻</a></li>
	 				</ul>
	 			</c:if>
	 			<c:if test="${sessionScope.user.userType=='administrator' }">
	 				<ul style="margin: auto">
	 					<li><a href="/news/user/manager/changePassword.jsp">修改密码</a></li>
	 					<li><a href="/news/servlet/UserServlet?type=changePrivate1">修改个人信息</a><br><br></li>
	 					
	 					<li><a href="/news/servlet/UserServlet?type=showPage">浏览用户</a></li>
	 					<li><a href="/news/servlet/UserServlet?type=check">审查用户</a></li>
	 					<li><a href="/news/management/searchUser.jsp">查询用户</a></li>
	 					<li><a href="/news/servlet/UserServlet?type=delete">删除用户</a><br><br></li>
	 					
	 					<li><a href="/news/servlet/NewsServlet?type=manageNews">管理新闻</a></li>
	 				</ul>
	 			</c:if>
	 		</div>
	 		<div class="rightDiv">
	 			<table width="600" border="1" align="center" cellspacing="0" cellpadding="0" style="margin: auto">
					<tbody>
						<tr><td colspan="2">个人信息：</td></tr>
						<tr><td>用户类型：</td>
							<td>${ sessionScope.user.userType}</td>
						</tr>			
						<tr><td>用户名：</td>
							<td>${ sessionScope.user.userName}</td>
						</tr>			
						<tr><td>头像：</td>	
							<td><img src="${ sessionScope.user.headIconUrl}" height="100"/></td></tr>
						<tr><td>注册日期：</td>
							<td>${ sessionScope.user.registrationDate}</td>
						</tr> 
						<c:if test="${sessionScope.user.userType=='user'}" >
							<tr><td>性别：</td>	
								<td>${ requestScope.userinformation.sex}</td></tr>
							<tr><td>爱好：</td>
								<td>${ requestScope.userinformation.hobby}</td>
							</tr> 
						</c:if>
					</tbody>
				</table>
	 		</div>
  		</div>
	<jsp:include page='/foot.jsp' flush="true" ></jsp:include>
  </div>
  </body>
</html>
