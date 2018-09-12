<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>审查用户</title>

  </head>
  
  <script type="text/javascript">
  	function checkAUser (userId) {
  		var ids = document.getElementById("ids");
  		ids.value = userId;
  		
  		document.getElementById('myform').submit(); //提交
  	}
  	
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
  
  <body>
  <div class="mainIndex">
  	<jsp:include page='/head.jsp' flush="true" ></jsp:include>
    <form action="/news/servlet/UserServlet?type=check" id="myform" method="POST">
    	<table align="center" border='1' style="margin: 50px auto">
    		<tr align="center" bgcolor="#ccc">
    			<td><a href="javascript:void(0);" onclick="getOnePage('','userId');">id</a></td>
    			<td>用户类型</td>
    			<td>用户名称</td>
    			<td>注册日期</td>
    			<td><a href="javascript:void(0);" onclick="getOnePage('','isUse');">账号可用性</a></td>
    			<td>切换账号可用性</td>
    		</tr>
    		<c:forEach items="${requestScope.users }" var="user">
    			<tr align="center">
    				<td><c:out value="${user.userId }" /></td>
    				<td><c:out value="${user.userType }" /></td>
    				<td><c:out value="${user.userName }" /></td>
    				<td><c:out value="${user.registrationDate }" /></td>
    				<td><c:out value="${user.isUse }" /></td>
    				<td>
    					<c:choose>
    						<c:when test="${user.isUse == 'use' }">
    							<input type="button"  value="停用" onclick="checkAUser('${user.userId}');" >
    						</c:when>
    						<c:when test="${user.isUse == 'stop' }">
    							<input type="button"  value="启用" onclick="checkAUser('${user.userId}');" >
    						</c:when>
    					</c:choose>
    				</td>
    			</tr>
    		</c:forEach>
		</table>
		<table align="center" border='1' style="margin: 50px auto">
			<tr align="center">
				<c:if test="${requestScope.pageInformation.page > 1 }">
					<td><input type="button"  value="首页" onclick="getOnePage('first','');"></td>
				</c:if>
				<c:if test="${requestScope.pageInformation.page > 1 }">
					<td><input type="button"  value="上一页" onclick="getOnePage('pre','');"></td>
				</c:if>
				<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPage }">
					<td><input type="button"  value="下一页" onclick="getOnePage('next','');"></td>
				</c:if>
				<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPage }">
					<td><input type="button"  value="尾页" onclick="getOnePage('last','');"></td>
				</c:if>
				<td>第${requestScope.pageInformation.page }页/共${requestScope.pageInformation.totalPage }页</td>
			</tr>
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
	<jsp:include page='/foot.jsp' flush="true" ></jsp:include>
  </div>
 </body>
</html>