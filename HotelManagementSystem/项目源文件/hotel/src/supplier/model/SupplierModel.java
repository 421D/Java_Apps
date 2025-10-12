package supplier.model;

import javax.swing.table.DefaultTableModel;

public class SupplierModel extends DefaultTableModel {
	Class[] types = new Class[] {java.lang.Object.class, java.lang.Object.class, 
			java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
			java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
			java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};
	//Object������Java������ȣ�����ʹ������ΪObject�ı���ָ���������͵Ķ���
	boolean[] canEdit = new boolean[] {false, false, false, false, false, false, 
										false, false, false, false, false};
	
	//��дDefaultTableModel�е�getColumnClass()�����ر����ÿһ�����ݵ���������
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	
	//��дDefaultTableModel�е�getCellEditable()�����ر���Ƿ���Ա��༭
	public boolean isCellEditable(int rowIdext, int columnIndex) {
		return canEdit[columnIndex];
	}
	
	public SupplierModel() { //���췽����
		//DefaultTableModel�Ĺ��췽��:DefaultTableModel(Object[][] data, object[] columnNames)
		//Object[][] data ����е����ݣ� object[] columnNames ����
		super(new Object[][] {}, new String[] {"���", "���֤����", "����", "�Ա�", "����", 
				"סַ", "��ϵ��ʽ", "����", "��ע"});
	}
}
