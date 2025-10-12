package server.dao;

import java.sql.*;
import server.DataConnection;
import user.bean.User;

public class UserDaoImp implements UserDao {
	//����Ҫ������Դ���н�����ʱ����ʹ������ӿڣ����ұ�дһ������������ʵ������ӿ����߼��϶�Ӧ����ض������ݴ洢��
	//SELECT * FROM tb_users WHERE username = user.getUserName() and passWord = user.getPassWord()
	DataConnection dataConnection = new DataConnection();  //�������ݿ�����
	Connection con = dataConnection.getCon();   //������ָ�����ݿ������
	@Override
	public User getUser(String userName, String passWord) {
		User user = null;  //�ֲ�����һ��Ҫ�ȳ�ʼ������ʹ��
		try {
			//ִ�о����sql���  Statement  PreparedStatement  ResultSet
			String sql = "SELECT * FROM tb_users WHERE username = ? and passWord = ?";//�����ѯ���
			PreparedStatement pst = con.prepareStatement(sql);  //ʵ����PreparedStatement����
			pst.setString(1, userName);           //����Ԥ�������Ĳ���
			pst.setString(2, passWord);
			ResultSet rest = pst.executeQuery();   //ִ��Ԥ�������
			while(rest.next()) {
				user = new User();
				user.setId(rest.getInt(1));         //Ӧ�ò�ѯ������ö�������
				user.setUserName(rest.getString(2));
				user.setPassWord(rest.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;         //���ز�ѯ���
	}
}

//UserDaoImp userDaoImp = new UserDaoImp();
//userDaoImp.getUser(userName, passWord);
//userDaoImp.getUser(admin, admin);
