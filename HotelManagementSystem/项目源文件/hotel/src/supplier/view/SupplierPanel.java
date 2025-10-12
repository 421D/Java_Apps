package supplier.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import supplier.bean.Supplier;
import supplier.dao.TokenUnvalidException;
import supplier.model.SupplierModel;
import user.Client;
import user.view.LoginFrame;

public class SupplierPanel extends JPanel {  //�������ͱ�����
	SupplierModel model;
	JTable table;
	public SupplierPanel() {
		//�����б߿����һ������"��Ӧ����Ϣ"
		/*this.setBorder(BorderFactory.createTitledBorder(null, "������Ϣ", 
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
				new Font("΢���ź�", Font.BOLD, 12), null));*/
		this.setLayout(new BorderLayout());
		
		//����������
		JScrollPane searchPanel = new JScrollPane(); //���й����������ֻ�ܷ���1�����
		searchPanel.setPreferredSize(new Dimension(0, 80));
		JPanel search = new JPanel();   //�н�����
		search.setLayout(null);
		//"�ͻ�����"��ǩ
		JLabel nameLabel = new JLabel("������");
		nameLabel.setBounds(10, 34, 100, 15);
		search.add(nameLabel);
		//"�ͻ�����"������
		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(80, 31, 119, 25);
		nameTextField.setColumns(10);
		search.add(nameTextField);
		//"��ַ"��ǩ
		JLabel afzIDLabel = new JLabel("���֤���룺");
		afzIDLabel.setBounds(209, 34, 170, 15);
		search.add(afzIDLabel);
		//"��ַ"������
		JTextField sfzIDTextField = new JTextField();
		sfzIDTextField.setBounds(290, 31, 160, 25);
		sfzIDTextField.setColumns(10);
		search.add(sfzIDTextField);
		//"����"��ť
		JButton searchButton = new JButton("����");
		searchButton.setBounds(480, 31, 77, 23);
		search.add(searchButton);
		//"���"��ť
		JButton insertButton = new JButton("���");
		insertButton.setBounds(560, 31, 77, 23);
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddSupplierFrame insertSupplier = new AddSupplierFrame();
				insertSupplier.setVisible(true);	
				
				List<Supplier> list = null;
				try {
					list = Client.supplierDao.selectSupplier(LoginFrame.token);
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (TokenUnvalidException e) {
					JOptionPane.showMessageDialog(null, "�Ự��ʱ�������µ�¼��", 
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				model.setRowCount(0);
				for(int i=0; i<list.size();i++) {
					Supplier supplier = list.get(i);
					model.addRow(new Object[] {supplier.getId(), supplier.getSfzID(), supplier.getName(),
							supplier.getSex(), supplier.getAge(), supplier.getAddress(),
							supplier.getPhone(), supplier.getEmail(), supplier.getBeiz()});
				}
			}	
		});
		search.add(insertButton);
		//"�޸�"��ť
		JButton updateButton = new JButton("�޸�");
		updateButton.setBounds(640, 31, 77, 23);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ȡ�û�ѡ��ı����кţ��кŴ�0��ʼ����δѡ�񷵻�-1
				int row = table.getSelectedRow(); 
				if(row<0) {
					JOptionPane.showMessageDialog(getParent(), "û��ѡ��Ҫ�޸ĵ����ݣ�", 
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {	
					//��ȡҪ�޸��еĵ�һ�У������Ϊ0����Ϣ������id
					int id = Integer.parseInt(model.getValueAt(row, 0).toString());
					try {
						//��ѡ���Supplier�����idд��File
						File file = new File("file.txt");
						file.createNewFile();
						FileOutputStream out = new FileOutputStream(file);
						out.write(id);
						out.close();
						
						UpdateSupplierFrame updateSupplier = new UpdateSupplierFrame();
						updateSupplier.setVisible(true);
						
						List<Supplier> list = null;
						try {
							list = Client.supplierDao.selectSupplier(LoginFrame.token);
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (TokenUnvalidException e2) {
							JOptionPane.showMessageDialog(null, "�Ự��ʱ�������µ�¼��", 
									"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
						model.setRowCount(0);
						for(int i=0; i<list.size();i++) {
							Supplier supplier = list.get(i);
							model.addRow(new Object[] {supplier.getId(), supplier.getSfzID(), supplier.getName(),
									supplier.getSex(), supplier.getAge(), supplier.getAddress(),
									supplier.getPhone(), supplier.getEmail(), supplier.getBeiz()});
						}
						repaint();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		search.add(updateButton);
		//"ɾ��"��ť
		JButton deleteButton = new JButton("ɾ��");
		deleteButton.setBounds(720, 31, 77, 23);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ȡ�û�ѡ��ı����кţ��кŴ�0��ʼ����δѡ�񷵻�-1
				int row = table.getSelectedRow(); 
				if(row<0) {
					JOptionPane.showMessageDialog(getParent(), "û��ѡ��Ҫɾ�������ݣ�", 
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				//��ȡҪɾ���еĵ�һ�У������Ϊ0����Ϣ������id
				int id = Integer.parseInt(model.getValueAt(row, 0).toString());
				
				//����Զ�̷���ɾ�����ݿ��е���Ϣ
				try {
					Client.supplierDao.deleteSupplier(id, LoginFrame.token);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					JOptionPane.showMessageDialog(null, "�Ự��ʱ�������µ�¼��", 
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				JOptionPane.showMessageDialog(getParent(), "����ɾ���ɹ���", 
						"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
				model.removeRow(row);
				repaint();
			}
		});
		search.add(deleteButton);
		
		searchPanel.setViewportView(search);
		this.add(searchPanel, BorderLayout.NORTH);
		
		//��Ʊ�����   JTable
		model = new SupplierModel();
		table = new JTable(model);
		table.setRowHeight(25);   //�����и�
		model.setRowCount(5);
		
		//��ʼ����Ӧ���б�
		List<Supplier> list = null;
		try {
			list = Client.supplierDao.selectSupplier(LoginFrame.token);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (TokenUnvalidException e) {
			JOptionPane.showMessageDialog(null, "�Ự��ʱ�������µ�¼��", 
					"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		model.setRowCount(0);
		for(int i=0; i<list.size();i++) {
			Supplier supplier = list.get(i);
			model.addRow(new Object[] {supplier.getId(), supplier.getSfzID(), supplier.getName(),
					supplier.getSex(), supplier.getAge(), supplier.getAddress(),
					supplier.getPhone(), supplier.getEmail(), supplier.getBeiz()});
		}
		
		JScrollPane tablePanel = new JScrollPane();
		tablePanel.setViewportView(table);
		this.add(tablePanel, BorderLayout.CENTER);
	}
}
