<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="tools.WebProperties" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!doctype html>
<html>
  <head>
  </head>
  <body>
  <div class="mainIndex">
  	<jsp:include page='/head.jsp' flush="true" ></jsp:include>
	<table width="600" align="center" border="1" style="margin: 0 auto">
		<tbody>
			<tr style="text-align: center"><td>${requestScope.news.caption}</td></tr>
			<tr><td>作者：${requestScope.news.author}</td></tr>
			<tr><td>时间：${requestScope.news.newsTime}</td></tr>
			<tr>
				<td>${requestScope.news.content}</td>
			</tr>
		</tbody>
	</table>
	 
	<br><br>
	<jsp:include page='/comment/addComment.jsp' flush="true" >
		<jsp:param name="newsId" value="${requestScope.news.newsId}" />
		<jsp:param name="page" value="1" />
		<jsp:param name="pageSize" value="${param.pageSize}" />
		<jsp:param name="totalPage" value="${requestScope.pageInformation.totalPage}" />    
	</jsp:include>
	
	<br><br>
	<jsp:include page='<%="/comment/showComment.jsp"%>' flush="true" >
		<jsp:param name="newsId" value="${requestScope.news.newsId}" />
		<jsp:param name="page" value="${param.page}" />
		<jsp:param name="pageSize" value="${param.pageSize}" />
		<jsp:param name="totalPage" value="${param.totalPage}" />
	</jsp:include>
	
	<jsp:include page='/foot.jsp' flush="true" ></jsp:include>
  </div>
  </body>
</html>