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
		//测试是否能得到一页信息
		assertEquals(new Integer(users.size()),pageInformation.getPageSize());
	}
	
	@Test
	public void testCheck() {
		String id = "10";//测试能否更改此用户的可用性
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
		pageInformation.setIds("10");//测试能否删除此用户
		List<User> users = userService.deletes(pageInformation);
		
		assertEquals(new Integer(users.size()),pageInformation.getPageSize());
	}
	
	@Test
	public void testLogin() {
		User user = new User();
		UserService userService = new UserService();
		//测试该用户是否可以登录
		user.setUserName("admin123");
		user.setPassword("123456");

		assertTrue(Boolean.parseBoolean(String.valueOf(userService.login(user))));
	}
	
	@Test
	public void testRegister() {
		User user = new User();
		UserService userService = new UserService();
		//测试该用户是否可以注册
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
		String newPassword = "123456789"; //测试是否可以更改此用户的密码
		
		user.setUserId(50);
		user.setUserName("admin123");
		user.setPassword("123456");

		assertTrue(Boolean.parseBoolean(String.valueOf(userService.changePassword(user, newPassword))));
	}
	
	@Test
	public void testGetByUserId() {
		UserService userService = new UserService();
		Integer userId = 10; //测试该id是否有个人信息
		
		assertNotNull(userService.getByUserId(userId));
	}
	
	/*
	 * 还有一个updatePrivate()方法不会测试
	 * 
	 */

}
