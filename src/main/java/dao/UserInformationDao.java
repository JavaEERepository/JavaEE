package dao;
import java.sql.SQLException;

import bean.UserInformation;

public class UserInformationDao {
	public UserInformation getByUserId(Integer userId,DatabaseDao databaseDao) throws SQLException{
		UserInformation userinformation=null;
		String sql="select * from userinformation where userId="+userId;
		databaseDao.query(sql);
		while(databaseDao.next()){
			userinformation=new UserInformation();
			userinformation.setSex(databaseDao.getString("sex"));
			userinformation.setHobby(databaseDao.getString("hobby"));
		}
		return userinformation;
	}
	
	public Integer update(UserInformation userinformation,DatabaseDao databaseDao) throws SQLException{
		String sql="update userinformation set sex='"+userinformation.getSex()
					+"',hobby='"+userinformation.getHobby()
					+"' where userId="+userinformation.getUserId();
		return databaseDao.update(sql);
	}	
	
	public Integer insert(UserInformation userinformation,DatabaseDao databaseDao) throws SQLException{
		String sql="insert into userinformation(userId,sex,hobby) values("
						+userinformation.getUserId()+",'"
						+userinformation.getSex()+"','"
						+userinformation.getHobby()+"')";
		return databaseDao.update(sql);
	}	
	
	public boolean hasUserId(Integer userId,DatabaseDao databaseDao) throws SQLException{
		String sql="select * from userinformation where userId="+userId.toString();
		databaseDao.query(sql);
		while(databaseDao.next()){
			return true;
		}
		return false;
	}
}
