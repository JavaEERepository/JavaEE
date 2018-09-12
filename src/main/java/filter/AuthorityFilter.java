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
		
		User user=(User)req.getSession().getAttribute("user"); //��session�л�ȡuser��Ϣ
		String type=req.getParameter("type"); //�ӿͻ��˻�ȡ��������type
		boolean anyAuthority = "login".equals(type) || "register".equals(type) ||
								"showNews".equals(type) || "showANews".equals(type);                //δ��¼��Ȩ��
		boolean userAuthority = "changePassword".equals(type) || "showPrivate".equals(type) || 
								"changePrivate1".equals(type) || "changePrivate2".equals(type) || "exit".equals(type)
								|| "addCommnet".equals(type) || "praise".equals(type);              //��ͨ�û���Ȩ��
		boolean newsAuthorAuthority = "add".equals(type) || "deleteANews".equals(type) ||
										"manageNews".equals(type) || "edit".equals(type) 
										|| "editANews".equals(type);                                //���Ź���Ա��Ȩ��
		boolean adminAuthority = "showPage".equals(type) || "delete".equals(type) ||
									"check".equals(type) || "search".equals(type);                  //����Ա��Ȩ��
		
		userAuthority = anyAuthority || userAuthority; 
		newsAuthorAuthority = anyAuthority || userAuthority || newsAuthorAuthority;
		adminAuthority = anyAuthority || userAuthority || newsAuthorAuthority || adminAuthority;
		
		if(user==null) {//δ��¼
			if(anyAuthority)
				chain.doFilter(request, response); //��������
			else {
				Message message=new Message();
				message.setMessage("���¼��");
				message.setRedirectTime(1000);
				request.setAttribute("message", message);
				request.getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
				return;
			}
		}else {//�ѵ�¼
			switch(user.getUserType()) {
			case "user":
				if(userAuthority) chain.doFilter(request, response);  //��������
				else {
					Message message=new Message();
					message.setMessage("Ȩ�޲�����");
					message.setRedirectTime(1000);
					request.setAttribute("message", message);
					request.getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
					return;
				}
				break;
			case "newsAuthor":
				if(newsAuthorAuthority) chain.doFilter(request, response);  //��������
				else {
					Message message=new Message();
					message.setMessage("Ȩ�޲�����");
					message.setRedirectTime(1000);
					request.setAttribute("message", message);
					request.getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
					return;
				}
				break;
			case "administrator":
				if(adminAuthority) chain.doFilter(request, response);  //��������
				else {
					Message message=new Message();
					message.setMessage("Ȩ�޲�����");
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
