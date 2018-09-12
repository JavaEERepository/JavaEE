package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.UserInformation;
import dao.DatabaseDao;
import dao.UserInformationDao;

public class TestUserInformationDao {

	@Test
	public void testGetByUserId() throws Exception {
		Integer userId = 10; //测试该id是否有用户信息
		DatabaseDao databaseDao = new DatabaseDao();
		UserInformationDao userInformationDao = new UserInformationDao();
		
		assertNotNull(userInformationDao.getByUserId(userId, databaseDao));
	}
	
	@Test
	public void testUpdate() throws Exception {
		DatabaseDao databaseDao = new DatabaseDao();
		UserInformationDao userInformationDao = new UserInformationDao();
		UserInformation userInformation = new UserInformation();
		//测试该用户是否可以更新信息
		userInformation.setUserId(10);
		userInformation.setSex("女");
		userInformation.setHobby("打代码");

		assertTrue(Boolean.parseBoolean(String.valueOf(userInformationDao.update(userInformation, databaseDao))));
	}
	
	@Test
	public void testInsert() throws Exception {
		DatabaseDao databaseDao = new DatabaseDao();
		UserInformationDao userInformationDao = new UserInformationDao();
		UserInformation userInformation = new UserInformation();
		//测试该用户是否可以插入信息
		userInformation.setUserId(50);
		userInformation.setSex("女");
		userInformation.setHobby("打代码");

		assertTrue(Boolean.parseBoolean(String.valueOf(userInformationDao.insert(userInformation, databaseDao))));
	}

	@Test
	public void testHasUserId() throws Exception {
		Integer userId = 10;  //测试是否有此用户id
		UserInformationDao userInformationDao = new UserInformationDao();
		DatabaseDao databaseDao = new DatabaseDao();

		assertTrue(userInformationDao.hasUserId(userId, databaseDao));
	}
}
