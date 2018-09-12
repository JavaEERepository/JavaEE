<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		
		function valPassword(input, span){
			var inputValue = document.getElementById(input).value;
			var spanObj=document.getElementById(span);
			var pattern=/^(\w){6,20}$/;
			
			if(inputValue==null || inputValue==""){
				spanObj.innerHTML="*不能为空";
				return false;
			}else if(inputValue.match(pattern)==null){
				spanObj.innerHTML="*密码只能输入6-20个字母、数字、下划线";
				return false;
			}else{
				spanObj.innerHTML="ok";
				return true;
			}
		}
		//新旧密码应该不一样，不一样返回true
		function passwordDifferent(input1,input2,span2){
			var input1Value=document.getElementById(input1).value;
			var input2Value=document.getElementById(input2).value;
			var span2Obj=document.getElementById(span2);
		
			if(input2Value==null || input2Value==""){
				span2Obj.innerHTML="*不能为空";
				return false;
			}else if(input1Value==input2Value){			
				span2Obj.innerHTML="*新旧密码不能相同！";
				return false ;
			}else{
				span2Obj.innerHTML="*ok";
				return true;
			}					
		}			
		
		//新密码和再次确认的密码应该一样，一样返回true
		function passwordSame(input1,input2,span2){
			var input1Value=document.getElementById(input1).value;
			var input2Value=document.getElementById(input2).value;
			var span2Obj=document.getElementById(span2);
		
			if(input2Value==null || input2Value==""){
				span2Obj.innerHTML="*不能为空";
				return false;
			}else if(input1Value==input2Value){			
				span2Obj.innerHTML="ok";
				return true ;
			}else{
				span2Obj.innerHTML="*两次密码不一样";
				return false;
			}					
		}	
			
		function submit1(){
			result1=valPassword("oldPassword", "oldPasswordSpan");			
			result1=valPassword("newPassword","newPasswordSpan") && result1;
			result1=passwordDifferent("oldPassword","newPassword","newPasswordSpan") && result1;
			result1=passwordSame("newPassword","newPassword1","newPasswordSpan1") && result1;
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
    <form action="/news/servlet/UserServlet?type=changePassword" method="post" onsubmit="return submit1()">
		<table style="margin: 50px auto">
			<tbody>
				<tr>
					<td></td>
					<td>修改密码</td>
				</tr>		
				<tr>
					<td align="right">旧密码：</td>
					<td align="left"><input type="password" name="oldPassword" id="oldPassword" onBlur="valPassword('oldPassword', 'oldPasswordSpan');"><span id="oldPasswordSpan"></span></td>
				</tr>
				<tr>
					<td align="right">新密码：</td>
					<td align="left"><input type="password" name="newPassword" id="newPassword" onBlur="valPassword('newPassword','newPasswordSpan');"><span id="newPasswordSpan"></span></td>
				</tr>
				<tr>
					<td align="right">再次输入新密码：</td>
					<td align="left"><input type="password" name="newPassword1" id="newPassword1" onBlur="passwordSame('newPassword','newPassword1','newPasswordSpan1');"><span id="newPasswordSpan1"></span></td>
				</tr>
				<tr>
					<td></td><td><input type="submit" value="提交"/></td>
				</tr>								
			</tbody>
		</table>
	</form>
	<jsp:include page='/foot.jsp' flush="true" ></jsp:include>
  </div>
  </body>
</html>
