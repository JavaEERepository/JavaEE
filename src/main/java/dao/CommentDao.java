package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Comment;
import tools.PageInformation;
import tools.Tool;

public class CommentDao {
	//得到一页评论
	public List<Comment> getOnePage(PageInformation pageInformation,DatabaseDao databaseDao) throws SQLException{
		List<Comment> comments=new ArrayList<Comment>(); 
		
		String sqlCount=Tool.getSql(pageInformation,"count");
		Integer allRecordCount=databaseDao.getCount(sqlCount);//符合条件的总记录数
		Tool.setPageInformation(allRecordCount, pageInformation);//更新pageInformation的总页数等
		
		if(pageInformation.getPage()==0)
			pageInformation.setPage(1);
		
		String sqlSelect=Tool.getSql(pageInformation,"select");
		databaseDao.query(sqlSelect);
		while (databaseDao.next()) {
			Comment comment=new Comment();
			comment.setCommentId(databaseDao.getInt("commentId"));
			comment.setNewsId(databaseDao.getInt("newsId"));
			comment.setUserName(databaseDao.getString("userName"));
			comment.setTime(databaseDao.getTimestamp("time"));
			comment.setContent(databaseDao.getString("content"));
			comment.setPraise(databaseDao.getInt("praise"));
			comment.setStair(databaseDao.getInt("stair"));
			comments.add(comment);	
		}		
		return comments;
	}
	
	//根据newsId得到楼层
	public Integer getStairByNewsId(Integer newsId,DatabaseDao databaseDao)throws SQLException{
		String sql="select count(*) as count1 from comment where newsId="+newsId;
		Integer stair=0;
		databaseDao.query(sql);
		while (databaseDao.next()) {
			stair=databaseDao.getInt("count1");
		}	
		return stair+1;
	}
	
	//添加评论
	public Integer addComment(Comment comment,DatabaseDao databaseDao)throws SQLException,Exception{
		String sql="insert into comment(newsId,userName,content,stair) values("
					+comment.getNewsId()+",'"+comment.getUserName()
					+"','"+comment.getContent()
					+"', "+comment.getStair()+")";
		return databaseDao.update(sql);
	}
	
	//根据评论者的ID获取评论
	public Comment getById(Integer commentId,DatabaseDao databaseDao)throws SQLException{
		databaseDao.getById("comment", commentId);
		while (databaseDao.next()) {
			Comment comment=new Comment();
			comment.setCommentId(databaseDao.getInt("commentId"));
			comment.setContent(databaseDao.getString("content"));
			comment.setNewsId(databaseDao.getInt("newsId"));
			comment.setPraise(databaseDao.getInt("praise"));
			comment.setStair(databaseDao.getInt("stair"));
			comment.setTime(databaseDao.getTimestamp("time"));
			comment.setUserName(databaseDao.getString("userName"));
			return comment;
		}
		return null;
	}
	
	//点赞
	public Integer paise(Integer commentId)throws SQLException,Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="update comment set praise=praise+1 where commentId="+commentId;
		return databaseDao.update(sql);
	}
}
