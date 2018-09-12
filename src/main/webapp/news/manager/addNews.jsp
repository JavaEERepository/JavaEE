<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tools.WebProperties" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加新闻</title>
    <meta charset="utf-8">
	
	<script src='<%=WebProperties.propertiesMap.get("ueditConfigJs")%>'	type="text/javascript"></script>
	<script src='<%=WebProperties.propertiesMap.get("ueditJs")%>'	type="text/javascript"></script>
	<script src='<%=WebProperties.propertiesMap.get("ueditLang")%>'	type="text/javascript"></script>
  </head>
  
  <body>
  <div class="mainIndex">
  	<jsp:include page='/head.jsp' flush="true" ></jsp:include>
	<form action="/news/servlet/NewsServlet?type=add" name="form1" method="post" onsubmit="return submit1()">
		<table width="600" align="center" border="1" style="margin: 20px auto">
			<tbody>
				<tr><td>标题：</td><td>
						<input type="text" name="caption" id="caption"></td>
				</tr>
				<tr><td>类型：</td><td>
						<select name="newsType" id="newsType">
							<c:forEach items="${applicationScope.newsTypes}"  var="newsType">      
						   		<option value="${newsType.name}">${newsType.name}</option>
							</c:forEach>								
						</select>
					</td>
				</tr>
				<tr>
					<td>作者：</td>
					<td><input type="text" name="author" id="author"></td>
				</tr>
				<tr>
					<td>日期：</td>
					<td><input type="datetime-local" name="newsTime" id="newsTime"></td>
				</tr>
				<tr>
					<td colspan="2">    
						<div>
	    					<script id="content" type="text/plain" style="width:800px;height:500px;"></script>
						</div>
		</td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="submit" value="添加新闻"></td>
				</tr>
			</tbody>
		</table>
	</form>
	
	<script type="text/javascript">
	
	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    var ue = UE.getEditor('content');
	</script>
	<jsp:include page='/foot.jsp' flush="true" ></jsp:include>
  </div>
  </body>
</html>
