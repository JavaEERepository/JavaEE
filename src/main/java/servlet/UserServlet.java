package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import tools.PageInformation;
import tools.SearchTool;
import tools.Tool;
import bean.User;
import bean.UserInformation;
import tools.Message;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		UserService userService = new UserService();
		Message message = new Message();
		
		if("showPage".equals(type)) {//浏览用户
			PageInformation pageInformation = new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			List<User> users = userService.getOnePage(pageInformation);
			
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			
			getServletContext().getRequestDispatcher("/management/showUser.jsp").forward(request, response);
		}else if("check".equals(type)) {//审查用户
			PageInformation pageInformation = new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			String id = pageInformation.getIds(); //记录需要更改的id
			pageInformation.setIds(null); //设ids为null避免误认为是删除操作
			List<User> users = userService.check(pageInformation, id); //获取更改后的用户数组
			if(users == null) {
				message.setMessage("切换用户账户可用性失败，请重新操作!");
				message.setRedirectUrl("/message.jsp");
			}else {
				request.setAttribute("pageInformation", pageInformation);
				request.setAttribute("users", users);
				getServletContext().getRequestDispatcher("/management/checkUser.jsp").forward(request, response);
			}
		}else if("search".equals(type)) {//查询用户
			PageInformation pageInformation = new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			pageInformation.setSearchSql(SearchTool.user(request));
			List<User> users = userService.getOnePage(pageInformation);
			
			if(users.size() == 0) {
				message.setMessage("您所查询的用户不存在!");
				message.setRedirectUrl("/management/showUser.jsp");
				request.setAttribute("message", message);
				getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
			}else {
				request.setAttribute("pageInformation", pageInformation);
				request.setAttribute("users", users);
				getServletContext().getRequestDispatcher("/management/showUser.jsp").forward(request, response);
			}
		}else if("delete".equals(type)) {//删除用户
			PageInformation pageInformation = new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			pageInformation.setSearchSql(" (userType='user' or userType='newsAuthor')");
			List<User> users = userService.deletes(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/management/deleteUser.jsp").forward(request, response);
		}else if("changePassword".equals(type)) {//修改密码
			String newPassword=request.getParameter("newPassword"); //从客户端获取新密码
			User user=(User)request.getSession().getAttribute("user"); //从Session获取当前用户信息
			user.setPassword(request.getParameter("oldPassword")); //设置当前用户的旧密码(登录操作时为防止密码泄露已把密码设为null)
			Integer result=userService.changePassword(user,newPassword);
			message.setResult(result);
			
			if(result==1){
				message.setMessage("修改密码成功！");					
			}else if(result==0){
				message.setMessage("修改密码失败，请联系管理员！");				
			}
			message.setRedirectTime(3);
			message.setRedirectUrl("/news/index.jsp");
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}else if("showPrivate".equals(type)) {//显示普通用户的个人信息
			User user=(User)request.getSession().getAttribute("user");
			if("user".equals(user.getUserType())){
				UserInformation userinformation=userService.getByUserId(user.getUserId());
				request.setAttribute("userinformation", userinformation);
			}			
			getServletContext().getRequestDispatcher("/user/manager/showPrivate.jsp").forward(request,response);
		}else if("changePrivate1".equals(type)) {//修改普通用户个人信息的第一步：显示可修改信息
			User user=(User)request.getSession().getAttribute("user");
			if("user".equals(user.getUserType())){
				UserInformation userinformation=userService.getByUserId(user.getUserId());
				request.setAttribute("userinformation", userinformation);
			}			
			getServletContext().getRequestDispatcher("/user/manager/changePrivate.jsp").forward(request,response);
		}else if("changePrivate2".equals(type)) {//修改普通用户个人信息的第二步：修改信息
			User user=(User)request.getSession().getAttribute("user");
			if("user".equals(user.getUserType())){
				UserInformation userinformation=new UserInformation();
				userinformation.setUserId(user.getUserId());
				userinformation.setSex(request.getParameter("sex"));
				userinformation.setHobby(request.getParameter("hobby"));
			}
			Integer result=userService.updatePrivate(user, request);
			message.setResult(result);
			if(result==5){
				message.setMessage("修改个人信息成功！");	
				message.setRedirectUrl("/news/servlet/UserServlet?type=showPrivate");
			}else {
				message.setMessage("修改个人信息失败，请联系管理员！");
				message.setRedirectUrl("/news/servlet/UserServlet?type=showPrivate");
			}
			message.setRedirectTime(3);
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}
	}	

}
