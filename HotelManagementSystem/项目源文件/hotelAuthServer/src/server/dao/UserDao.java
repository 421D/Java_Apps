package server.dao;

import user.bean.User;

public interface UserDao {
	//�����ж�����Դ�ķ��ʲ���
	//SELECT * FROM tb_users WHERE username = user.getUserName() and passWord = user.getPassWord()
	public User getUser(String userName, String passWord);
}
