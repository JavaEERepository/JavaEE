package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import bean.User;
import service.UserService;
import tools.PageInformation;

public class TestUserService {

	@Test
	public void testGetOnePage() {
		UserService userService = new UserService();
		PageInformation pageInformation = new PageInformation();
		pageInformation.setTableName("user");
		pageInformation.setPage(1);
		pageInformation.setPageSize(3);
		List<User> users = userService.getOnePage(pageInformation);
		//�����Ƿ��ܵõ�һҳ��Ϣ
		assertEquals(new Integer(users.size()),pageInformation.getPageSize());
	}
	
	@Test
	public void testCheck() {
		String id = "10";//�����ܷ���Ĵ��û��Ŀ�����
		UserService userService = new UserService();
		PageInformation pageInformation = new PageInformation();
		pageInformation.setTableName("user");
		pageInformation.setPage(1);
		pageInformation.setPageSize(3);
		List<User> users = userService.check(pageInformation,id);
		
		assertEquals(new Integer(users.size()),pageInformation.getPageSize());
	}
	
	@Test
	public void testDeletes() {
		UserService userService = new UserService();
		PageInformation pageInformation = new PageInformation();
		pageInformation.setTableName("user");
		pageInformation.setPage(1);
		pageInformation.setPageSize(3);
		pageInformation.setIds("10");//�����ܷ�ɾ�����û�
		List<User> users = userService.deletes(pageInformation);
		
		assertEquals(new Integer(users.size()),pageInformation.getPageSize());
	}
	
	@Test
	public void testLogin() {
		User user = new User();
		UserService userService = new UserService();
		//���Ը��û��Ƿ���Ե�¼
		user.setUserName("admin123");
		user.setPassword("123456");

		assertTrue(Boolean.parseBoolean(String.valueOf(userService.login(user))));
	}
	
	@Test
	public void testRegister() {
		User user = new User();
		UserService userService = new UserService();
		//���Ը��û��Ƿ����ע��
		user.setUserType("user");
		user.setUserName("abcd123");
		user.setPassword("123456");
		user.setIsUse("use");

		assertTrue(Boolean.parseBoolean(String.valueOf(userService.register(user))));
	}
	
	@Test
	public void testChangePassword() {
		User user = new User();
		UserService userService = new UserService();
		String newPassword = "123456789"; //�����Ƿ���Ը��Ĵ��û�������
		
		user.setUserId(50);
		user.setUserName("admin123");
		user.setPassword("123456");

		assertTrue(Boolean.parseBoolean(String.valueOf(userService.changePassword(user, newPassword))));
	}
	
	@Test
	public void testGetByUserId() {
		UserService userService = new UserService();
		Integer userId = 10; //���Ը�id�Ƿ��и�����Ϣ
		
		assertNotNull(userService.getByUserId(userId));
	}
	
	/*
	 * ����һ��updatePrivate()�����������
	 * 
	 */

}
