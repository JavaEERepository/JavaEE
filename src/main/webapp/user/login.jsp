<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="/news/css/1.css" rel="stylesheet" type="text/css">
	
  <script type="text/javascript">
	function valName(){
		var pattern = new RegExp("^[a-z]([a-z0-9])*[-_]?([a-z0-9]+)$","i");//创建模式对象
		var str1=document.getElementById("userName").value;//获取文本框的内容
		
		if(document.getElementById("userName").value==null || document.getElementById("name").value==""){
			document.getElementById("namespan").innerHTML="*不能为空";
			return false;
		}else
			return true;
	}
	
	function valPassword(){
		var str = document.getElementById("password").value;
		if(document.getElementById("password").value==null || document.getElementById("password").value==""){
			document.getElementById("passwordspan").innerHTML="*不能为空";
			return false;
		}else
			return true;
	}
		
	function submit1(){
		result1=valName();
		result1=valPassword() && result1;		
		if( result1)
			return true;//提交
		else 
			return false;//阻止提交
	}
	
  </script>	
  </head>
  
  <body>
  <div class="mainIndex">
	  <jsp:include page='/head.jsp' flush="true" ></jsp:include>
	  <form action="/news/servlet/SecurityServlet?type=login" method="post" onsubmit="return submit1()">
	    <table style="margin: 0 auto">
	    	<tr><td></td><td>登录</td></tr>
	    	<tr>
	    		<td align="right">用户名:</td>
	    		<td align="left"><input type="text" name="userName" id="userName" onBlur="valName()"><span id="namespan"></span></td>
	    	</tr>
	    	<tr>
	    		<td align="right">密码:</td>
	    		<td align="left"><input type="password" name="password" id="password" onBlur="valPassword()"><span id="passwordspan"></span></td>
	    	</tr>
	    	<tr>
				<td></td><td><input type="submit" value="登录"/></td>
			</tr>
	    </table>
	  </form>
	  <jsp:include page='/foot.jsp' flush="true" ></jsp:include>
  </div>
  </body>
</html>
