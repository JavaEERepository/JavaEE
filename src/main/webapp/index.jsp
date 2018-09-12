<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="bean.User"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="/news/css/1.css" rel="stylesheet" type="text/css">
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
  		
  		<div id="redian" class="content">
            <div id="guonei" class="boxTitle">
                <strong>最新</strong>
                <div class="moreNews">
            		<a href="/news/servlet/NewsServlet?type=showNews">更多</a>
            	</div>
            </div>
            
            <div id="text1" class="boxText">
                <ul>
                	<c:forEach items="${applicationScope.lately}"  var="lately"> 
                		<li>
                			<a href="/news/servlet/NewsServlet?type=showANews&newsId=${lately.newsId}&page=1&pageSize=2">${lately.caption}</a>
                			<div style="float:right">${lately.newsTime }</div>
                		</li>
                	</c:forEach>
                </ul>
            </div>
	    </div>
  		
  		<c:forEach items="${applicationScope.newsTypes}"  var="newsType">
			<div id="redian" class="content">
	            <div id="guonei" class="boxTitle">
	                <strong>${newsType.name}</strong>
	                <div class="moreNews">
            			<a href="/news/servlet/NewsServlet?type=showNews">更多</a>
            		</div>
	            </div>
	            
	            <div id="text1" class="boxText">
	                <ul>
		                <c:if test="${newsType.name=='国际' }">
		                	<c:forEach items="${applicationScope.national}"  var="national"> 
		                		<li>
		                			<a href="/news/servlet/NewsServlet?type=showANews&newsId=${national.newsId}&page=1&pageSize=2">${national.caption}</a>
		                			<div style="float:right">${national.newsTime }</div>
		                		</li>
		                	</c:forEach>
		                </c:if>
		                <c:if test="${newsType.name=='社会' }">
		                	<c:forEach items="${applicationScope.society}"  var="society"> 
		                		<li>
		                			<a href="/news/servlet/NewsServlet?type=showANews&newsId=${society.newsId}&page=1&pageSize=2">${society.caption}</a>
		                			<div style="float:right">${society.newsTime }</div>
		                		</li>
		                	</c:forEach>
		                </c:if>
		                <c:if test="${newsType.name=='体育' }">
		                	<c:forEach items="${applicationScope.sports}"  var="sports"> 
		                		<li>
		                			<a href="/news/servlet/NewsServlet?type=showANews&newsId=${sports.newsId}&page=1&pageSize=2">${sports.caption}</a>
		                			<div style="float:right">${sports.newsTime }</div>
		                		</li>
		                	</c:forEach>
		                </c:if>
	                </ul>
	            </div>
		  	 </div>
	  	 </c:forEach>
		 <jsp:include page='/foot.jsp' flush="true" ></jsp:include>
	</div>
  </body>
</html>
