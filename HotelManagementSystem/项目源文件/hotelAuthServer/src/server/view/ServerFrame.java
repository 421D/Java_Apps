package server.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ServerFrame extends JFrame {
	private int width = 800;
	private int height = 600;
	public static LocalTableModel model = null;
	private JTable table = null;
	public static JLabel noticeLabel = null;
	public ServerFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
		this.setTitle("���������");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���ֹ�����BorderLayout
		this.setLayout(new BorderLayout());
		
		//��Ʊ�ǩ���
		JPanel fixPanel = new JPanel();
		noticeLabel = new JLabel("��ǰ����������0����ǰ�������ڴ�ռ�ã�" + 
							Runtime.getRuntime().totalMemory()/(8*1024*1024) + "MB");
		//1Byte = 8bit; 1KB = 1024Byte; 1MB = 1024KB;
		noticeLabel.setFont(new Font("΢���ź�", 0, 16));
		noticeLabel.setForeground(new Color(0, 125, 183));
		fixPanel.add(noticeLabel);
		
		//�����б����
		JScrollPane tablePanel = new JScrollPane();  //�б�Ĵ�С�ǲ��ɿص�
		//JTable������ʾ��ά���ݣ��ṩ�༭��ѡ��ȹ��ܡ�
		//JTable������Դ��DefaultTableModel������õ�����ģ�ͣ��Ķ�����JTable����ʱ���ɰ󶨡�
		model = new LocalTableModel();
		table = new JTable(model);
		table.setRowHeight(25);  //�����и�
		model.setRowCount(5);
		tablePanel.setViewportView(table);
		
		
		this.add(fixPanel, BorderLayout.NORTH);   //����ǩ�����ӵ������NORTH����
		this.add(tablePanel, BorderLayout.CENTER);//���б������ӵ������Center����
	}
	
	//�б�ģ���ڲ���
	public static class LocalTableModel extends DefaultTableModel {
		Class[] types = new Class[] {java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};
		//Object������Java������ȣ�����ʹ������ΪObject�ı���ָ���������͵Ķ���
		boolean[] canEdit = new boolean[] {false, false, false, false, false};
		
		//��дDefaultTableModel�е�getColumnClass()�����ر����ÿһ�����ݵ���������
		public Class getColumnClass(int columnIndex) {
			return types[columnIndex];
		}
		
		//��дDefaultTableModel�е�getCellEditable()�����ر���Ƿ���Ա��༭
		public boolean isCellEditable(int rowIdext, int columnIndex) {
			return canEdit[columnIndex];
		}
		
		public LocalTableModel() { //���췽����
			//DefaultTableModel�Ĺ��췽��:DefaultTableModel(Object[][] data, object[] columnNames)
			//Object[][] data ����е����ݣ� object[] columnNames ����
			super(new Object[][] {}, new String[] {"���", "�û�", "����", "����ʱ��", "ʣ��ʱ��"});
		}
	}
}
