<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户注册</title>
    
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
			
			if(str1==null || str1==""){
				document.getElementById("namespan").innerHTML="*不能为空";
				return false;
			}else if(str1.length>=8 && pattern.test(str1)){//pattern.test() 模式如果匹配，会返回true，不匹配返回false
				document.getElementById("namespan").innerHTML="ok";
				return true;
			}else{
				document.getElementById("namespan").innerHTML="*用户名至少需要8个字符，以字母开头，以字母或数字结尾，可以有-和_";
				return false;
			}
		}
		
		function valPassword(){
			var str = document.getElementById("password").value;
			var pattern=/^(\w){6,20}$/;
			
			if(document.getElementById("password").value==null || document.getElementById("password").value==""){
				document.getElementById("passwordspan").innerHTML="*不能为空";
				return false;
			}else if(str.match(pattern)==null){
				document.getElementById("passwordspan").innerHTML="*密码只能输入6-20个字母、数字、下划线";
				return false;
			}else{
				document.getElementById("passwordspan").innerHTML="ok";
				return true;
			}
		}
		
		function passwordSame(){
			var str = document.getElementById("password").value;
			if(document.getElementById("password2").value==null || document.getElementById("password2").value==""){
				document.getElementById("passwordspan2").innerHTML="*不能为空";
				return false;
			}else if(document.getElementById("password").value==document.getElementById("password2").value){			
				document.getElementById("passwordspan2").innerHTML="ok";
				return true ;
			}else{
				document.getElementById("passwordspan2").innerHTML="*两次密码不一样";
				return false;
			}
					
		}
			
		function submit1(){
			result1=valName();
			result1=valPassword() && result1;
			result1=passwordSame() && result1;
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
    <form action="/news/servlet/SecurityServlet?type=register" method="post" onsubmit="return submit1()">
		<table style="margin: 0 auto">
			<tbody>
				<tr>
					<td></td><td>注册</td>
				</tr>
				<tr>
					<td align="right">用户类型：</td>
					<td><select name="userType">
							<option value="user">普通用户</option>
							<option value="newsAuthor">新闻发布员</option>
							<option value="administrator">管理员</option>
					</select></td>
				</tr>			
				<tr>
					<td align="right">用户名：</td>
					<td align="left"><input type="text" name="userName" id="userName" onBlur="valName()"><span id="namespan"></span></td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td align="left"><input type="password" name="password" id="password" onBlur="valPassword()"><span id="passwordspan"></span></td>
				</tr>
				<tr>
					<td align="right">重新输入密码：</td>
					<td align="left"><input type="password" name="password2" id="password2" onBlur="passwordSame()"><span id="passwordspan2"></span></td>
				</tr>
				<tr>
					<td></td><td><input type="submit" value="注册"/></td>
				</tr>
			</tbody>
		</table>
	</form>
	<jsp:include page='/foot.jsp' flush="true" ></jsp:include>
  </div>
  </body>
</html>
