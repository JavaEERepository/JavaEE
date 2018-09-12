package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.PageInformation;
import tools.Tool;
import bean.News;

public class NewsDao {
	//�������
	public Integer add(News news,DatabaseDao databaseDao) throws SQLException{
		String sql="insert into news(caption,author,newsType,content,newsTime) values("
				+"'"+news.getCaption()+"','"
				+news.getAuthor()+"','"
				+news.getNewsType()+"','"
				+news.getContent()+"','"
				+news.getNewsTime()+"')";
		return databaseDao.update(sql);
	}
	
	//�޸�����
	public Integer edit(News news,DatabaseDao databaseDao) throws SQLException{
		String sql="update news set caption='"+news.getCaption()+"',author='"+news.getAuthor()+"',newsType='"
				+news.getNewsType()+"',content='"+news.getContent()+"',newsTime='"+news.getNewsTime()+"' "+
				"where newsId="+news.getNewsId()+" ";
		return databaseDao.update(sql);
	}
	
	//��ȡȫ������
	public List<News> getAllNews(PageInformation pageInformation,DatabaseDao databaseDao) throws SQLException{
		List<News> newses=new ArrayList<News>(); 
		String sqlSelect=Tool.getSql(pageInformation,"select");
		databaseDao.query(sqlSelect);
		while (databaseDao.next()) {
			News news=new News();
			news.setNewsId(databaseDao.getInt("newsId"));
			news.setCaption(databaseDao.getString("caption"));
			news.setAuthor(databaseDao.getString("author"));
			news.setNewsType(databaseDao.getString("newsType"));
			news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(databaseDao.getTimestamp("publishTime"));
			newses.add(news);	
		}
		
		return newses;
	}
	
	//��ʾ����:�õ�һҳ����Ϣ
	public List<News> getOnePage(PageInformation pageInformation,DatabaseDao databaseDao) throws SQLException{
		List<News> newses=new ArrayList<News>(); 
		pageInformation.setPageSize(5); //����ÿһҳ�ĳ���
		String sqlCount=Tool.getSql(pageInformation,"count");
		Integer allRecordCount=databaseDao.getCount(sqlCount);//�����������ܼ�¼��
		Tool.setPageInformation(allRecordCount, pageInformation);//����pageInformation����ҳ����
		
		String sqlSelect=Tool.getSql(pageInformation,"select");
		databaseDao.query(sqlSelect);
		while (databaseDao.next()) {
			News news=new News();
			news.setNewsId(databaseDao.getInt("newsId"));
			news.setCaption(databaseDao.getString("caption"));
			news.setAuthor(databaseDao.getString("author"));
			news.setNewsType(databaseDao.getString("newsType"));
			news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(databaseDao.getTimestamp("publishTime"));
			newses.add(news);	
		}		
		return newses;
	}
	
	//ɾ������
	public Integer deletes(String tableName,String ids,DatabaseDao databaseDao)throws SQLException,Exception{
		return databaseDao.deletes(tableName, ids, databaseDao);
	}
	
	//��newsId��ȡ����
	public News getById(Integer newsId) throws SQLException,Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		News news=new News();
		
		databaseDao.getById("news", newsId);
		while (databaseDao.next()) {			
			news.setNewsId(databaseDao.getInt("newsId"));
			news.setCaption(databaseDao.getString("caption"));
			news.setAuthor(databaseDao.getString("author"));
			news.setNewsType(databaseDao.getString("newsType"));
			news.setContent(databaseDao.getString("content"));
			news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(databaseDao.getTimestamp("publishTime"));
		}	
		return news;
	}
}
