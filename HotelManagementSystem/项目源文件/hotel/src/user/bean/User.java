package user.bean;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;           //����ӳ������������
	private String userName;  //����ӳ���û���������
	private String passWord;  //����ӳ�����������
	public int getId() {      //id���Ե�get()����
		return id;
	}
	public void setId(int id) { //id���Ե�set()����
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
