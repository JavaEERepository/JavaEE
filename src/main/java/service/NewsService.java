package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.PageInformation;
import dao.DatabaseDao;
import dao.NewsDao;
import bean.News;

public class NewsService {
	//添加新闻
	public Integer add(News news){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			NewsDao newsDao=new NewsDao();
			return newsDao.add( news, databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}
	
	//修改新闻
	public Integer edit(News news) {
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			NewsDao newsDao=new NewsDao();
			return newsDao.edit(news, databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}
	
	//获取全部新闻
	public List<News> getAllNews(PageInformation pageInformation) {	
		List<News> newses=new ArrayList<News>();
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			NewsDao newsDao=new NewsDao();		
			newses=newsDao.getAllNews(pageInformation,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return newses;
	}
	
	//显示新闻:得到一页的信息
	public List<News> getOnePage(PageInformation pageInformation) {	
		List<News> newses=new ArrayList<News>();
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			NewsDao newsDao=new NewsDao();		
			newses=newsDao.getOnePage(pageInformation,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return newses;
	}
	
	//删除新闻
	public List<News> deletes(PageInformation pageInformation) {	
		List<News> newses=null;
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			NewsDao newsDao=new NewsDao();
			newsDao.deletes(pageInformation.getTableName(),pageInformation.getIds(),databaseDao);
			pageInformation.setIds(null);
			newses=newsDao.getOnePage(pageInformation,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return newses;
	}
	
	//由newsId获取新闻
	public News getNewsById(Integer newsId){
		NewsDao newsDao=new NewsDao();		
		try {
			return newsDao.getById(newsId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
