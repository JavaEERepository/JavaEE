<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head><meta charset="utf-8"></head>
  <body>
  	<form action="/news/servlet/CommentServlet?type=addCommnet" method="post">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="margin: 0 auto">
			<tbody><tr><td>
					<textarea name="content" cols="60" rows="8" id="textarea" required></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" name="submit" id="submit" value="提交"></td>
				</tr></tbody>
		</table>
		<input type="hidden" name="newsId" id="newsId" value="${param.newsId}">
	 	<input type="hidden" name="page" id="page" value="${param.page}">
		<input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize}">
	 	<input type="hidden" name="totalPage" id="totalPage" value="${param.totalPage}">
	</form>    	
  </body>
</html>
