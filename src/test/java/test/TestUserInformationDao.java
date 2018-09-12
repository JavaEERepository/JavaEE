package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.UserInformation;
import dao.DatabaseDao;
import dao.UserInformationDao;

public class TestUserInformationDao {

	@Test
	public void testGetByUserId() throws Exception {
		Integer userId = 10; //���Ը�id�Ƿ����û���Ϣ
		DatabaseDao databaseDao = new DatabaseDao();
		UserInformationDao userInformationDao = new UserInformationDao();
		
		assertNotNull(userInformationDao.getByUserId(userId, databaseDao));
	}
	
	@Test
	public void testUpdate() throws Exception {
		DatabaseDao databaseDao = new DatabaseDao();
		UserInformationDao userInformationDao = new UserInformationDao();
		UserInformation userInformation = new UserInformation();
		//���Ը��û��Ƿ���Ը�����Ϣ
		userInformation.setUserId(10);
		userInformation.setSex("Ů");
		userInformation.setHobby("�����");

		assertTrue(Boolean.parseBoolean(String.valueOf(userInformationDao.update(userInformation, databaseDao))));
	}
	
	@Test
	public void testInsert() throws Exception {
		DatabaseDao databaseDao = new DatabaseDao();
		UserInformationDao userInformationDao = new UserInformationDao();
		UserInformation userInformation = new UserInformation();
		//���Ը��û��Ƿ���Բ�����Ϣ
		userInformation.setUserId(50);
		userInformation.setSex("Ů");
		userInformation.setHobby("�����");

		assertTrue(Boolean.parseBoolean(String.valueOf(userInformationDao.insert(userInformation, databaseDao))));
	}

	@Test
	public void testHasUserId() throws Exception {
		Integer userId = 10;  //�����Ƿ��д��û�id
		UserInformationDao userInformationDao = new UserInformationDao();
		DatabaseDao databaseDao = new DatabaseDao();

		assertTrue(userInformationDao.hasUserId(userId, databaseDao));
	}
}
