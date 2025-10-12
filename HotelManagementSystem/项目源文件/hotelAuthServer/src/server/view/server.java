package server.view;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import server.TokenManager;
import server.dao.UserDaoImp;
import supplier.dao.SupplierDao;
import supplier.dao.SupplierDaoImp;
import user.bean.User;

public class server {
	public static void main(String[] args) {
		ServerFrame serverFrame = new ServerFrame();
		serverFrame.setVisible(true);
		
		//�����������������ͻ�����
		try {		
			Properties prop = new Properties();
			//��ȡ�����ļ���������
			InputStream in = Class.class.getResourceAsStream("/common/util/server.properties");
			//���������ļ�
			prop.load(in);
			//��ȡ�ļ��е����� getProperty()
			int authServerPort = Integer.parseInt(prop.getProperty("authServerPort"));
			int appServerPort =  Integer.parseInt(prop.getProperty("appServerPort"));
			String serverAddress = prop.getProperty("serverAddress");
			in.close();
			
			//�ڷ�������ע��RMIͨѶ�˿���ͨѶ·��
			//ע��ͨѶ�˿�
			LocateRegistry.createRegistry(appServerPort);
		    //ע��ͨѶ·��
			SupplierDao supplierDao = new SupplierDaoImp();
			Naming.rebind("//" + serverAddress + ":" + appServerPort + "/supplierDao", supplierDao);
		   
			
			//ÿ���������׽��������ڷ��������ض��Ķ˿ڣ���������˿�
			ServerSocket serverSocket = new ServerSocket(authServerPort);
			System.out.println("����ǰ......");
			
			//ѭ�������ͻ���
			while(true) {
				//�������˵�Socket
				//��ʱ�������˳���ͣ��accept()�ϣ�Ҳ����8000�˿ڣ�һֱ���ͻ��˳��������������ż���ִ��
				Socket socket = serverSocket.accept();
				System.out.println("������......");
				
				//���տͻ��˷��͹������û���������
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				User user = (User)ois.readObject(); //��ȡ�û���Ϣ��User�ڿͻ��˺ͷ������˵İ���һ��һ�£�
				System.out.println(user.getUserName()); //����
				System.out.println(user.getPassWord()); //����
				
				//�����ݿ�����֤�û������Ϣ
				UserDaoImp dao = new UserDaoImp();
				User userTemp = dao.getUser(user.getUserName(), user.getPassWord()); //��֤�û������Ϣ
				
				OutputStream os = socket.getOutputStream();           //�õ�Socket�Դ����ֽ���
				OutputStreamWriter osw = new OutputStreamWriter(os);  //���ֽ���ת��Ϊ�ַ���
				PrintWriter pw = new PrintWriter(osw, true);          //PrintWriter�Ǵ��л�������д����
				if(userTemp != null) {
					//��֤�ɹ�
					//pw.println("OK");	
					String token = TokenManager.istance.getToken(userTemp);
					pw.println(token);
				} else {
					//��֤ʧ��
					pw.println("Failed");
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}
}

