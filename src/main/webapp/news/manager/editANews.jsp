<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="tools.WebProperties" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>编辑新闻界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src='<%=WebProperties.propertiesMap.get("ueditConfigJs")%>'	type="text/javascript"></script>
	<script src='<%=WebProperties.propertiesMap.get("ueditJs")%>'	type="text/javascript"></script>
	<script src='<%=WebProperties.propertiesMap.get("ueditLang")%>'	type="text/javascript"></script>
  </head>
  
  <body>
  <div class="mainIndex">
  	<jsp:include page='/head.jsp' flush="true" ></jsp:include>
    <form action="/news/servlet/NewsServlet?type=edit" id="myform" method="post" onsubmit="return submit1()">
		<table width="600" align="center" border="1" style="margin: 20px auto">
			<tbody>
				<tr><td>标题：</td><td>
					<input type="text" value="${requestScope.news.caption}"  name="caption" id="caption"></td>
				</tr>
				<tr><td>类型：</td><td>
						<select name="newsType" id="newsType">
						<c:forEach items="${applicationScope.newsTypes}"  var="newsType">
							<c:choose>
							    <c:when test="${newsType.name == requestScope.news.newsType}">
							       	<option value="${newsType.name}" selected>${newsType.name}</option>
							    </c:when>
							    <c:otherwise>
							        <option value="${newsType.name}">${newsType.name}</option>
							    </c:otherwise>
							</c:choose>
						</c:forEach>								
						</select>
					</td>
				</tr>
				<tr>
					<td>作者：</td>
					<td><input type="text" value=${requestScope.news.author} name="author" id="author"></td>
				</tr>
				<tr>
					<td>日期：</td>
					<td><input type="datetime-local" value="${requestScope.news.newsTime}" name="newsTime" id="newsTime"></td>
				</tr>
				<tr>
					<td colspan="2">    
						<div>
	    					<script id="content" type="text/plain" style="width:800px;height:500px;"></script>
						</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="修改新闻">&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" onclick="cancel()" value="取消修改">
					</td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" name="page" id="page" value="${requestScope.pageInformation.page }" >
		<input type="hidden" name="pageSize" id="pageSize" value="${requestScope.pageInformation.pageSize }" >
		<input type="hidden" name="totalPage" id="totalPage" value="${requestScope.pageInformation.totalPage }" >
		<input type="hidden" name="allRecordCount" id="allRecordCount" value="${requestScope.pageInformation.allRecordCount }" >
		<input type="hidden" name="orderFiled" id="orderFiled" value="${requestScope.pageInformation.orderFiled }" >
		<input type="hidden" name="orderType" id="orderType" value="${requestScope.pageInformation.orderType }" >
		<input type="hidden" name="ids" id="ids" value="${requestScope.pageInformation.ids }" >
		<input type="hidden" name="searchSql" id="searchSql" value="${requestScope.pageInformation.searchSql }" >
	</form>
	
	
	<script type="text/javascript">	
	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    var ue = UE.getEditor('content');
	    //设置编辑器的内容
	    ue.ready(function() {
    		ue.setContent('${requestScope.news.content}');
		});
		
		function cancel(){//取消修改 
			ids1=document.getElementById("ids"); 
		  	ids1.value="";
		  	//提交
		  	document.getElementById('myform').action="/news/servlet/NewsServlet?type=manageNews";
			document.getElementById('myform').submit();			
		}
		
	</script>
	<jsp:include page='/foot.jsp' flush="true" ></jsp:include>
  </div>
  </body>
</html>
