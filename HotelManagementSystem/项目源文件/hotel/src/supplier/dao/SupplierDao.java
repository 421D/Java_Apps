package supplier.dao;

import java.rmi.*;
import java.util.List;
import supplier.bean.Supplier;

public interface SupplierDao extends Remote { //��չ�ӿ�
	//��ӹ�Ӧ����Ϣ�ķ���
	public void insertSupplier(Supplier supplier, String token) 
			throws RemoteException, TokenUnvalidException;
	//��ѯ��Ӧ�̱���ȫ�����ݵķ���
	public List<Supplier> selectSupplier(String token) 
			throws RemoteException, TokenUnvalidException; 
	//����Ų�ѯ��Ӧ����Ϣ�ķ���
	public Supplier selectSupplierbyid(int id, String token) 
			throws RemoteException, TokenUnvalidException;  
	//���ͻ���ַ��ѯ��Ӧ����Ϣ�ķ���
	public List<Supplier> selectSupplierByAddress(String address, String token) 
			throws RemoteException, TokenUnvalidException;   
	//���ͻ����Ʋ�ѯ��Ӧ����Ϣ�ķ���
	public List<Supplier> selectSupplierByName(String name, String token) 
			throws RemoteException, TokenUnvalidException; 
	//���ͻ����ƺͿͻ���ַ��ѯ��Ӧ����Ϣ�ķ���
	public List<Supplier> selectSupplierByNameAddress(String address, String name, 
			String token) throws RemoteException, TokenUnvalidException; 
	//�޸Ĺ�Ӧ����Ϣ�ķ���
	public void updateSupplier(Supplier supplier, String token) 
			throws RemoteException, TokenUnvalidException;  
	//ɾ����Ӧ����Ϣ�ķ���
	public void deleteSupplier(int id, String token) 
			throws RemoteException, TokenUnvalidException; 
}
