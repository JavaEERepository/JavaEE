package tools;

public class PageInformation {
	private String tableName; //表名
	private Integer page; //第几页，页码
	private Integer pageSize; //页长，每页多少个数据
	private Integer totalPage; //总页数
	private Integer allRecordCount; //总记录数
	private String orderFiled; //要排序的字段
	private String orderType; //排序的类型：升序/降序
	private String ids; //主键id，逗号隔开，如：3, 5, 9；用于删除操作
	private String searchSql; //查询语句的条件
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getAllRecordCount() {
		return allRecordCount;
	}
	public void setAllRecordCount(Integer allRecordCount) {
		this.allRecordCount = allRecordCount;
	}
	public String getOrderFiled() {
		return orderFiled;
	}
	public void setOrderFiled(String orderFiled) {
		this.orderFiled = orderFiled;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getSearchSql() {
		return searchSql;
	}
	public void setSearchSql(String searchSql) {
		this.searchSql = searchSql;
	}
	
}
