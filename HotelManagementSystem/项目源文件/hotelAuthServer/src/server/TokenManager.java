package server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.UUID;
import server.view.ServerFrame;
import user.bean.User;

/*����ģʽ���裺1.�ѹ��캯��˽��2.�Լ�����һ������3.���Ⱪ¶һ�������������ȡ���洴���Ķ���*/
public class TokenManager {
	public static TokenManager istance = new TokenManager();  //2.�Լ�����һ������
	
	//�û���Token��ʣ��ʱ�� 
	//Hashtable��ϣ����ֵ��key-value�������Կ���һ�����еı��key�ǲ��ظ��ģ�����ͨ��get(key)�õ�value
	//static���εķ������߱��� �������ڶ�����з��ʣ�����ͨ������ֱ�ӷ��ʣ�����ϵͳ��ֻ����һ��
	private static Hashtable<User, String> user2token = new Hashtable<User, String>(); //�洢�û������ƵĶ�Ӧ��ϵ
	private static Hashtable<String, User> token2user = new Hashtable<String, User>(); //�洢���ƺ��û��Ķ�Ӧ��ϵ
	private static Hashtable<String, Long> token2time = new Hashtable<String, Long>(); //�洢���ƺ�ʣ��ʱ��Ĺ�ϵ
	private static Hashtable<String, String> username2token = new Hashtable<String, String>();//�洢�û��������ƵĹ�ϵ
	private static int availablePeriod = 60;     //���Ƶ���Чʱ��(��ʼʱ��)
	
	private TokenManager() {  //1.�ѹ��캯��˽��
		Thread thread = new Thread(new Thread() {  //�����ڲ���
			public void run() {
				try {
					Properties prop = new Properties();
					//��ȡ�����ļ���������
					InputStream in = Class.class.getResourceAsStream("/common/util/server.properties");
					//���������ļ�
					prop.load(in);
					//��ȡ�ļ��е����� getProperty()
					availablePeriod = Integer.parseInt(prop.getProperty("tokenTimeOut"));
					in.close();
				} catch (IOException e1) {
					System.out.println("����������ʧ��");
					e1.printStackTrace();
				}
				
				while(true) {
					long sleepTime = availablePeriod/2; //����ʱ��
					if(sleepTime<1) {
						sleepTime = 6*1000;
					}
					
					try {
						Thread.sleep(sleepTime);
						istance.checkToken();  //��ʱѭ����������Ƿ���
						
						//���Ʒ������˼�ؽ���
						//1�������ؽ�����table������ 
						while(ServerFrame.model.getRowCount() != 0) {//getRowCount()��ȡ�е�����
							ServerFrame.model.removeRow(0);  //ɾ����
						}
						//2�����»���table�е�����
						Iterator<User> iterator = user2token.keySet().iterator();
						int i = 1;     //table�е����
						long now = System.currentTimeMillis();      //��ǰʱ��
						while(iterator.hasNext()) {
							User user = iterator.next();
							String token = user2token.get(user);
							long loginTime = token2time.get(token);  //��¼ʱ��
							long last = (now - loginTime)/1000;      //����ʱ��ms-->s
							ServerFrame.model.addRow(new Object[] {i, user.getUserName(), 
									              token, last, availablePeriod-last});   //���һ��
							i++;
						}
						//3�����»��Ʊ�ǩ���������
						ServerFrame.noticeLabel.setText("��ǰ����������" + (i-1) + "��ǰ�������ڴ�ռ�ã�" + 
										Runtime.getRuntime().totalMemory()/(8*1024*1024) + "MB");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
				}
			}
		});
		thread.start();	
	}
	
	//����Token
	private String generateToken(User user) {
		return UUID.randomUUID().toString().substring(0, 16);
		//UUID(Universally Unique Identifier) ����һ���ų�ȫ��Ψһ��ID
		//randomUUID()�����UUID
		//toString()ת��Ϊ�ַ���
		//substring(int beginIndex, int endIndex)��ȡ�ַ���
	}
	
	//�洢�û������ƺ�ʣ��ʱ��(= ������Чʱ��-����ʱ�䣨��ǰʱ��-��¼ʱ�䣩)���໥��ϵ
	private synchronized void putToken(User user, String token) { //synchronizedͬ��������ͬ���߳�˳��ִ�У�
		user2token.put(user, token);
		token2user.put(token, user);
		token2time.put(token, System.currentTimeMillis());
		username2token.put(user.getUserName(), token);
	}
	
	//�����û���ȡToken --- �����֤�ɹ�֮�����
	public synchronized String getToken(User user) {
		//String token = user2token.get(user);
		String token = username2token.get(user.getUserName());
		if(token == null) {  //��һ�ε�½
			token = generateToken(user);
			putToken(user, token);
		} else {             //�ѵ�½
			token = "login";
		}
		return token;
	}
	
	//��֤Token�Ƿ���Ч --- �Ƿ��Ѿ������ĳ���û�
	public synchronized boolean verify(String token) {
		return token2user.get(token) != null;
	}
	
	//����Token��ȡ�û�
	public User getUser(String token) {
		return token2user.get(token);
	}
	
	//����Token����Чʱ������λ��s��
	public static void setAvailablePeriod(int period) {
		availablePeriod = period;
	}
	
	//��������Ƿ�ʱ  --- ʵ���ϣ������ڼ���û��Ƿ�ʱ
	private synchronized void checkToken() {  //ѭ��������е�Token
		long crt = System.currentTimeMillis(); //��ǰʱ�̵�ʱ�䣬��λ��ms
		//Iterator������������ѭ������б�Iterator���������ֳ�Ϊ�α꣬�ṩһ�ַ������ڷ���һ�������еĸ������󣬸�����ʺͱ�����
		Iterator<String> iterator = token2user.keySet().iterator();
		String token = null;
		Long time = new Long(0);    //�û��ĵ�¼ʱ��
		//ԭ����Java�У��е����������������Ķ���֮��ִ�У���������������֮��ֱ������
		//������������      long    int     byte
		//�������ͣ��ࣩ  Long   Integer  Byte
		while(iterator.hasNext()) {  //hasNext()�жϷ��ʵ������Ƿ���ڣ�����ѭ��
			token = iterator.next(); //next()���صĵ�ǰ���ʵ����ݵ�ֵ
			time = token2time.get(token);
			//�ж���Чʱ�������ʱ��֮��Ĺ�ϵ
			if( (crt-time) > availablePeriod*1000) {  //��ʱ
				token2time.remove(token);
				user2token.remove(token2user.get(token));
				username2token.remove(token2user.get(token).getUserName());
				token2user.remove(token);
			}
		}
	}
}
