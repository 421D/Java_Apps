package user.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;

import common.view.MainFrame;
import user.Client;
import user.bean.User;

public class LoginFrame extends JFrame {
	JTextField usernameTextField;
	JPasswordField passwordField;
	private boolean isDraging = false;    //����Ƿ��ƶ�����
	private int x, y;                     //��¼����x��y����     
	private int width = 535;
	private int height = 410;
	public static String token;
	public LoginFrame() {
		//��ȡ��Ļ���󣬽�����̶�����Ļ�����롣 //Dimension��װ��һ�������ĸ߶ȺͿ��
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//�����¼�����С������Ļ��С���ô������Ļ�ȴ�
		if(width > screenSize.width) {
			width = screenSize.width;
		}
		if(height > screenSize.height) {
			height = screenSize.height;
		}
		
		this.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
		this.setResizable(false);  //���ô����С���ɱ�
		this.setUndecorated(true); //����Ϊû�б�����

		//��һ������ӱ������JPanel......
		//JFrameΪʲô����ֱ����������   JFrame����һ����������ֻ��һ����ܣ����ֱ�������������׳��쳣��
		//��ô����� ��Ҫһ���м���������JPanel����ʱ����Ҫ���������ӵ������У�����setContentPane()��������������ΪJFrame��������塣
		BackgroundPanel backgroundPanel = getLoginPanel();   //��������ʼ���������
		
		this.setContentPane(backgroundPanel);   //�����������ӵ���¼���塣  ��backgroundPanel����ΪLoginFrame���������
	}
	
	//��¼����������
	public BackgroundPanel getLoginPanel() {
		BackgroundPanel backgroundPanel = new BackgroundPanel();  // ������¼������	
		backgroundPanel.setImage(getToolkit().getImage(getClass().getResource("../icon/login.png")));
		
		//����ʺŵı�ǩ�������
		JLabel usernameLabel = new JLabel("�û���");
		usernameLabel.setFont(new java.awt.Font("΢���ź�", 0, 16)); //�������ã�Font��ͨ�����͡���ʽ���ֺŽ��й���
		usernameLabel.setForeground(/*Color.black*/ new Color(0, 125, 183));
		usernameLabel.setBounds(423, 240, 73, 35);
		backgroundPanel.add(usernameLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(166, 240, 243, 34);
		backgroundPanel.add(usernameTextField);
		
		//�������ı�ǩ�����������
		JLabel passwordLabel = new JLabel("����");
		passwordLabel.setFont(new java.awt.Font("΢���ź�", 0, 16));
		passwordLabel.setForeground(new Color(0, 125, 183));
		passwordLabel.setBounds(423, 274, 73, 35);
		backgroundPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(166, 277, 243, 34);
		backgroundPanel.add(passwordField);

		JLabel noticeLabel = new JLabel("�����˺ź�����");
		noticeLabel.setFont(new java.awt.Font("΢���ź�", 0, 16));
		noticeLabel.setForeground(new Color(0, 125, 183));
		noticeLabel.setBounds(166, 310, 243, 34);
		backgroundPanel.add(noticeLabel);
		
		//��ӵ�¼��ť
		JButton loginButton = new JButton("��¼");
		loginButton.setBounds(166, 358, 243, 34);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = usernameTextField.getText();  
				char[] password = passwordField.getPassword();
				User user = new User();
				user.setUserName(userName);
				user.setPassWord(String.valueOf(password));
				//��һ��������user���������˽�����֤  ......δ�����
				try {
					//�ͻ��˵�Socket��new������
					//��һ��������ҪѰ�ҵķ����������ַ,127.0.0.1��ÿ̨�������ӵ�еĻػ���ַ��ָ�򱾻���Ҳ����д��localhost
					//�ڶ����������˿ں�
					Socket socket = new Socket(Client.serverAddress, Client.authServerPort);
					
					//��������˷����û���������      user
					OutputStream os = socket.getOutputStream(); //�õ�Socket�Դ����ֽ���
					ObjectOutputStream oos = new ObjectOutputStream(os); //������
					oos.writeObject(user);
					
					//���շ������˷��ص���֤��Ϣ
					InputStream is = socket.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String yorn= br.readLine();
					System.out.println(yorn); //����
					
					if(yorn.equals("Failed")) {
						JOptionPane.showMessageDialog(getContentPane(), "�û���������������������룡");
					} else if(yorn.equals("login")) {
						JOptionPane.showMessageDialog(getContentPane(), "���û��ѵ�¼��");
					}else {   //��֤�ɹ���������¼
						token = yorn;
						setVisible(false);
						MainFrame mainFrame = new MainFrame();
						mainFrame.setVisible(true);
					}
					
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		backgroundPanel.add(loginButton);
		
		//��ӡ��رա���ť
		JButton closeButton = new JButton();
		ImageIcon closeIcon = new ImageIcon(getClass().getResource("../icon/close.png"));
		closeButton.setIcon(closeIcon);
		closeButton.setBorder(null);  //ȡ���߿�
		closeButton.setBounds(508, 8, closeIcon.getIconWidth(), closeIcon.getIconHeight());
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);  //�˳�������������ж�����ڣ�ȫ�������˳���
			}		
		});
		backgroundPanel.add(closeButton);
		
		//��ӡ���С������ť
		JButton minimizeButton = new JButton();
		ImageIcon minimizeIcon = new ImageIcon(getClass().getResource("../icon/minimize.png"));
		minimizeButton.setIcon(minimizeIcon);
		minimizeButton.setBorder(null);  //ȡ���߿�
		minimizeButton.setBounds(475, 8, minimizeIcon.getIconWidth(), minimizeIcon.getIconHeight());
		minimizeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);  //������С��
			}		
		});
		backgroundPanel.add(minimizeButton);
		
		//������������1 ���ô�����ƶ�������¼���   MouseListener  MouseMotionListener
		backgroundPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) { //����
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) { //�������
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {  //�뿪���
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {  //����
				isDraging = true;
				x = e.getX();
				y = e.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {  //�ͷ�
				isDraging = false;	
			}
			
		});
		backgroundPanel.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {   //�϶�
				if(isDraging) {
					// setSize(width, height)
					// setBounds(x, y, width, height)
					// setLocation(x, y)
					int left = getLocation().x;
					int top = getLocation().y;
					setLocation(left + (e.getX()-x), top + (e.getY() - y));
				}
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {   //�ƶ�
				// TODO Auto-generated method stub
				
			}
		});
			
		return backgroundPanel;
	}
}

 //������¼���屳����
class BackgroundPanel extends JPanel {
	private Image image;
	public BackgroundPanel() {
		this.setOpaque(false);  //���ÿؼ�͸��
		this.setLayout(null);
	}
		
	//���ñ���ͼƬ����ķ���
	public void setImage(Image image) {
		this.image = image;
	}
	//��������
	protected void paintComponent(Graphics g) {
		if(image != null) {                 //���ͼƬ�Ѿ���ʼ��
			g.drawImage(image, 0, 0, this); //����ͼƬ
		}
		super.paintComponent(g);
	}
}
