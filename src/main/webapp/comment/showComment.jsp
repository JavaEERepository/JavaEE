<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link href="/news/css/1.css" rel="stylesheet" type="text/css">
	
	<script type='text/javascript'>
		
      function getOnePage(type,newsId){
	    	var page = document.getElementById("page");
	  		var totalPage = document.getElementById("totalPage");
	
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

	  		document.getElementById('myform').action="/news/servlet/NewsServlet?type=showANews&newsId="+newsId+"&page="+page.value;
	  		document.getElementById('myform').submit(); //提交
      	}
      	
      	function praise(commentId,newsId){
      		document.getElementById('myform').action="/news/servlet/CommentServlet?type=praise&"
      			+"&commentId="+commentId+"&newsId="+newsId;
      		document.getElementById('myform').submit();
      	}
      	
      	function model(commentId){
      		document.getElementById('myModel').innerHTML=
      			"<form action='/news/servlet/CommentServlet?type=addCommnet' method='post'> \
      				<table border='1' align='center' cellpadding='0' cellspacing='0' style='margin: 0 auto'>\
						<tbody><tr><td> \
								<textarea name='content' cols='60' rows='8' id='textarea' required></textarea></td>\
							</tr>\
							<tr>\
							<td><input type='submit' name='submit' id='submit' value='提交'>\
								<input type='submit' onclick='cancel();' value='取消'></td>\
						</tr></tbody>\
					</table>\
					<input type='hidden' name='newsId' id='newsId' value='${param.newsId}'>\
					<input type='hidden' name='page' id='page' value='${param.page}'>\
					<input type='hidden' name='pageSize' id='pageSize' value='${param.pageSize}'>\
					<input type='hidden' name='totalPage' id='totalPage' value='${requestScope.pageInformation.totalPage}'>\
					<input type='hidden' name='commentId' id='commentId'>\
				</form>";
			document.getElementById('commentId').value=commentId;
      		document.getElementById('myModel').style.display="block";      		
      	}
      	
      	function cancel(){
      		document.getElementById('myModel').style.display="none"; 
      	}
      	
	</script>
  </head>
  
  <body>
  	<form action="/news/servlet/NewsServlet?type=showANews&newsId=${param.newsId}" id="myform" method="post">
  	 <table align="center" border='1' style="margin: 0 auto">
	    <tr bgcolor='#FFACAC'>
	      <td>评论：</td>
	    </tr>	    
	    <c:forEach items="${requestScope.comments}"  var="comment">      
	   		<tr><td>用户：${comment.userName}&nbsp;&nbsp;于
	   			<fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${comment.time}" />发表：&nbsp;&nbsp;第${comment.stair}楼层<br>
	   				${comment.content}<br>
	   				赞（<a href="javascript:void(0);" onclick="praise(${comment.commentId},${param.newsId});">${comment.praise}</a>）
	   				<a href="javascript:void(0);" onclick="model(${comment.commentId});">回复</a>
	   		</td></tr>
		</c:forEach> 	    
	</table>
	 <table align="center" border='1' style="margin: 10px auto">     
	   	<tr>			
			<c:if test="${requestScope.pageInformation.page > 1}">
				<td><input type="button"  value="首页" onclick="getOnePage('first','${param.newsId}');"></td>
			</c:if>
			
			<c:if test="${requestScope.pageInformation.page > 1}">
				<td><input type="button"  value="上一页" onclick="getOnePage('pre','${param.newsId}');"></td>
			</c:if>			 
			
			<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPage}">
				<td><input type="button"  value="下一页" onclick="getOnePage('next','${param.newsId}');"></td>
			</c:if>	  			
			
			<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPage}">
				<td><input type="button"  value="尾页" onclick="getOnePage('last','${param.newsId}');"></td>
			</c:if>	
			
			<td>第${requestScope.pageInformation.page }页/共${requestScope.pageInformation.totalPage }页</td>   	
		</tr>    
	 </table>
	 	<input type="hidden" name="page" id="page" value="${requestScope.pageInformation.page}" >
		<input type="hidden" name="pageSize" id="pageSize" value="${requestScope.pageInformation.pageSize}" >
		<input type="hidden" name="totalPage" id="totalPage" value="${requestScope.pageInformation.totalPage}">
   </form>
   
   <div id="myModel" class="model"></div>
  </body>

  
</html>