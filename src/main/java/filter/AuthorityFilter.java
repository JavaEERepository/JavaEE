package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import tools.Message;
import bean.User;

public class AuthorityFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		//HttpServletResponse res = (HttpServletResponse) response;
		
		User user=(User)req.getSession().getAttribute("user"); //从session中获取user信息
		String type=req.getParameter("type"); //从客户端获取操作类型type
		boolean anyAuthority = "login".equals(type) || "register".equals(type) ||
								"showNews".equals(type) || "showANews".equals(type);                //未登录的权限
		boolean userAuthority = "changePassword".equals(type) || "showPrivate".equals(type) || 
								"changePrivate1".equals(type) || "changePrivate2".equals(type) || "exit".equals(type)
								|| "addCommnet".equals(type) || "praise".equals(type);              //普通用户的权限
		boolean newsAuthorAuthority = "add".equals(type) || "deleteANews".equals(type) ||
										"manageNews".equals(type) || "edit".equals(type) 
										|| "editANews".equals(type);                                //新闻管理员的权限
		boolean adminAuthority = "showPage".equals(type) || "delete".equals(type) ||
									"check".equals(type) || "search".equals(type);                  //管理员的权限
		
		userAuthority = anyAuthority || userAuthority; 
		newsAuthorAuthority = anyAuthority || userAuthority || newsAuthorAuthority;
		adminAuthority = anyAuthority || userAuthority || newsAuthorAuthority || adminAuthority;
		
		if(user==null) {//未登录
			if(anyAuthority)
				chain.doFilter(request, response); //继续访问
			else {
				Message message=new Message();
				message.setMessage("请登录！");
				message.setRedirectTime(1000);
				request.setAttribute("message", message);
				request.getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
				return;
			}
		}else {//已登录
			switch(user.getUserType()) {
			case "user":
				if(userAuthority) chain.doFilter(request, response);  //继续访问
				else {
					Message message=new Message();
					message.setMessage("权限不够！");
					message.setRedirectTime(1000);
					request.setAttribute("message", message);
					request.getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
					return;
				}
				break;
			case "newsAuthor":
				if(newsAuthorAuthority) chain.doFilter(request, response);  //继续访问
				else {
					Message message=new Message();
					message.setMessage("权限不够！");
					message.setRedirectTime(1000);
					request.setAttribute("message", message);
					request.getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
					return;
				}
				break;
			case "administrator":
				if(adminAuthority) chain.doFilter(request, response);  //继续访问
				else {
					Message message=new Message();
					message.setMessage("权限不够！");
					message.setRedirectTime(1000);
					request.setAttribute("message", message);
					request.getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
					return;
				}
				break;
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {}
	public void destroy() {}
}
