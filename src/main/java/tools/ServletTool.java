package tools;

//import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import bean.News;

public class ServletTool {
	static public News news(HttpServletRequest request){
		
		News news=new News();
		String newsId=request.getParameter("newsId");
		if(newsId!=null && !newsId.isEmpty())
			news.setNewsId(Integer.parseInt(request.getParameter("newsId")));
		
		news.setCaption(request.getParameter("caption"));
		news.setAuthor(request.getParameter("author"));
		news.setNewsType(request.getParameter("newsType"));
		//uedit�ϴ������ݵ����������ǣ�editorValue
		news.setContent(request.getParameter("editorValue"));
		String a=request.getParameter("newsTime");
		//DateTimeFormatter���ڽ��ַ���������LocalDateTime���͵Ķ��󣬻��߷�֮
		LocalDateTime localDateTime=LocalDateTime.parse(a, DateTimeFormatter.ISO_LOCAL_DATE_TIME);		
		news.setNewsTime(localDateTime);

		return news;
	}
}
