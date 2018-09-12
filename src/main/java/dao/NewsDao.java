package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.PageInformation;
import tools.Tool;
import bean.News;

public class NewsDao {
	//添加新闻
	public Integer add(News news,DatabaseDao databaseDao) throws SQLException{
		String sql="insert into news(caption,author,newsType,content,newsTime) values("
				+"'"+news.getCaption()+"','"
				+news.getAuthor()+"','"
				+news.getNewsType()+"','"
				+news.getContent()+"','"
				+news.getNewsTime()+"')";
		return databaseDao.update(sql);
	}
	
	//修改新闻
	public Integer edit(News news,DatabaseDao databaseDao) throws SQLException{
		String sql="update news set caption='"+news.getCaption()+"',author='"+news.getAuthor()+"',newsType='"
				+news.getNewsType()+"',content='"+news.getContent()+"',newsTime='"+news.getNewsTime()+"' "+
				"where newsId="+news.getNewsId()+" ";
		return databaseDao.update(sql);
	}
	
	//获取全部新闻
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
	
	//显示新闻:得到一页的信息
	public List<News> getOnePage(PageInformation pageInformation,DatabaseDao databaseDao) throws SQLException{
		List<News> newses=new ArrayList<News>(); 
		pageInformation.setPageSize(5); //设置每一页的长度
		String sqlCount=Tool.getSql(pageInformation,"count");
		Integer allRecordCount=databaseDao.getCount(sqlCount);//符合条件的总记录数
		Tool.setPageInformation(allRecordCount, pageInformation);//更新pageInformation的总页数等
		
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
	
	//删除新闻
	public Integer deletes(String tableName,String ids,DatabaseDao databaseDao)throws SQLException,Exception{
		return databaseDao.deletes(tableName, ids, databaseDao);
	}
	
	//由newsId获取新闻
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
