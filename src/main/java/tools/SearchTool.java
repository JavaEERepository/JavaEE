package tools;

import javax.servlet.http.HttpServletRequest;

public class SearchTool {
	//����һ���ַ������ѯ����:ģ����ѯ
	private static String addStringFuzzyAnd(String fieldName,String searchSql,HttpServletRequest request){
		String param=request.getParameter(fieldName);
		if(param!=null && !param.isEmpty()&& !param.equals("all")){
			if(searchSql.length()>1){//�Ѿ���һ����������Ҫ��  and
				searchSql+=" and "+fieldName +" like '%"+param+"%' ";
			}else{
				searchSql+=" "+fieldName +" like '%"+param+"%' ";
			}				
		}
		return  searchSql;
	}
	//����һ���ַ������ѯ����:��ȷ��ѯ
	private static String addStringAnd(String fieldName,String searchSql,HttpServletRequest request){
		String param=request.getParameter(fieldName);
		if(param!=null && !param.isEmpty()&& !param.equals("all")){
			if(searchSql.length()>1){//�Ѿ���һ����������Ҫ��  and
				searchSql+=" and "+fieldName +"='"+param+"' ";
			}else{
				searchSql+=" "+fieldName +"='"+param+"' ";
			}				
		}
		return  searchSql;
	}	
	//����һ���������ѯ����:>=lowDate  and <upDate
	private static String addDateAnd(String fieldName,String lowDateName,String upDateName,String searchSql,HttpServletRequest request){
		String lowDate=request.getParameter(lowDateName);
		String upDate=request.getParameter(upDateName);
		
		if(lowDate!=null && !lowDate.isEmpty()){
			if(searchSql.length()>1){//�Ѿ���һ����������Ҫ��  and
				searchSql+=" and "+fieldName +">='"+lowDate+"' ";
			}else{
				searchSql+=" "+fieldName +">='"+lowDate+"' ";
			}				
		}
		
		if(upDate!=null && !upDate.isEmpty()){
			if(searchSql.length()>1){//�Ѿ���һ����������Ҫ��  and
				searchSql+=" and "+fieldName +"<'"+upDate+"' ";
			}else{
				searchSql+=" "+fieldName +"<'"+upDate+"' ";
			}				
		}		
		return  searchSql;
	}		
	//�û���Ĳ�ѯ����
	public static String user(HttpServletRequest request){
		String searchSql="";
		searchSql=addStringAnd("userType",searchSql,request);		
		searchSql=addStringFuzzyAnd("userName",searchSql,request);
		searchSql=addStringAnd("isUse",searchSql,request);
		searchSql=addDateAnd("registrationDate","lowDate","upDate",searchSql,request);

		return searchSql;
	}
}