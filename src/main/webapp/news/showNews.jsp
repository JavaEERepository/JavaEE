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
    
    <title>显示新闻</title>
    
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
	<script type="text/javascript">
	
	
	
      function getOnePage (type, orderFiledName) {
  		var page = document.getElementById("page");
  		var pageSize = document.getElementById("pageSize");
  		var totalPage = document.getElementById("totalPage");
  		var orderFiled = document.getElementById("orderFiled");
  		var orderType = document.getElementById("orderType");
  		//切换排序
  		if(orderFiledName!="") {
  			orderFiled.value = orderFiledName; //设置排序字段名称
  			if(orderType.value == "ASC")//切换排序类型
  				orderType.value = "DESC";
  			else
  				orderType.value = "ASC";
  				
  			page.value = 1; //重新从第一页开始显示
  		}

  		//翻页
  		var pageValue = parseInt(page.value);

  		if(type=='first')
  			page.value = "1";
  		else if(type=='pre') {
  		  	pageValue -= 1;
  		  	page.value = pageValue.toString();
  		}
  		else if(type=='next') {
  			pageValue += 1;
  			page.value = pageValue.toString();
  		}
  		else if(type=='last')
  			page.value = totalPage.value;
  		
  		document.getElementById('myform').submit(); //提交
  	}
	</script>
  </head>
  
  <body>
  <div class="mainIndex">
  		<jsp:include page='/head.jsp' flush="true" ></jsp:include>
		   <form action="/news/servlet/NewsServlet?type=showNews" id="myform" method="post">
		  	 <table align="center" border='1' style="margin: 50px auto">
			    <tr align="center" bgcolor="#ccc">
			      <td><a href="javascript:void(0);" onclick="getOnePage('','newsId');">id</a></td>
			      <td>标题</td><td>作者</td><td><a href="javascript:void(0);" onclick="getOnePage('','newsTime');">日期</a></td>
			    </tr>	    
			    <c:forEach items="${requestScope.newses}"  var="news">      
			   		<tr>
				   		<td>${news.newsId}</td>     
				   		<td><a href="/news/servlet/NewsServlet?type=showANews&newsId=${news.newsId}&page=1&pageSize=2" target="_blank">${news.caption}</a></td>	
				   		<td>${news.author}</td>     
				   		<td>${news.newsTime}</td>	
				   	</tr>
				</c:forEach> 	    
			</table>
			 <table align="center" border='1' style="margin: 50px auto">     
			   	<tr>			
					<c:if test="${requestScope.pageInformation.page > 1}">
						<td><input type="button"  value="首页" onclick="getOnePage('first','');"></td>
					</c:if>
					
					<c:if test="${requestScope.pageInformation.page > 1}">
						<td><input type="button"  value="上一页" onclick="getOnePage('pre','');"></td>
					</c:if>			 
					
					<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPage}">
						<td><input type="button"  value="下一页" onclick="getOnePage('next','');"></td>
					</c:if>	  			
					
					<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPage}">
						<td><input type="button"  value="尾页" onclick="getOnePage('last','');"></td>
					</c:if>	
					
					<td>第${requestScope.pageInformation.page }页/共${requestScope.pageInformation.totalPage }页</td>   	
					
				</tr>    
			 </table>
			 	<input type="hidden" name="page" id="page" value="${requestScope.pageInformation.page}" >
				<input type="hidden" name="pageSize" id="pageSize" value="${requestScope.pageInformation.pageSize}" >
				<input type="hidden" name="totalPage" id="totalPage" value="${requestScope.pageInformation.totalPage}" >
				<input type="hidden" name="allRecordCount" id="allRecordCount" value="${requestScope.pageInformation.allRecordCount}" >
				<input type="hidden" name="orderFiled" id="orderFiled" value="${requestScope.pageInformation.orderFiled}" >
				<input type="hidden" name="orderType" id="orderType" value="${requestScope.pageInformation.orderType}" >
				<input type="hidden" name="ids" id="ids" value="${requestScope.pageInformation.ids}" >
				<input type="hidden" name="searchSql" id="searchSql" value="${requestScope.pageInformation.searchSql}" >
		  </form>
		  <jsp:include page='/foot.jsp' flush="true" ></jsp:include>
	</div>
  </body>
</html>
