<%@ page language="java" import="java.util.*,tools.Message" pageEncoding="UTF-8"%>
<%	
	Message message=(Message) request.getAttribute("message");
	response.setHeader("refresh", message.getRedirectTime()+";URL="+message.getRedirectUrl());
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!doctype html>
<html>
 <head>
 	<meta charset="utf-8"></head>  
 	<link href="/news/css/1.css" rel="stylesheet" type="text/css">
  <body>
  <div class="mainIndex">
  	<jsp:include page='/head.jsp' flush="true" ></jsp:include>
	<table width="600" border="0" align="center" cellpadding="0" cellspacing="0" style="margin: 0 auto">
		<tbody>
			<tr>
				<td>${requestScope.message.message}  </td>
			</tr>
			<tr><td>
				<c:if test="${requestScope.message.redirectTime < 10}">
					${requestScope.message.redirectTime}秒后将跳转页面。<br>
					如果没有跳转,请按 <a href="${requestScope.message.redirectUrl} ">这里</a>!!!
				</c:if>				
				<c:if test="${requestScope.message.redirectTime >= 10}">
					<a href="javascript:void(0);" onclick="history.go(-1);">返回上一步</a>
				</c:if>	
			</td></tr>
		</tbody>
	</table> 
	<jsp:include page='/foot.jsp' flush="true" ></jsp:include>
  </div>   
  </body>
</html>
