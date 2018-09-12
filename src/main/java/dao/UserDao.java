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
	//检查是否有此用户
	public boolean hasUser(User user,DatabaseDao databaseDao) throws SQLException{
		String sql="select * from user where userName='"+user.getUserName()+"'";
		databaseDao.query(sql);
		while(databaseDao.next()){
			return true;
		}
		return false;
	}
	
	//得到一页的信息
	public List<User> getOnePage (PageInformation pageInformation, DatabaseDao databaseDao) throws SQLException {
		List<User> users = new ArrayList<User>();
		pageInformation.setPageSize(3); //设置每一页的长度
		String sqlCount = Tool.getSql(pageInformation, "count");
		Integer allRecordCount = databaseDao.getCount(sqlCount);//符合条件的总记录数
		Tool.setPageInformation(allRecordCount, pageInformation);//更新pageInformation的总页数
		
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
	
	//切换用户账户的可用性
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
	
	//删除用户
	public Integer deletes (String ids, DatabaseDao databaseDao) throws SQLException {
		if(ids!=null && !ids.isEmpty()) {
			String sql = "delete from user where userId in (" + ids +")";
			return databaseDao.update(sql);
		}else
			return -1;
	}
	
	//用户登录
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
				return 1;//可以登录
			}			
			return 0;//用户存在，但被停用
		}
		return -1;//该用户不存在或密码错误
	}
	
	//用户注册
	public Integer register(User user,DatabaseDao databaseDao) throws SQLException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        
		user.setHeadIconUrl("\\"+WebProperties.propertiesMap.get("projectName")
				+WebProperties.propertiesMap.get("headIconFileDefault"));//默认头像
		String sql="insert into user(userType,userName,registrationDate,password,isUse,headIconUrl) values('"+
				user.getUserType()+"','"+user.getUserName()+"','"+df.format(new Date())+"','"+
				user.getPassword()+"','"+user.getIsUse()+"','"+
				user.getHeadIconUrl().replace("\\", "\\\\")+"')";
		return databaseDao.update(sql);
	}
	
	//更新用户头像的url
	public Integer updateHeadIcon(User user,DatabaseDao databaseDao)throws SQLException{//
		String sql = "update user set headIconUrl='"+user.getHeadIconUrl()+
				"' where userId ="+user.getUserId().toString();
		return databaseDao.update(sql.replace("\\", "\\\\"));

	}
}
