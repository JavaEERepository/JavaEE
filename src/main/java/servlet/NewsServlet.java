package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Comment;
import bean.News;
import service.CommentService;
import service.NewsService;
import tools.Message;
import tools.PageInformation;
import tools.ServletTool;
import tools.Tool;

public class NewsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String type=request.getParameter("type");
		NewsService newsService=new NewsService();
		Message message=new Message();
		
		if("add".equals(type)) {//添加新闻
			News news=ServletTool.news(request);
			int result=newsService.add(news);
			message.setResult(result);
			
			if(result==1){
				message.setMessage("添加新闻成功！请添加新的新闻！");
				message.setRedirectUrl("/news/news/manager/addNews.jsp");
			}else{
				message.setMessage("添加新闻失败！请联系管理员！");
				message.setRedirectUrl("/news/index.jsp");
			}
			
			message.setRedirectTime(3);
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}else if("showNews".equals(type)) {//显示新闻
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("news", request, pageInformation);
			List<News> newss=newsService.getOnePage(pageInformation);
			
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("newses", newss);
			getServletContext().getRequestDispatcher("/news/showNews.jsp").forward(request,response);
		}else if("showANews".equals(type)) {//显示一则新闻
			Integer newsId=Integer.parseInt(request.getParameter("newsId"));
			News news=newsService.getNewsById(newsId);
			CommentService commentService=new CommentService();
			PageInformation pageInformation=new PageInformation();
			
			Tool.getPageInformation("comment", request, pageInformation);
			pageInformation.setSearchSql("(newsId="+newsId+")");
			List<Comment> comments=commentService.getOnePage(pageInformation);
			
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("comments", comments);
			request.setAttribute("news", news);
			getServletContext().getRequestDispatcher("/news/aNewsShow.jsp").forward(request,response);
		}else if("deleteANews".equals(type) || "manageNews".equals(type)) {//删除新闻或管理新闻
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("news", request, pageInformation);
			List<News> newses=null;
			
			if("manageNews".equals(type))
				newses=newsService.getOnePage(pageInformation);
			else if("deleteANews".equals(type))
				newses=newsService.deletes(pageInformation);

			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("newses", newses);
			getServletContext().getRequestDispatcher("/news/manager/manageNews.jsp").forward(request,response);
		}else if("editANews".equals(type)){//显示编辑页面
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("news", request, pageInformation);
			Integer newsId=Integer.parseInt(pageInformation.getIds());
			News news=newsService.getNewsById(newsId);
			
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("news", news);
			getServletContext().getRequestDispatcher("/news/manager/editANews.jsp").forward(request,response);
		}else if("edit".equals(type)){//修改新闻
			News news=ServletTool.news(request);
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("news", request, pageInformation);
			Integer newsId=Integer.parseInt(pageInformation.getIds());
			news.setNewsId(newsId);
			
			int result=newsService.edit(news);
			message.setResult(result);
			
			if(result==1)
				message.setMessage("修改新闻成功！");
			else
				message.setMessage("修改新闻失败！请联系管理员！");
			
			message.setRedirectTime(3);
			message.setRedirectUrl("/news/servlet/NewsServlet?type=manageNews");
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}
	}

}
