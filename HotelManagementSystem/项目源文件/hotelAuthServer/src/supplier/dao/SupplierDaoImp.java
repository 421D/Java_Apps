package supplier.dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import server.DataConnection;
import server.TokenManager;
import server.TokenUnvalidException;
import supplier.bean.Supplier;

//����һ����ʵ��Զ�̽ӿڼ�Զ�̷�������Ҫ�̳�UnicastRemoteObject��ʹ��UnicastRemoteObjectȥ����RMIϵͳ��
//���һ����̳���UnicastRemoteObject����ô������Ҫ�ṩһ�����캯�����������׳�һ��RemoteException����
//���������������super(),�ͼ�����UnicastRemoteObject�еĹ��췽�������RMI�����Ӻ�Զ�̶���ĳ�ʼ����
public class SupplierDaoImp extends UnicastRemoteObject implements SupplierDao {
	private static final long serialVersionUID = 1L;
	public SupplierDaoImp() throws RemoteException {    //���캯��
		super();
	}
	
	DataConnection connection = new DataConnection();   //�������ݿ�����
	Connection conn = null;               
	//��ӹ�Ӧ����Ϣ�ķ���
	@Override 
	public void insertSupplier(Supplier supplier, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //��֤����
			throw new TokenUnvalidException();
		}
		conn = connection.getCon();      //������ָ�����ݿ������
		try {
			PreparedStatement statement = conn.prepareStatement("insert into tb_supplier values(?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, supplier.getSfzID());
			statement.setString(2, supplier.getName());
			statement.setString(3, supplier.getSex());
			statement.setString(4, supplier.getAge());
			statement.setString(5, supplier.getAddress());
			statement.setString(6, supplier.getPhone());
			statement.setString(7, supplier.getEmail());
			statement.setString(8, supplier.getBeiz());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override    // ��ѯ��Ӧ�̱���ȫ�����ݵķ���
	public List<Supplier> selectSupplier(String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //��֤����
			throw new TokenUnvalidException();
		}
		List<Supplier> list = new ArrayList<Supplier>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_supplier"); // ִ�в�ѯ���
			while(rest.next()) {   // ѭ��������ѯ�����
				Supplier supplier = new Supplier();    // ���������ݱ��Ӧ��JavaBean����
				supplier.setId(rest.getInt(1));        // Ӧ�ò�ѯ������ö�������
				supplier.setSfzID(rest.getString("sfzID"));
				supplier.setName(rest.getString("name"));
				supplier.setSex(rest.getString("sex"));
				supplier.setAge(rest.getString("age"));
				supplier.setAddress(rest.getString("address"));
				supplier.setPhone(rest.getString("phone"));
				supplier.setEmail(rest.getString("email"));
				supplier.setBeiz(rest.getString("beiz"));
				list.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override    // ����Ų�ѯ��Ӧ����Ϣ�ķ���
	public Supplier selectSupplierbyid(int id, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //��֤����
			throw new TokenUnvalidException();
		}
		Supplier supplier = new Supplier();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_supplier where id = " + id);
			while(rest.next()) {
				supplier.setId(rest.getInt(1));
				supplier.setSfzID(rest.getString("sfzID"));
				supplier.setName(rest.getString("name"));
				supplier.setSex(rest.getString("sex"));
				supplier.setAge(rest.getString("age"));
				supplier.setAddress(rest.getString("address"));
				supplier.setPhone(rest.getString("phone"));
				supplier.setEmail(rest.getString("email"));
				supplier.setBeiz(rest.getString("beiz"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplier;
	}
	@Override    // ���ͻ���ַ��ѯ��Ӧ����Ϣ�ķ���
	public List<Supplier> selectSupplierByAddress(String address, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //��֤����
			throw new TokenUnvalidException();
		}
		Supplier supplier = new Supplier();
		conn = connection.getCon();
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_supplier where sfzID = '" + address + "'");
			while(rest.next()) {
				supplier.setId(rest.getInt(1));
				supplier.setSfzID(rest.getString("sfzID"));
				supplier.setName(rest.getString("name"));
				supplier.setSex(rest.getString("sex"));
				supplier.setAge(rest.getString("age"));
				supplier.setAddress(rest.getString("address"));
				supplier.setPhone(rest.getString("phone"));
				supplier.setEmail(rest.getString("email"));
				supplier.setBeiz(rest.getString("beiz"));
				list.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override    // ���ͻ����Ʋ�ѯ��Ӧ����Ϣ�ķ���
	public List<Supplier> selectSupplierByName(String name, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //��֤����
			throw new TokenUnvalidException();
		}
		conn = connection.getCon();
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_supplier where name = '" + name + "'");
			while(rest.next()) {
				Supplier supplier = new Supplier();    // ���������ݱ��Ӧ��JavaBean����
				supplier.setId(rest.getInt(1));        // Ӧ�ò�ѯ������ö�������
				supplier.setSfzID(rest.getString("sfzID"));
				supplier.setName(rest.getString("name"));
				supplier.setSex(rest.getString("sex"));
				supplier.setAge(rest.getString("age"));
				supplier.setAddress(rest.getString("address"));
				supplier.setPhone(rest.getString("phone"));
				supplier.setEmail(rest.getString("email"));
				supplier.setBeiz(rest.getString("beiz"));
				list.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override    // ���ͻ����ƺͿͻ���ַ��ѯ��Ӧ����Ϣ�ķ���
	public List<Supplier> selectSupplierByNameAddress(String address, String name, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //��֤����
			throw new TokenUnvalidException();
		}
		Supplier supplier = new Supplier();
		conn = connection.getCon();
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_supplier where sfzID = '" + name + "' and name = '" + address + "'");
			while(rest.next()) {
				supplier.setId(rest.getInt(1));
				supplier.setSfzID(rest.getString("sfzID"));
				supplier.setName(rest.getString("name"));
				supplier.setSex(rest.getString("sex"));
				supplier.setAge(rest.getString("age"));
				supplier.setAddress(rest.getString("address"));
				supplier.setPhone(rest.getString("phone"));
				supplier.setEmail(rest.getString("email"));
				supplier.setBeiz(rest.getString("beiz"));
				list.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override    // �޸Ĺ�Ӧ����Ϣ�ķ���
	public void updateSupplier(Supplier supplier, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //��֤����
			throw new TokenUnvalidException();
		}
		conn = connection.getCon();
		try {
			String sql = "update tb_supplier set sfzID=?, name=?, sex=?, age=?, address=?, phone=?, email=?, beiz=? where id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, supplier.getSfzID());
			statement.setString(2, supplier.getName());
			statement.setString(3, supplier.getSex());
			statement.setString(4, supplier.getAge());
			statement.setString(5, supplier.getAddress()); 
			statement.setString(6, supplier.getPhone());
			statement.setString(7, supplier.getEmail());
			statement.setString(8, supplier.getBeiz());
			statement.setInt(11, supplier.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override    // ɾ����Ӧ����Ϣ�ķ���
	public void deleteSupplier(int id, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //��֤����
			throw new TokenUnvalidException();
		}
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("delete from tb_supplier where id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
