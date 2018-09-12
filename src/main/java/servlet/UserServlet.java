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
		
		if("showPage".equals(type)) {//����û�
			PageInformation pageInformation = new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			List<User> users = userService.getOnePage(pageInformation);
			
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			
			getServletContext().getRequestDispatcher("/management/showUser.jsp").forward(request, response);
		}else if("check".equals(type)) {//����û�
			PageInformation pageInformation = new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			String id = pageInformation.getIds(); //��¼��Ҫ���ĵ�id
			pageInformation.setIds(null); //��idsΪnull��������Ϊ��ɾ������
			List<User> users = userService.check(pageInformation, id); //��ȡ���ĺ���û�����
			if(users == null) {
				message.setMessage("�л��û��˻�������ʧ�ܣ������²���!");
				message.setRedirectUrl("/message.jsp");
			}else {
				request.setAttribute("pageInformation", pageInformation);
				request.setAttribute("users", users);
				getServletContext().getRequestDispatcher("/management/checkUser.jsp").forward(request, response);
			}
		}else if("search".equals(type)) {//��ѯ�û�
			PageInformation pageInformation = new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			pageInformation.setSearchSql(SearchTool.user(request));
			List<User> users = userService.getOnePage(pageInformation);
			
			if(users.size() == 0) {
				message.setMessage("������ѯ���û�������!");
				message.setRedirectUrl("/management/showUser.jsp");
				request.setAttribute("message", message);
				getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
			}else {
				request.setAttribute("pageInformation", pageInformation);
				request.setAttribute("users", users);
				getServletContext().getRequestDispatcher("/management/showUser.jsp").forward(request, response);
			}
		}else if("delete".equals(type)) {//ɾ���û�
			PageInformation pageInformation = new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			pageInformation.setSearchSql(" (userType='user' or userType='newsAuthor')");
			List<User> users = userService.deletes(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/management/deleteUser.jsp").forward(request, response);
		}else if("changePassword".equals(type)) {//�޸�����
			String newPassword=request.getParameter("newPassword"); //�ӿͻ��˻�ȡ������
			User user=(User)request.getSession().getAttribute("user"); //��Session��ȡ��ǰ�û���Ϣ
			user.setPassword(request.getParameter("oldPassword")); //���õ�ǰ�û��ľ�����(��¼����ʱΪ��ֹ����й¶�Ѱ�������Ϊnull)
			Integer result=userService.changePassword(user,newPassword);
			message.setResult(result);
			
			if(result==1){
				message.setMessage("�޸�����ɹ���");					
			}else if(result==0){
				message.setMessage("�޸�����ʧ�ܣ�����ϵ����Ա��");				
			}
			message.setRedirectTime(3);
			message.setRedirectUrl("/news/index.jsp");
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}else if("showPrivate".equals(type)) {//��ʾ��ͨ�û��ĸ�����Ϣ
			User user=(User)request.getSession().getAttribute("user");
			if("user".equals(user.getUserType())){
				UserInformation userinformation=userService.getByUserId(user.getUserId());
				request.setAttribute("userinformation", userinformation);
			}			
			getServletContext().getRequestDispatcher("/user/manager/showPrivate.jsp").forward(request,response);
		}else if("changePrivate1".equals(type)) {//�޸���ͨ�û�������Ϣ�ĵ�һ������ʾ���޸���Ϣ
			User user=(User)request.getSession().getAttribute("user");
			if("user".equals(user.getUserType())){
				UserInformation userinformation=userService.getByUserId(user.getUserId());
				request.setAttribute("userinformation", userinformation);
			}			
			getServletContext().getRequestDispatcher("/user/manager/changePrivate.jsp").forward(request,response);
		}else if("changePrivate2".equals(type)) {//�޸���ͨ�û�������Ϣ�ĵڶ������޸���Ϣ
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
				message.setMessage("�޸ĸ�����Ϣ�ɹ���");	
				message.setRedirectUrl("/news/servlet/UserServlet?type=showPrivate");
			}else {
				message.setMessage("�޸ĸ�����Ϣʧ�ܣ�����ϵ����Ա��");
				message.setRedirectUrl("/news/servlet/UserServlet?type=showPrivate");
			}
			message.setRedirectTime(3);
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}
	}	

}
