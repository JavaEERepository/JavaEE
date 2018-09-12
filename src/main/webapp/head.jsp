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
    
    <title>My JSP 'head.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="/news/css/1.css" rel="stylesheet" type="text/css">

  </head>
  
  <body>
  
	  <div class="top">
	  	<div style="float:left">
	  			<div class="titleBox" style="background-color: #blue"><div class="title"><a href="#">首页</a></div></div>
	  			<c:forEach items="${applicationScope.newsTypes}"  var="newsType">
					<div class="titleBox"><div class="title">${newsType.name}</div></div>
				</c:forEach>
	  	</div>
	  	<div style="float:right">
            <c:if test="${sessionScope.user.userName==null }">
            	<div style="margin-top: 6px">
				  	<a href="/news/user/login.jsp" target="_self">登录</a>/
	            	<a href="/news/user/register.jsp" target="_self">注册</a>
	            </div>
			</c:if>
            <c:if test="${sessionScope.user.userName!=null }">
            	<div>
				  	<img src="${sessionScope.user.headIconUrl }" height=30px style="margin-top: 3px" />&nbsp;
				  	用户名：<a href="/news/servlet/UserServlet?type=showPrivate">${sessionScope.user.userName }</a>
				  	/<a href='/news/servlet/SecurityServlet?type=exit'>注销</a>
			  	</div>
     		</c:if>
     	</div>
	  </div>

  </body>
</html>
