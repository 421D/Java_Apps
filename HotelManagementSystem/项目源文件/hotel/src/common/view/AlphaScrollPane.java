package common.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import purchase.view.PurchasePanel;

public class AlphaScrollPane extends JScrollPane {
	//private GlassButton jibendanganButton = null;    //����������ť
	private GlassButton kerenguanliButton = null;  //������Ϣ����ť
	private GlassButton kerenzhufangButton = null;           //����ס������ť
	private GlassButton fangjianButton = null;          //�������ť
	private GlassButton jiucanButton = null;          //�Ͳ͹���ť
	private GlassButton renyuanButton = null;        //��Ա����ť
	public AlphaScrollPane() {
		super();   //���ø���Ĺ��캯��
		this.setBackground(new Color(151, 188,229));
		
		//���6����ť���Ƚ�6����ť������һ��JPanel�ϣ��ڽ�JPanel��Ϊһ��������ӵ�JScrollPane�ϡ�
		JPanel scrollPanel = new JPanel();
		scrollPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 38, 8));
		
		//scrollPanel.add(getJibendanganButton());  
		scrollPanel.add(getKerenguanliButton());
		scrollPanel.add(getKerenzhufangButton());
		scrollPanel.add(getFangjianButton());
		scrollPanel.add(getJiucanButton());
		scrollPanel.add(getumenButton());
		
		//ButtonGroup����Ϊһ�鰴ť����һ�����������
		//ʹ����ͬ��ButtonGroup��ζ�š�����������һ����ťʱ�����ر����е��������а�ť��
		//JButton��JMenuItem���밴ť��û���κ����塣   JToggleButton
		ButtonGroup buttonGroup = new ButtonGroup();
		//buttonGroup.add(getJibendanganButton());
		buttonGroup.add(getKerenguanliButton());
		buttonGroup.add(getumenButton());
		buttonGroup.add(getFangjianButton());
		buttonGroup.add(getJiucanButton());
		buttonGroup.add(getKerenzhufangButton());
		
		this.setViewportView(scrollPanel); //����ť���������Ϊ����������ͼ
	}
	
	//��ư�ť
	/*private GlassButton getJibendanganButton() {
		if(jibendanganButton == null) {
			jibendanganButton = new GlassButton();	
			URL url = getClass().getResource("../icon/eat.png");
			jibendanganButton.setIcon(new ImageIcon(url));  //���ð�ť��ͼ��
			jibendanganButton.setText("��������");
			jibendanganButton.setFont(new Font("΢���ź�", 1, 12));
			jibendanganButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "������������", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("΢���ź�", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(MainFrame.panel_1);
					repaint();
				}
				
			});
		}	
		return jibendanganButton; 
	}*/
	private GlassButton getKerenguanliButton() {
		if(kerenguanliButton == null) {
			kerenguanliButton = new GlassButton();	
			URL url = getClass().getResource("../icon/keren.png");
			kerenguanliButton.setIcon(new ImageIcon(url));  //���ð�ť��ͼ��
			kerenguanliButton.setText("������Ϣ����");
			kerenguanliButton.setFont(new Font("΢���ź�", 1, 12));
			kerenguanliButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO �Զ����ɵķ������
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "������Ϣ����", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("΢���ź�", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(purchasePanel, BorderLayout.CENTER);
					MainFrame.workspacePanel.add(MainFrame.panel_1);
					repaint();
				}
				
			});
		}	
		return kerenguanliButton; 
	}
	private GlassButton getKerenzhufangButton() {
		if(kerenzhufangButton == null) {
			kerenzhufangButton = new GlassButton();	
			URL url = getClass().getResource("../icon/zuf.png");
			kerenzhufangButton.setIcon(new ImageIcon(url));  //���ð�ť��ͼ��
			kerenzhufangButton.setText("����ס������");
			kerenzhufangButton.setFont(new Font("΢���ź�", 1, 12));
			kerenzhufangButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "����ס������", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("΢���ź�", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(purchasePanel, BorderLayout.CENTER);
					
					repaint();
				}});
		}	
		return kerenzhufangButton; 
	}
	private GlassButton getFangjianButton() {
		if(fangjianButton == null) {
			fangjianButton = new GlassButton();	
			URL url = getClass().getResource("../icon/fangjian.png");
			fangjianButton.setIcon(new ImageIcon(url));  //���ð�ť��ͼ��
			fangjianButton.setText("������Ϣ����");
			fangjianButton.setFont(new Font("΢���ź�", 1, 12));
			fangjianButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "������Ϣ����", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("΢���ź�", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(purchasePanel, BorderLayout.CENTER);
					
					repaint();
				}});
		}	
		return fangjianButton; 
	}
	private GlassButton getJiucanButton() {
		if(jiucanButton == null) {
			jiucanButton = new GlassButton();	
			URL url = getClass().getResource("../icon/eat.png");
			jiucanButton.setIcon(new ImageIcon(url));  //���ð�ť��ͼ��
			jiucanButton.setText("�Ͳ͹���");
			jiucanButton.setFont(new Font("΢���ź�", 1, 12));
			jiucanButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "�Ͳ͹���", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("΢���ź�", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(purchasePanel, BorderLayout.CENTER);
					
					repaint();
				}});
			
		}	
		return jiucanButton; 
	}
	private GlassButton getumenButton() {
		if(renyuanButton == null) {
			renyuanButton = new GlassButton();	
			URL url = getClass().getResource("../icon/reygl.png");
			renyuanButton.setIcon(new ImageIcon(url));  //���ð�ť��ͼ��
			renyuanButton.setText("��Ա����");
			renyuanButton.setFont(new Font("΢���ź�", 1, 12));
			renyuanButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "��Ա����", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("΢���ź�", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(purchasePanel, BorderLayout.CENTER);
					
					repaint();
				}});
		}	
		return renyuanButton; 
	}
}
