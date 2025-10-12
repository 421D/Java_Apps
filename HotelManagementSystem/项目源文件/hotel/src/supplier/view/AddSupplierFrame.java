package supplier.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import supplier.bean.Supplier;
import supplier.dao.TokenUnvalidException;
import user.Client;
import user.view.LoginFrame;

//ģʽ�Ի��� �� ��ģʽ�Ի���
public class AddSupplierFrame extends JDialog {  // ������Ӧ����岼��
	JPanel contentPane;
	JLabel sfzIDLabel;
	JTextField sfzIDTextField;
	JLabel nameLabel;
	JTextField nameTextField;
	JLabel sexLabel;
	JTextField sexTextField;
	JLabel ageLabel;
	JTextField ageTextField;
	JLabel addressLabel;
	JTextField addressTextField;
	JLabel phoneLabel;
	JTextField phoneTextField;
	JLabel emailLabel;
	JTextField emailTextField;
	JLabel beizLabel;
	JTextArea beizTextArea;
	
	JLabel starLabel;
	JLabel starLabel_1;
	JLabel starLabel_2;
	JLabel starLabel_3;
	JLabel starLabel_4;
	JLabel starLabel_5;
	JLabel starLabel_6;
	JButton insertButton;
	JButton closeButton;
	
	public AddSupplierFrame() {
		this.setModal(true);   //ģ̬����ʼ������ǰ�棬���������߳�ֱ���Ի����߳̽���
		this.setResizable(false);
		this.setTitle("��ӿͻ�����");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((screenSize.width - 800) / 2, (screenSize.height - 475) / 2, 800, 475);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		this.setContentPane(contentPane);

		sfzIDLabel = new JLabel("���֤����:");
		sfzIDLabel.setBounds(49, 97, 100, 25);
		contentPane.add(sfzIDLabel);
		sfzIDTextField = new JTextField();
		sfzIDTextField.setBounds(142, 94, 184, 25);
		sfzIDTextField.setColumns(10);
		contentPane.add(sfzIDTextField);
		
		nameLabel = new JLabel("������");
		nameLabel.setBounds(49, 53, 100, 25);
		contentPane.add(nameLabel);
		nameTextField = new JTextField();
		nameTextField.setBounds(142, 50, 184, 25);
		nameTextField.setColumns(10);
		contentPane.add(nameTextField);

		sexLabel = new JLabel("�Ա�:");
		sexLabel.setBounds(385, 97, 100, 25);
		contentPane.add(sexLabel);
		sexTextField = new JTextField();
		sexTextField.setBounds(483, 94, 184, 25);
		sexTextField.setColumns(10);
		contentPane.add(sexTextField);
		
		ageLabel = new JLabel("����:");
		ageLabel.setBounds(49, 140, 100, 25);
		contentPane.add(ageLabel);
		ageTextField = new JTextField();
		ageTextField.setBounds(142, 137, 184, 25);
		ageTextField.setColumns(10);
		contentPane.add(ageTextField);
		
		addressLabel = new JLabel("�ͻ�סַ��");
		addressLabel.setBounds(385, 53, 100, 25);
		contentPane.add(addressLabel);
		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(483, 50, 184, 25);
		contentPane.add(addressTextField);

		phoneLabel = new JLabel("��ϵ��ʽ:");
		phoneLabel.setBounds(385, 140, 100, 25);
		contentPane.add(phoneLabel);
		phoneTextField = new JTextField();
		phoneTextField.setBounds(483, 137, 184, 25);
		phoneTextField.setColumns(10);
		contentPane.add(phoneTextField);

		emailLabel = new JLabel("����:");
		emailLabel.setBounds(49, 180, 100, 25);
		contentPane.add(emailLabel);
		emailTextField = new JTextField();
		emailTextField.setBounds(142, 177, 184, 25);
		emailTextField.setColumns(10);
		contentPane.add(emailTextField);

		beizLabel = new JLabel("��ע:");
		beizLabel.setBounds(49, 294, 100, 25);
		contentPane.add(beizLabel);
		beizTextArea = new JTextArea();
		beizTextArea.setBounds(142, 267, 525, 89);
		contentPane.add(beizTextArea);

		// Ϊ��Ҫ��֤����������*�ű��
		starLabel = new JLabel("*");
		starLabel.setForeground(Color.RED);
		starLabel.setBounds(330, 53, 6, 15);
		contentPane.add(starLabel);
		starLabel_1 = new JLabel("*");
		starLabel_1.setForeground(Color.RED);
		starLabel_1.setBounds(680, 53, 18, 15);
		contentPane.add(starLabel_1);
		starLabel_2 = new JLabel("*");
		starLabel_2.setForeground(Color.RED);
		starLabel_2.setBounds(330, 97, 18, 15);
		contentPane.add(starLabel_2);
		starLabel_3 = new JLabel("*");
		starLabel_3.setForeground(Color.RED);
		starLabel_3.setBounds(680, 97, 18, 15);
		contentPane.add(starLabel_3);
		starLabel_4 = new JLabel("*");
		starLabel_4.setForeground(Color.RED);
		starLabel_4.setBounds(330, 140, 18, 15);
		contentPane.add(starLabel_4);
		starLabel_5 = new JLabel("*");
		starLabel_5.setForeground(Color.RED);
		starLabel_5.setBounds(330, 180, 18, 15);
		contentPane.add(starLabel_5);
		starLabel_6 = new JLabel("*");
		starLabel_6.setForeground(Color.RED);
		starLabel_6.setBounds(330, 223, 18, 15);
		contentPane.add(starLabel_6);

		// Ϊ������ť��Ӱ�ť�¼�������
		insertButton = new JButton("���");
		insertButton.setBounds(255, 388, 93, 23);
		contentPane.add(insertButton);
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sfzID = sfzIDTextField.getText();
				String name = nameTextField.getText();
				String sex = sexTextField.getText();
				String age = ageTextField.getText();
				String address = addressTextField.getText();
				String phone = phoneTextField.getText();
				String email = emailTextField.getText();
				String beiz = beizTextArea.getText();
				
				//��֤������ֵ
				if(sfzID.equals("") || name.equals("") || sex.equals("") ||
						age.equals("") || address.equals("") || 
						phone.equals("") || email.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "�����Ǻŵ���Ϣ��д����", 
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				Supplier supplier = new Supplier();
				supplier.setSfzID(sfzID);
				supplier.setName(name);
				supplier.setSex(sex);
				supplier.setAge(age);
				supplier.setAddress(address);
				supplier.setPhone(phone);
				supplier.setEmail(email);
				supplier.setBeiz(beiz);
				
				
				try {
					Client.supplierDao.insertSupplier(supplier, LoginFrame.token);
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (TokenUnvalidException e) {
					JOptionPane.showMessageDialog(null, "�Ự��ʱ�������µ�¼��", 
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				JOptionPane.showMessageDialog(getParent(), "������ӳɹ���", "��Ϣ��ʾ��", 
						JOptionPane.INFORMATION_MESSAGE);
				do_closeButton_actionPerformed(arg0);
			}			
		});

		// Ϊ�˳���ť��Ӱ�ť�¼�������
		closeButton = new JButton("�˳�");
		closeButton.setBounds(385, 388, 93, 23);
		contentPane.add(closeButton);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
	}

	// ���ùرհ�ť����
	protected void do_closeButton_actionPerformed(ActionEvent e) {
		this.setVisible(false);   //���ش���,����û�����ر�
		//this.dispose();             //�������ڣ������ͷŴ���ʹ�õ���Դ		
	}
}