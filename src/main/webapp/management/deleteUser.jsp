<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>删除用户</title>

  </head>
  
  <script type="text/javascript">
  	function checkAll1(obj){
	  	var checkboxs= document.getElementsByName("checkbox1");
	  	for (var i = 0; i < checkboxs.length; i++) 
	  		checkboxs[i].checked =obj.checked;	  
	 }
	
	 function deleteUsers(){
	 	var checkboxs= document.getElementsByName("checkbox1"); 
	 	var ids="";
	 	//拼接id为 ：1,2,
	  	for(i=0;i<checkboxs.length;i++){
	      	if(checkboxs[i].checked == true)
	          	ids+=checkboxs[i].value+",";            	
	      }
		if(ids.length<1){
			alert("请选择至少一个需删除的元素！");
			return false;//阻止提交
		}
		ids=ids.substring(0,ids.length-1);//删除最后的逗号
		ids1=document.getElementById("ids"); 
	 	ids1.value=ids;
	 	//提交
	  	document.getElementById('myform').submit();
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
    <form action="/news/servlet/UserServlet?type=delete" id="myform" method="POST">
    	<table align="center" border='1' style="margin: 50px auto">
    		<tr align="center" bgcolor="#ccc">
    			<td><input id="checkboxAll" type='checkbox' onchange="checkAll1(this);"></td>
    			<td><a href="javascript:void(0);" onclick="getOnePage('','userId');">id</a></td>
    			<td>用户类型</td>
    			<td>用户名称</td>
    			<td>注册日期</td>
    			<td>账号可用性</td>
    		</tr>
    		<c:forEach items="${requestScope.users }" var="user">
    			<tr align="center">
    				<td><input name="checkbox1"  type="checkbox" value="${user.userId}"></td>
    				<td><c:out value="${user.userId }" /></td>
    				<td><c:out value="${user.userType }" /></td>
    				<td><c:out value="${user.userName }" /></td>
    				<td><c:out value="${user.registrationDate }" /></td>
    				<td><c:out value="${user.isUse }" /></td>
    			</tr>
    		</c:forEach>
		</table>
		<table align="center" border='1' style="margin: 50px auto">
			<tr align="center">
				<td><input type="button"  value="删除所选项" onclick="deleteUsers();"></td>
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