package tools;

public class PageInformation {
	private String tableName; //����
	private Integer page; //�ڼ�ҳ��ҳ��
	private Integer pageSize; //ҳ����ÿҳ���ٸ�����
	private Integer totalPage; //��ҳ��
	private Integer allRecordCount; //�ܼ�¼��
	private String orderFiled; //Ҫ������ֶ�
	private String orderType; //��������ͣ�����/����
	private String ids; //����id�����Ÿ������磺3, 5, 9������ɾ������
	private String searchSql; //��ѯ��������
	
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
