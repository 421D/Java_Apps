package server;

import java.sql.*;

public class DataConnection {
	private String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://localhost:1433;DatabaseName = hotel";
	private String user = "sa";
	private String password = "123456";
	private Connection cn;
	private PreparedStatement pstm;
	//�������ݿ�����
	public DataConnection() {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.out.println("�������ݿ�����ʧ��!");
			e.printStackTrace();
		}
	}
	
	//��ȡ���ݿ�����
	public Connection getCon() {
		try {
			cn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("�������ݿ�����ʧ��!");
			e.printStackTrace();
		}
		return cn;  //�������ݿ����Ӷ���
	}
	
	
	//ִ��SQL���(����paramsΪ���ݵĲ���ֵ����)
	public void doPstm(String sql, Object[] params) {
		if(sql != null && !sql.equals("")) {
			if(params == null) {
				params = new Object[0];
			}
			getCon();
			try {
				pstm = cn.prepareStatement(sql);
				for(int i = 0; i < params.length; i++) {  //�������ò���
					pstm.setObject(i+1, params[i]);
				}
				pstm.execute();
			} catch (SQLException e) {
				System.out.println("doPstm()��������");
				e.printStackTrace();
			}
		}
	}
	
	//���ز�ѯ���
	public ResultSet getRs() throws SQLException {
		return pstm.getResultSet();
	}
	
	//��ȡ��Ӱ�����������
	public int getCount() throws SQLException {
		return pstm.getUpdateCount();	
	}
	
	//�ر����ݿ�����
	public void closed() {
		try {
			if(cn != null) {
				cn.close();
			}
		} catch (SQLException e) {
			System.out.println("�ر�Connection����ʧ��");
			e.printStackTrace();
		}
		
		
		try {
			if(pstm != null) {
				pstm.close();
			}
		} catch (SQLException e) {
			System.out.println("�ر�PrepardeStatement����ʧ��");
			e.printStackTrace();
		}	
	}
}
