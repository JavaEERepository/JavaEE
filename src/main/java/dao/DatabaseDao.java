package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DatabaseDao {
	public static String drv;//���ݿ�����
	public static String url;//���ݿ���ַ
	public static String usr;//�û���
	public static String pwd;//����
	
	private Connection connect = null;
	private Statement stmt=null;
	private ResultSet rs = null;
	
	//���캯�������ӵ����ݿ�
	public DatabaseDao()  throws Exception{
		Class.forName(drv);
		connect = DriverManager.getConnection(url, usr, pwd);
		stmt = connect.createStatement();			
	}
	
	//ִ�в�ѯ
	public void  query(String sql) throws SQLException{
		rs = stmt.executeQuery(sql);
	}

	//ִ�и���
	public int  update(String sql) throws SQLException{
		return stmt.executeUpdate(sql);
	}	
	
	//rs����һ����¼�Ƿ����
	public boolean next() throws SQLException{
		return rs.next();
	}
	/*-----------------------------------------------------------------------------*/
	
	//��ȡ�ַ��������ֶε�ֵ���ֶ�ֵΪnull�͵ģ����տ��ַ�������
	public String getString(String field) throws SQLException{
		return rs.getString(field);
	}
	
	//��ȡ���������ֶε�ֵ
	public Integer getInt(String field) throws SQLException{
		return rs.getInt(field);
	}
	
	//��ȡ���������ֶε�ֵ
	public Timestamp getTimestamp(String field) throws SQLException{
		return rs.getTimestamp(field);
	}	
	
	//��ȡʵ�������ֶε�ֵ
	public Float getFloat(String field) throws SQLException{
		return rs.getFloat(field);
	}
	
	//��ѯ���������ļ�¼����Ŀ
	public int getCount(String sql) throws SQLException{
		query(sql);
		while (next()) {
			return this.getRs().getInt("count1");
		}
		return 0;
	}	
	
	// ��ȡ����ֶ����ƣ������浽������
	public ArrayList<String> FieldsList(String tableName) throws SQLException{
		ArrayList<String> fieldList = new ArrayList<String>();
		String sql = "select * from " + tableName + " limit 1";// limit 1��ʾ��ѯ���ֻ����һ����¼
		query(sql);
		ResultSetMetaData fields = rs.getMetaData();//ResultSetMetaData��¼�˱��Ԫ���ݣ����ֶ����ƣ��ֶ����͵�
		
		for (int i = 1; i < fields.getColumnCount() + 1; i++) {//getColumnCount������ȡ�ֶ�����
			fieldList.add(fields.getColumnName(i));//getColumnName(i)��ȡ�ֶ�����
		}
		return fieldList;
	}
	
	//���ݱ��ֶΣ���ȡ���ֶ������з��ظ�ֵ
	public ArrayList<String> getStringFieldValueByTableAndField(String tableName,
			String fieldName) throws SQLException{
		ArrayList<String> FieldValueList = new ArrayList<String>();
		query("select distinct " + fieldName + " from " + tableName);
		
		while (next()) {
			FieldValueList.add(getString(fieldName));
		}

		return FieldValueList;
	}
	
	//�޸�ĳ����ĳ����¼��id����ĳ���ַ����ֶε�ֵ
	public Integer updateAStringFieldById(String tableName,Integer id,
		String fieldName,String fieldValue)throws SQLException{
		String sql="update "+tableName+" set "+fieldName+"='"+fieldValue+"' where "+
				tableName.toLowerCase()+"Id="+id.toString();
		return update(sql);
	}
	
	//��ȡ����ʱ�������ֶε�ֵ
	public LocalDateTime getLocalDateTime(String field) throws SQLException{
		return rs.getTimestamp(field).toLocalDateTime();
	}
	
	//ɾ������û�
	public Integer deletes(String tableName,String ids,DatabaseDao databaseDao)throws SQLException{//��ѯʧ�ܷ���-1
		if(ids!=null && ids.length()>0){
			String sql = "delete from "+tableName+" where "+tableName.toLowerCase()+"Id in ("+ids+")";
			return databaseDao.update(sql);
		}else
			return -1;
	}
	
	//����id��ȡ
	public void getById(String tableName, Integer id) throws SQLException{
		tableName=tableName.toLowerCase();
		String sql="select * from "+tableName+" where "+tableName+"Id="+id.toString();
		query(sql);
	}
	
	/*-----------------------------------------------------------------------------*/
	public void setAutoCommit(boolean f) throws SQLException{
		connect.setAutoCommit(f);
	}
	
	public void commit() throws SQLException{
		connect.commit();
	}
	
	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
	
}
