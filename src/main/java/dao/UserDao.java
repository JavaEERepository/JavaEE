package dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.User;
import tools.PageInformation;
import tools.Tool;
import tools.WebProperties;

public class UserDao {
	//����Ƿ��д��û�
	public boolean hasUser(User user,DatabaseDao databaseDao) throws SQLException{
		String sql="select * from user where userName='"+user.getUserName()+"'";
		databaseDao.query(sql);
		while(databaseDao.next()){
			return true;
		}
		return false;
	}
	
	//�õ�һҳ����Ϣ
	public List<User> getOnePage (PageInformation pageInformation, DatabaseDao databaseDao) throws SQLException {
		List<User> users = new ArrayList<User>();
		pageInformation.setPageSize(3); //����ÿһҳ�ĳ���
		String sqlCount = Tool.getSql(pageInformation, "count");
		Integer allRecordCount = databaseDao.getCount(sqlCount);//�����������ܼ�¼��
		Tool.setPageInformation(allRecordCount, pageInformation);//����pageInformation����ҳ��
		
		String getSql=Tool.getSql(pageInformation, "select");
		databaseDao.query(getSql);
		while(databaseDao.next()) {
			User user = new User();
			user.setUserId(databaseDao.getInt("userId"));
			user.setUserType(databaseDao.getString("userType"));
			user.setUserName(databaseDao.getString("userName"));
			user.setRegistrationDate(databaseDao.getTimestamp("registrationDate"));
			user.setIsUse(databaseDao.getString("isUse"));
			users.add(user);
		}
		
		return users;
	}
	
	//�л��û��˻��Ŀ�����
	public Integer changeIsUse (String id, DatabaseDao databaseDao) throws SQLException {
		String sql = "select * from user where userId in (" + id + ")";
		databaseDao.query(sql);
		while (databaseDao.next()) {
			String isUse = databaseDao.getString("isUse");
			if("use".equals(isUse))
				isUse = "stop";
			else
				isUse = "use";
			sql = "update user set isUse='" + isUse + "' where userId in (" + id + ")";
			databaseDao.update(sql);
			return 1;
		}
		
		return 0;
	}
	
	//ɾ���û�
	public Integer deletes (String ids, DatabaseDao databaseDao) throws SQLException {
		if(ids!=null && !ids.isEmpty()) {
			String sql = "delete from user where userId in (" + ids +")";
			return databaseDao.update(sql);
		}else
			return -1;
	}
	
	//�û���¼
	public Integer login(User user) throws SQLException, Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="select * from user where userName='" + user.getUserName()+
						"' and password='"+ user.getPassword()+"'";
		databaseDao.query(sql);
		while(databaseDao.next()){
			String isUse=databaseDao.getString("isUse");
			if( ("use").equals(isUse)  ){
				user.setUserType(databaseDao.getString("userType"));
				user.setUserId(databaseDao.getInt("userId"));
				user.setHeadIconUrl(databaseDao.getString("headIconUrl"));
				user.setRegistrationDate(databaseDao.getTimestamp("registrationDate"));
				return 1;//���Ե�¼
			}			
			return 0;//�û����ڣ�����ͣ��
		}
		return -1;//���û������ڻ��������
	}
	
	//�û�ע��
	public Integer register(User user,DatabaseDao databaseDao) throws SQLException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
        
		user.setHeadIconUrl("\\"+WebProperties.propertiesMap.get("projectName")
				+WebProperties.propertiesMap.get("headIconFileDefault"));//Ĭ��ͷ��
		String sql="insert into user(userType,userName,registrationDate,password,isUse,headIconUrl) values('"+
				user.getUserType()+"','"+user.getUserName()+"','"+df.format(new Date())+"','"+
				user.getPassword()+"','"+user.getIsUse()+"','"+
				user.getHeadIconUrl().replace("\\", "\\\\")+"')";
		return databaseDao.update(sql);
	}
	
	//�����û�ͷ���url
	public Integer updateHeadIcon(User user,DatabaseDao databaseDao)throws SQLException{//
		String sql = "update user set headIconUrl='"+user.getHeadIconUrl()+
				"' where userId ="+user.getUserId().toString();
		return databaseDao.update(sql.replace("\\", "\\\\"));

	}
}
