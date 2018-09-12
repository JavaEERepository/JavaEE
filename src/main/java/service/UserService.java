package service;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.User;
import bean.UserInformation;
import dao.DatabaseDao;
import dao.UserDao;
import dao.UserInformationDao;
import tools.FileTool;
import tools.PageInformation;
import tools.WebProperties;

public class UserService {
	//�õ�һҳ����Ϣ
	public List<User> getOnePage (PageInformation pageInformation) {
		List<User> users = new ArrayList<User>();
		
		try {
			DatabaseDao databaseDao = new DatabaseDao();
			UserDao userDao = new UserDao();
			users = userDao.getOnePage(pageInformation, databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	//�޸��û����˻�������
	public List<User> check (PageInformation pageInformation, String id) {
		List<User> users = null;
		try {
			DatabaseDao databaseDao = new DatabaseDao();
			UserDao userDao = new UserDao();
			
			if(id!=null && !id.isEmpty()) 
				userDao.changeIsUse(id, databaseDao);
			
			users = userDao.getOnePage(pageInformation, databaseDao);
		}catch (SQLException e) {
			users = null;
			e.printStackTrace();
		}catch (Exception e) {
			users = null;
			e.printStackTrace();
		}
		
		return users;
	}
	
	//ɾ���û�
	public List<User> deletes (PageInformation pageInformation) {
		List<User> users = null;
		try {
			DatabaseDao databaseDao = new DatabaseDao();
			UserDao userDao = new UserDao();
			userDao.deletes(pageInformation.getIds(), databaseDao);
			pageInformation.setIds(null);
			users = userDao.getOnePage(pageInformation, databaseDao);
		}catch (SQLException e) {
			users = null;
			e.printStackTrace();
		}catch (Exception e) {
			users = null;
			e.printStackTrace();
		}
		
		return users;
	}
	
	//�û���¼
	public Integer login(User user){
		int result=-2;	//���ݿ����ʧ��	
		
		try {
			UserDao UserDao=new UserDao();
			return UserDao.login(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return result;
	}
	
	//�û�ע��
	public Integer register(User user){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			UserDao UserDao=new UserDao();			
			if(UserDao.hasUser(user, databaseDao)){
				return 0;//ʧ�ܣ��û��Ѵ���
			}else{//û��ͬ���û�������ע��
				if(UserDao.register(user, databaseDao)>0)
					return 1;	//�ɹ�
				else
					return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;//���ݿ����ʧ��
		} catch (Exception e) {
			e.printStackTrace();
			return -2;//�����쳣
		}		
	}
	
	//�޸�����
	public Integer changePassword(User user, String newPassword) {	
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			UserDao userDao=new UserDao();
			if(userDao.hasUser( user, databaseDao)){
				if(databaseDao.updateAStringFieldById("user",
						user.getUserId(),"password",newPassword)>0)
					return 1;//�޸ĳɹ�
				else
					return 0;//�û����ڣ����޸�ʧ�ܣ���������������
			}else
				return -1;//�û�������
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;//���ݿ�����
		} catch (Exception e) {
			e.printStackTrace();
			return -3;//�����쳣			
		}		
	}
	
	//��ʾ������Ϣ
	public UserInformation getByUserId(Integer userId){
		UserInformation userinformation=null;
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			UserInformationDao userinformationDao=new UserInformationDao();
			userinformation=userinformationDao.getByUserId(userId,databaseDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userinformation;
	}
	
	//�޸ĸ�����Ϣ
	public Integer updatePrivate(User user,HttpServletRequest request){	
		Integer result;
		try {
			UserInformation userinformation=new UserInformation();
			if("user".equals(user.getUserType())){
					userinformation.setUserId(user.getUserId());
			}
			String oldHeadIconUrl=user.getHeadIconUrl();
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Configure a repository (to ensure a secure temp location is used)
			String fullPath=request.getServletContext()
					.getRealPath(WebProperties.propertiesMap.get("tempDir"));//��ȡ���·���ľ���·��
			File repository = new File(fullPath);
			factory.setRepository(repository);//������ʱ�ļ���ŵ��ļ���
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// ����request�������и���Ԫ�غ��ϴ��ļ���ȡ����
		
			List<FileItem> items = upload.parseRequest(request);//items��Ÿ���Ԫ��
			
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {//������Ԫ��
			    FileItem item = iter.next();

			    if (item.isFormField()) {//���ϴ��ļ���Ԫ��			    	
			        if("sex".equals(item.getFieldName()))//��ȡ��Ԫ�ص�name����
			        	userinformation.setSex(item.getString("UTF-8"));//��ȡ��Ԫ�ص�ֵ��һ����value���ԣ�
			        if("hobby".equals(item.getFieldName()))//��ȡ��Ԫ�ص�name����
			        	userinformation.setHobby(item.getString("UTF-8"));//��ȡ��Ԫ�ص�ֵ��һ����value���ԣ�
			    } else {//�ϴ��ļ�		
			    	File uploadedFile ;
			    	String randomFileName;
			    	do{
			    		randomFileName=FileTool.getRandomFileNameByCurrentTime(item.getName());
			    		String full=request.getServletContext()
				    			.getRealPath(WebProperties.propertiesMap.get("headIconDirDefault")+"\\"
				    					+randomFileName);
			    		uploadedFile=new File(full);
			    	}while(uploadedFile.exists());//ȷ���ļ�δ����
			    	
			        item.write(uploadedFile);//����ʱ�ļ�ת��Ϊ���ļ�����
			        result=1;//��ʾ�ϴ��ļ��ɹ�
			        item.delete();//ɾ����ʱ�ļ�
			        result=2;//��ʾ�ϴ��ļ��ɹ�������ʱ�ļ�ɾ��			        
			        user.setHeadIconUrl("\\"+WebProperties.propertiesMap.get("projectName")
							+WebProperties.propertiesMap.get("headIconDirDefault")
							+"\\"+randomFileName);
			    }
			}
			DatabaseDao databaseDao=new DatabaseDao();
			UserDao userDao=new UserDao();
			UserInformationDao userinformationDao=new UserInformationDao();
			
			//��ʼ������
			databaseDao.setAutoCommit(false);
			userDao.updateHeadIcon(user, databaseDao);//�����û����ͷ��
			if("user".equals(user.getUserType())){
				userinformation.setUserId(user.getUserId());
				//��ͨ�û���userinformation��Ϣ
				if(userinformationDao.hasUserId(user.getUserId(), databaseDao))
					userinformationDao.update(userinformation, databaseDao);//�����û���ϸ��Ϣ
				else
					userinformationDao.insert(userinformation, databaseDao);//�����µ��û���ϸ��Ϣ
			}
			databaseDao.commit();
			databaseDao.setAutoCommit(true);
			result=3;//��ʾ�ϴ��ļ��ɹ�����ʱ�ļ�ɾ������·�����浽���ݿ�ɹ�
			
			if(oldHeadIconUrl.contains(FileTool.getFileName(
					WebProperties.propertiesMap.get("headIconFileDefault"))))
				result=5;////��ʾ�ϴ��ļ��ɹ�����ʱ�ļ�ɾ������·�����浽���ݿ�ɹ����ϵ�ͼƬʹ��ϵͳĬ��ͼƬ������Ҫɾ��
			else//�ϵ�ͼƬû��ʹ��ϵͳĬ��ͼƬ����Ҫɾ��
				if(FileTool.deleteFile(new File(FileTool.root.replace(
						"\\"+WebProperties.propertiesMap.get("projectName"), "")+oldHeadIconUrl)))
					result=5;////��ʾ�ϴ��ļ��ɹ�����ʱ�ļ�ɾ������·�����浽���ݿ�ɹ����ϵ�ͼƬ��ɾ��
				else
					result=4;////��ʾ�ϴ��ļ��ɹ�����ʱ�ļ�ɾ������·�����浽���ݿ�ɹ����ϵ�ͼƬ�޷���ɾ��		
		}catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}
		return  result;
	}
}
