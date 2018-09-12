package tools;

import javax.servlet.http.HttpServletRequest;

public class Tool {
	//得到pageInformation的数据
	public static void getPageInformation (String tableName, HttpServletRequest request, PageInformation pageInformation) {
		pageInformation.setTableName(tableName); //设置表名
		
		String temp=request.getParameter("page");
		if(temp==null)
			pageInformation.setPage(null);
		else
			pageInformation.setPage(Integer.parseInt(temp)); //设置第几页
		
		temp=request.getParameter("pageSize");
		if(temp==null)
			pageInformation.setPageSize(null);
		else
			pageInformation.setPageSize(Integer.parseInt(temp)); //设置页长
		
		temp=request.getParameter("totalPage");
		if(temp==null)
			pageInformation.setTotalPage(null);
		else
			pageInformation.setTotalPage(Integer.parseInt(temp)); //设置总页数
		
		temp=request.getParameter("allRecordCount");
		if(temp==null)
			pageInformation.setAllRecordCount(null);
		else
			pageInformation.setAllRecordCount(Integer.parseInt(temp)); //设置总记录数
		
		pageInformation.setOrderFiled(request.getParameter("orderFiled")); //设置排序字段
		pageInformation.setOrderType(request.getParameter("orderType")); //设置排序类型
		pageInformation.setIds(request.getParameter("ids")); //设置主键id
		pageInformation.setSearchSql(request.getParameter("searchSql")); //设置Sql语句
	}
	
	//生成sql语句
	public static String getSql (PageInformation pageInformation, String type) {
		String sql = "";
		
		if(pageInformation.getIds()!=null && !pageInformation.getIds().isEmpty()) {//生成删除语句
			sql+="delete * from "+pageInformation.getTableName().toLowerCase()+"where "+
					pageInformation.getTableName().toLowerCase()+"Id in ( "+pageInformation.getIds()+") ";
		}else if("count".equals(type)) {//生成查询语句，只查询发个条件的记录数目
			sql+="select count(*) as count1 from "+pageInformation.getTableName().toLowerCase()+" ";
			//拼接查询条件
			if(pageInformation.getSearchSql()!=null && !pageInformation.getSearchSql().isEmpty()) {
				sql+="where "+pageInformation.getSearchSql()+" ";
			}
		}else if("select".equals(type)) {//生成一般查询语句
			sql+="select * from "+pageInformation.getTableName().toLowerCase()+" ";
			//拼接查询条件
			if(pageInformation.getSearchSql()!=null && !pageInformation.getSearchSql().isEmpty()) {
				sql+="where "+pageInformation.getSearchSql()+" ";
			}
			//拼接排序语句，默认为主键降序
			if(pageInformation.getOrderFiled()==null || pageInformation.getOrderFiled().isEmpty()) {
				sql+="order by "+pageInformation.getTableName()+"Id DESC ";
			}else {
				sql+="order by "+pageInformation.getOrderFiled()+" "+pageInformation.getOrderType()+" ";
			}
			//分页
			if(pageInformation.getPage()!=null &&pageInformation.getPage()>-100) {
				Integer start = (pageInformation.getPage()-1)*pageInformation.getPageSize();
				sql+="limit "+start.toString()+","+pageInformation.getPageSize()+" ";
			}
		}
		
		return sql;
	}
	
	//更新pageInformation的总页数
	public static void setPageInformation (Integer allRecordCount, PageInformation pageInformation) {
		pageInformation.setAllRecordCount(allRecordCount);
		Integer totalPage = (int) Math.ceil(1.0* allRecordCount / pageInformation.getPageSize());//ceil()为向上取整
		pageInformation.setTotalPage(totalPage);
		
		//判断是否为空
		if(pageInformation.getPage()==null)
			pageInformation.setPage(1);
		//判断是否溢出
		if(pageInformation.getPage() < 1)
			pageInformation.setPage(1);
		if(pageInformation.getPage() > totalPage)
			pageInformation.setPage(totalPage);
	}
}
