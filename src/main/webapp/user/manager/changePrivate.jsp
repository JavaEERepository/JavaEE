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
    
    <title>修改个人信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div class="mainIndex">
  	<jsp:include page='/head.jsp' flush="true" ></jsp:include>
    <form action="/news/servlet/UserServlet?type=changePrivate2" method="post"  enctype="multipart/form-data">
		<table width="600" border="1" align="center" cellspacing="0" cellpadding="0" style="margin: 50px auto">
			<tbody>
				<tr><td colspan="2">修改个人信息：</td></tr>		
				<tr><td>头像：</td>	
					<td><img src="${ sessionScope.user.headIconUrl}" height="100"/><input id="myFile" name="myFile" type="file" onchange="preview()"><br>
						<br>预览：<img id="myImage" height="100"/></td></tr>
				<c:if test="${sessionScope.user.userType=='user'}" >
					<tr><td>性别：</td>	
						<td><select name="sex" id="sex">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</td></tr>
					<tr><td>爱好：</td>
						<td><input type="text" name="hobby" value="${requestScope.userinformation.hobby}"/></td>
					</tr> 
				</c:if>	
				<tr><td colspan="2"><input type="submit" value="提交"/></td></tr>					
			</tbody>
		</table> 
  	</form>
  	<jsp:include page='/foot.jsp' flush="true" ></jsp:include>
  </div>
  </body>
  <script language=javascript>
		function preview() {
		 	var preview = document.getElementById("myImage");
		 	var file  = document.getElementById("myFile").files[0];
		 	var reader = new FileReader();
		 	reader.onloadend = function () {
		  		preview.src = reader.result;
		 	}
		 	
			if (file) 
			  	reader.readAsDataURL(file);
			else 
			  	preview.src = "";			
		}
		
		var sex = document.getElementById("sex");
		for (var i = 0; i < sex.options.length; i++) {
            if (sex.options[i].value == "${requestScope.userinformation.sex}") {
                sex.options[i].selected = true;
                break;
            }
        }
	</script>
</html>

