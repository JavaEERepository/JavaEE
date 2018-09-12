package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Comment;
import dao.CommentDao;
import dao.DatabaseDao;
import tools.PageInformation;

public class CommentService {
	//得到一页评论
	public List<Comment> getOnePage(PageInformation pageInformation) {	
		List<Comment> comments=new ArrayList<Comment>();
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			CommentDao commentDao=new CommentDao();		
			comments=commentDao.getOnePage(pageInformation,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return comments;
	}
	
	//对新闻的回复
	public Integer addComment(Comment comment){
		CommentDao commentDao=new CommentDao();		
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			Integer stair=commentDao.getStairByNewsId(comment.getNewsId(),databaseDao);
			comment.setStair(stair);
			return commentDao.addComment(comment,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
	}
	
	//对评论的回复
	public Integer addCommentToComment(Comment comment){
		CommentDao commentDao=new CommentDao();		
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			Comment oldComment=commentDao.getById(comment.getCommentId(),databaseDao);
			String s="回复第"+oldComment.getStair()+"楼层&nbsp;"
						+oldComment.getUserName()+"&nbsp;的留言：<br>";
			comment.setContent(s+comment.getContent());
			Integer stair=commentDao.getStairByNewsId(comment.getNewsId(),databaseDao);
			comment.setStair(stair);
			return commentDao.addComment(comment,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
	}
	
	//点赞
	public Integer paise(Integer commentId){
		try {
			CommentDao commentDao=new CommentDao();
			if(commentDao.paise(commentId)>0)
				return 1;//
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;//
		} catch (Exception e) {			
			e.printStackTrace();
			return -3;//
		}	
	}
}
