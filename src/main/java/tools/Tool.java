package tools;

import javax.servlet.http.HttpServletRequest;

public class Tool {
	//�õ�pageInformation������
	public static void getPageInformation (String tableName, HttpServletRequest request, PageInformation pageInformation) {
		pageInformation.setTableName(tableName); //���ñ���
		
		String temp=request.getParameter("page");
		if(temp==null)
			pageInformation.setPage(null);
		else
			pageInformation.setPage(Integer.parseInt(temp)); //���õڼ�ҳ
		
		temp=request.getParameter("pageSize");
		if(temp==null)
			pageInformation.setPageSize(null);
		else
			pageInformation.setPageSize(Integer.parseInt(temp)); //����ҳ��
		
		temp=request.getParameter("totalPage");
		if(temp==null)
			pageInformation.setTotalPage(null);
		else
			pageInformation.setTotalPage(Integer.parseInt(temp)); //������ҳ��
		
		temp=request.getParameter("allRecordCount");
		if(temp==null)
			pageInformation.setAllRecordCount(null);
		else
			pageInformation.setAllRecordCount(Integer.parseInt(temp)); //�����ܼ�¼��
		
		pageInformation.setOrderFiled(request.getParameter("orderFiled")); //���������ֶ�
		pageInformation.setOrderType(request.getParameter("orderType")); //������������
		pageInformation.setIds(request.getParameter("ids")); //��������id
		pageInformation.setSearchSql(request.getParameter("searchSql")); //����Sql���
	}
	
	//����sql���
	public static String getSql (PageInformation pageInformation, String type) {
		String sql = "";
		
		if(pageInformation.getIds()!=null && !pageInformation.getIds().isEmpty()) {//����ɾ�����
			sql+="delete * from "+pageInformation.getTableName().toLowerCase()+"where "+
					pageInformation.getTableName().toLowerCase()+"Id in ( "+pageInformation.getIds()+") ";
		}else if("count".equals(type)) {//���ɲ�ѯ��䣬ֻ��ѯ���������ļ�¼��Ŀ
			sql+="select count(*) as count1 from "+pageInformation.getTableName().toLowerCase()+" ";
			//ƴ�Ӳ�ѯ����
			if(pageInformation.getSearchSql()!=null && !pageInformation.getSearchSql().isEmpty()) {
				sql+="where "+pageInformation.getSearchSql()+" ";
			}
		}else if("select".equals(type)) {//����һ���ѯ���
			sql+="select * from "+pageInformation.getTableName().toLowerCase()+" ";
			//ƴ�Ӳ�ѯ����
			if(pageInformation.getSearchSql()!=null && !pageInformation.getSearchSql().isEmpty()) {
				sql+="where "+pageInformation.getSearchSql()+" ";
			}
			//ƴ��������䣬Ĭ��Ϊ��������
			if(pageInformation.getOrderFiled()==null || pageInformation.getOrderFiled().isEmpty()) {
				sql+="order by "+pageInformation.getTableName()+"Id DESC ";
			}else {
				sql+="order by "+pageInformation.getOrderFiled()+" "+pageInformation.getOrderType()+" ";
			}
			//��ҳ
			if(pageInformation.getPage()!=null &&pageInformation.getPage()>-100) {
				Integer start = (pageInformation.getPage()-1)*pageInformation.getPageSize();
				sql+="limit "+start.toString()+","+pageInformation.getPageSize()+" ";
			}
		}
		
		return sql;
	}
	
	//����pageInformation����ҳ��
	public static void setPageInformation (Integer allRecordCount, PageInformation pageInformation) {
		pageInformation.setAllRecordCount(allRecordCount);
		Integer totalPage = (int) Math.ceil(1.0* allRecordCount / pageInformation.getPageSize());//ceil()Ϊ����ȡ��
		pageInformation.setTotalPage(totalPage);
		
		//�ж��Ƿ�Ϊ��
		if(pageInformation.getPage()==null)
			pageInformation.setPage(1);
		//�ж��Ƿ����
		if(pageInformation.getPage() < 1)
			pageInformation.setPage(1);
		if(pageInformation.getPage() > totalPage)
			pageInformation.setPage(totalPage);
	}
}
