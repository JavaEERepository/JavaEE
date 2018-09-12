package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Message;
import service.UserService;
import bean.User;

public class SecurityServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		Message message=new Message();
		UserService userService=new UserService();
		message.setRedirectTime(3);
		
		if("register".equals(type)){
			User user=new User();
			user.setUserType(request.getParameter("userType"));
			user.setUserName(request.getParameter("userName"));
			user.setPassword(request.getParameter("password"));
			if("user".equals(user.getUserType()))
				user.setIsUse("use");
			else
				user.setIsUse("stop");			
			
			int result=userService.register(user);
			message.setResult(result);
			if(result==1){
				message.setMessage("注册成功！");
				message.setRedirectUrl("/news/user/login.jsp");
			}else if(result==0){
				message.setMessage("同名用户已存在，请改名重新注册！");
				message.setRedirectUrl("/news/user/register.jsp");
			}else{
				message.setMessage("注册失败！");
				message.setRedirectUrl("/news/user/register.jsp");
			}
			message.setRedirectTime(3);
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}else if("login".equals(type)){
			User user=new User();
			user.setUserName(request.getParameter("userName"));
			user.setPassword(request.getParameter("password"));
			int result=userService.login(user);
			message.setResult(result);
			if(result==1){
				user.setPassword(null);//防止密码泄露
				request.getSession().setAttribute("user", user);
				String originalUrl=(String)request.getSession().getAttribute("originalUrl");
				
				if(originalUrl==null)
					response.sendRedirect("/news/index.jsp");
				else
					response.sendRedirect(originalUrl);
				
				return;
			}else if(result==0){
				message.setMessage("用户存在，但已被停用，请联系管理员！");
				message.setRedirectUrl("/news/user/login.jsp");
			}else if(result==-1){
				message.setMessage("用户不存在，或者密码错误，请重新登录！");
				message.setRedirectUrl("/news/user/login.jsp");
			}else if(result==-2){
				message.setMessage("出现其它错误，请重新登录！");
				message.setRedirectUrl("/news/user/login.jsp");
			}	
			message.setRedirectTime(3);
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);			
		}else if("exit".equals(type)){//注销
			request.getSession().removeAttribute("user");
			response.sendRedirect("/news/index.jsp");
		}
	}

}
