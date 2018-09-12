package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import bean.User;
import dao.DatabaseDao;
import dao.UserDao;
import tools.PageInformation;

public class TestUserDao {

	@Test
	public void testHasUser() throws Exception {
		User user = new User();
		UserDao userDao = new UserDao();
		DatabaseDao databaseDao = new DatabaseDao();
		//�����Ƿ��д��û�
		user.setUserName("admin123");
		assertTrue(userDao.hasUser(user, databaseDao));
	}
	
	@Test
	public void testGetOnePage() throws Exception {
		UserDao userDao = new UserDao();
		PageInformation pageInformation = new PageInformation();
		pageInformation.setPage(1);
		pageInformation.setPageSize(3);
		
		DatabaseDao databaseDao = new DatabaseDao();		
		List<User> users = userDao.getOnePage(pageInformation, databaseDao);
		assertEquals(new Integer(users.size()),pageInformation.getPageSize());				
	}		

	@Test
	public void testChangeIsUse() throws Exception {
		String id = "49";  //�����Ƿ����ͣ�û����ø��û�
		UserDao userDao = new UserDao();
		DatabaseDao databaseDao = new DatabaseDao();
		
		assertTrue(Boolean.parseBoolean(String.valueOf(userDao.changeIsUse(id, databaseDao))));
	}
	
	@Test
	public void testDeletes() throws Exception {
		String ids = "45";  //�����Ƿ����ɾ�����û�
		UserDao userDao = new UserDao();
		DatabaseDao databaseDao = new DatabaseDao();

		assertTrue(Boolean.parseBoolean(String.valueOf(userDao.deletes(ids, databaseDao))));
	}
	
	@Test
	public void testLogin() throws Exception {
		User user = new User();
		UserDao userDao = new UserDao();
		//���Ը��û��Ƿ���Ե�¼
		user.setUserName("admin123");
		user.setPassword("123456");

		assertTrue(Boolean.parseBoolean(String.valueOf(userDao.login(user))));
	}
	
	@Test
	public void testRegister() throws Exception {
		User user = new User();
		UserDao userDao = new UserDao();
		DatabaseDao databaseDao = new DatabaseDao();
		//���Ը��û��Ƿ����ע��
		user.setUserType("user");
		user.setUserName("abcd123");
		user.setPassword("123456");
		user.setIsUse("use");

		assertTrue(Boolean.parseBoolean(String.valueOf(userDao.register(user, databaseDao))));
	}
	
	@Test
	public void testUpdateHeadIcon() throws Exception {
		User user = new User();
		UserDao userDao = new UserDao();
		DatabaseDao databaseDao = new DatabaseDao();
		//���Ը��û��Ƿ����ע��
		user.setUserId(47);
		user.setHeadIconUrl("/news/upload/images/headIcon/7.jpg");

		assertTrue(Boolean.parseBoolean(String.valueOf(userDao.updateHeadIcon(user, databaseDao))));
	}

}
