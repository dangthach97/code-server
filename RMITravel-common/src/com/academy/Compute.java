package com.academy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Compute extends Remote {
	int add(int a, int b) throws RemoteException;
	
	// Sự khác nhau ở đây là: ở dưới đây ta dùng 1 object, còn ở trên là 1 primatic
	// Nhớ remote RemoteException nhé: cái này hỗ trợ ném ra ngoại lệ nếu có vấn đề bất cập xảy ra.
	// Google tham khảo thêm.
	String deploy(Strategy strategy) throws RemoteException;
	
	//
	List<Person> findAll() throws RemoteException;
	Person findOneById(long id) throws RemoteException;
	boolean save(Person person) throws RemoteException;
	boolean update(Person person, long id) throws RemoteException;
	boolean delete(long id) throws RemoteException;
	
	
}
