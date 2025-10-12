package common.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import Seller.view.SellerPanel;
import supplier.view.SupplierPanel;


public class MainFrame extends JFrame{
	//���񣺴����С 1000*600 ����λ����Ļ���룬����Ϊ�ɵ��ڴ�С
	private int width = 1000;
	private int height = 600;
	private JMenuBar menuBar = null;  //�˵���
	SmallScrollPanel moduleButtonGroup = null;   //���˵���ť����
	JTree tree = null;
	JPanel messagePanel = null;
	public MainFrame() {
		//��ȡ��Ļ���󣬽����嶨λ����Ļ����
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if(width > screenSize.width) {
			width = screenSize.width;
		}
		if(height > screenSize.height) {
			height = screenSize.height;
		}
		this.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
		this.setResizable(true);   //����Ϊ�ɵ��ڴ�С
		this.setTitle("�Ƶ����ϵͳ");
		URL url = getClass().getResource("../icon/hotel.png");
		this.setIconImage(new ImageIcon(url).getImage());
		
		//���ڴ���رյ����⣺
		//JFrame�������ϵĹرհ�ť֮�رմ��ڣ����رճ���
		//setDefaultCloseOperation()���õ����رհ�ť�Ĳ�����
		// 1 EXIT_ON_CLOSE �˳�Ӧ�ó��򣨵���Sysytem.exit(0);��
		// 2 HIDE_ON_CLOSE ���ش��� (Ĭ��)
		// 3
		// 4
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//������������1 ���ò˵���  2 ���ô���Ĳ��ַ�ʽ  3 ���˵���ť��������
		
		// ���ò˵���
		this.setJMenuBar(getMenus());
		
		//���ô���Ĳ��ַ�ʽ����BorderLayout
		/*//����һ     ��һ���м�����
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(getContentPanel(), BorderLayout.CENTER);
		contentPane.add(getModuleButtonGroup(), BorderLayout.NORTH);
		this.add(contentPane);
		*/
		
		//������   JFrame��һ���������
		this.getContentPane().setLayout(new BorderLayout());
		//this.getContentPane().setBackground(Color.BLACK);     //����
		//��Center������ӹ����ռ����
		this.getContentPane().add(getContentPanel(), BorderLayout.CENTER);
		//��North����������˵���ť�������
		this.getContentPane().add(getModuleButtonGroup(), BorderLayout.NORTH);
	}
	
	//��Ʋ˵���
	private JMenuBar getMenus() {
		if(menuBar == null) {         //��׳��
			//�����˵�����  ������Ҫ��Ӳ˵����������Ҫ��Ӳ˵�����
			menuBar = new JMenuBar();
			//����һ���˵�
			JMenu m1 = new JMenu("�ļ�");
			JMenu m2 = new JMenu("����");
			//���������˵�
			JMenuItem mi11 = new JMenuItem("�˳�");
			JMenuItem mi21 = new JMenuItem("����");
			//�������˵���ӵ�һ���˵�
			m1.add(mi11);
			m2.add(mi21);
			//��һ���˵���ӵ��˵���
			menuBar.add(m1);
			menuBar.add(m2);
			
			//�������˵�ע���¼������� ActionListener
			mi11.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}	
			});
			mi21.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(getContentPane(), "��������");
				}
			});
		}
		return menuBar;
	}
	
	//������˵���ť�������
	private SmallScrollPanel getModuleButtonGroup() {
		if(moduleButtonGroup == null) {
			moduleButtonGroup = new SmallScrollPanel();
			//moduleButtonGroup.setBackground(Color.YELLOW); //����
		}
		return moduleButtonGroup;
	}
	
	//��ƹ����ռ����
	static JPanel workspacePanel = null;  //workspacePanel��Ϊ�����֣���������Ŀ¼���  �Ҳ���������
	static JPanel panel_1;
	static JPanel panel_2;
	private JPanel getContentPanel() {
		if(workspacePanel == null) {
			workspacePanel = new JPanel();
			//�����б߿����һ������  createTitledBorder()
		workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "������Ϣ����", 
					TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
					new Font("΢���ź�", Font.BOLD, 12), null));
			workspacePanel.setLayout(new BorderLayout());   //���ַ�ʽ
			
			messagePanel = new JPanel();   //�н��������Ҳ����Ϣ���
			
			panel_1=new JPanel();
			panel_1.setLayout(new BorderLayout());
			//panel_1.add(treePanel, BorderLayout.WEST);
			panel_1.add(messagePanel, BorderLayout.CENTER);
			
			
			//workspacePanel.add(treePanel, BorderLayout.WEST);	
			
			//����Ҳ��������壨����ƹ�Ӧ�̹���ģ�飩
			messagePanel.setLayout(new BorderLayout());
			SupplierPanel supplierPanel = new SupplierPanel();
			messagePanel.add(supplierPanel,BorderLayout.CENTER);
			workspacePanel.add(panel_1, BorderLayout.CENTER);
			
			
			
		}
		return workspacePanel;
	}
}
