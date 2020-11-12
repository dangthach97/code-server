package com.academy.jtravel.Controller;

import java.io.PrintStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.academy.Compute;
import com.academy.Person;
import com.academy.Strategy;
import com.academy.jtravel.Utils.ConnectionUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ServerController extends UnicastRemoteObject implements Compute {
	private static final int PORT_NUMBER = 2020;
	/**
	 * Làm cho Math ở phía Server sẵn sàng cho Cient dùng .Kết hợp với class Compute
	 * thì nó sẽ được vứt lên RMI và thằng UnicastRemoteObject để máy ảo java nhận
	 * diện đối tượng
	 */

	private static final long serialVersionUID = 1L;

	@FXML
	private static Text status;

	@FXML
	private Button btnAction;

	@Override
	public int add(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a + b;
	}

	@Override
	public String deploy(Strategy strategy) throws RemoteException {
		return strategy.getName();
	}
	

	@Override
	public List<Person> findAll() throws RemoteException {
		String sql = "SELECT * FROM tourdb";
		List<Person> persons = new ArrayList<Person>();
		StringBuilder stringBuilder = new StringBuilder(sql);
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(stringBuilder.toString());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String passport = rs.getString("passport");
				String cmnd = rs.getString("cmnd");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String tour = rs.getString("tour");
				String hotel = rs.getString("hotel");
				String service = rs.getString("service");
				String numberroom = rs.getString("numberroom");
				String verhicle = rs.getString("verhicle");
				String numberchair = rs.getString("numberchair");
				String status = rs.getString("status");
				persons.add(new Person(name, passport, cmnd, address, phone, email, tour, hotel, service, numberroom,
						verhicle, numberchair, status));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return persons;
	}

	@Override
	public Person findOneById(long id) throws RemoteException {
		Person persons = new Person();
		String sql = "SELECT * FROM tourdb WHERE id=" + id + ";";
		StringBuilder stringBuilder = new StringBuilder(sql);
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(stringBuilder.toString());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int Id = rs.getInt("id");
				String name = rs.getString("name");
				String passport = rs.getString("passport");
				String cmnd = rs.getString("cmnd");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String tour = rs.getString("tour");
				String hotel = rs.getString("hotel");
				String service = rs.getString("service");
				String numberroom = rs.getString("numberroom");
				String verhicle = rs.getString("verhicle");
				String numberchair = rs.getString("numberchair");
				String status = rs.getString("status");

				persons.setId(Id);
				persons.setName(name);
				persons.setPassport(passport);
				persons.setCmnd(cmnd);
				persons.setAddress(address);
				persons.setPhone(phone);
				persons.setEmail(email);
				persons.setTour(tour);
				persons.setHotel(hotel);
				persons.setService(service);
				persons.setNumberroom(numberroom);
				persons.setVerhicle(verhicle);
				persons.setNumberchair(numberchair);
				persons.setStatus(status);
			}
			System.out.println(persons);
			return persons;
		} catch (Exception e) {
			System.out.println("Cannot found person!");
		}
		return persons;
	}
	

	@Override
	public boolean save(Person person) throws RemoteException {
		Connection conn = ConnectionUtils.getConnection();
		StringBuilder stringBuilder = new StringBuilder(
				"INSERT INTO tourdb (name, passport, cmnd, address, phone, email, tour, hotel, service, numberroom, verhicle, numberchair, status)");
		stringBuilder.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(stringBuilder.toString());
			pstm.setString(1, person.getName());
			pstm.setString(2, person.getPassport());
			pstm.setString(3, person.getCmnd());
			pstm.setString(4, person.getAddress());
			pstm.setString(5, person.getPhone());
			pstm.setString(6, person.getEmail());
			pstm.setString(7, person.getTour());
			pstm.setString(8, person.getHotel());
			pstm.setString(9, person.getService());
			pstm.setString(10, person.getNumberroom());
				pstm.setString(11, person.getVerhicle());
				pstm.setString(12, person.getNumberchair());
				pstm.setString(13, person.getStatus());
			pstm.executeUpdate();
			System.out.println("Saved");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error save(): " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(long id) throws RemoteException {
		Connection conn = ConnectionUtils.getConnection();
		String sql = "DELETE FROM tourdb WHERE ID = ?";
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setLong(1, id);
			pstm.executeUpdate();
			System.out.println("Deleted");
			return true;
		} catch (Exception e) {
			System.out.println("Error delete(): " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean update(Person person, long id) throws RemoteException {
		Connection conn = ConnectionUtils.getConnection();
		StringBuilder stringBuilder = new StringBuilder("UPDATE tourdb SET");
		stringBuilder.append(
				" name= ?, passport= ?, cmnd= ?, address= ?, phone= ?, email= ?, tour= ?, hotel= ?, service= ?, numberroom= ?, verhicle= ?, numberchair= ?, status= ? WHERE id=" + id + ";");
		;
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(stringBuilder.toString());
			pstm.setString(1, person.getName());
			pstm.setString(2, person.getPassport());
			pstm.setString(3, person.getCmnd());
			pstm.setString(4, person.getAddress());
			pstm.setString(5, person.getPhone());
			pstm.setString(6, person.getEmail());
			pstm.setString(7, person.getTour());
			pstm.setString(8, person.getHotel());
			pstm.setString(9, person.getService());
			pstm.setString(10, person.getNumberroom());
			pstm.setString(11, person.getVerhicle());
			pstm.setString(12, person.getNumberchair());
			pstm.setString(13, person.getStatus());
			pstm.executeUpdate();
			System.out.println("Updated");
			return true;
		} catch (Exception e) {
			System.out.println("Error update element id= " + person.getId() + " with content error: " + e.getMessage());
		}
		return false;
	}

	// TODO Hàm khởi tạo
	public ServerController() throws RemoteException {

	}

	public static void main(String[] args) {
		ServerSerializable server = new ServerSerializable();
		server.serve();
	}
	
	@FXML public void startServer() {
		// TODO Security and Proxy

		if (System.getSecurityManager() == null) {
			System.out.println("Setting the RMISecurityManager on System...");
			System.setProperty("java.security.policy", "file:./security.policy");
		}

		try {
			ServerController model = new ServerController();
			// TODO Ném thằng math lên rmiregistry, có nhiều cách nhưng ở đây mình dùng
			// thẳng trong code
			LocateRegistry.createRegistry(PORT_NUMBER);
			// port default: 1099
			Registry registry = LocateRegistry.getRegistry(PORT_NUMBER);

			// tham số 1: math là id của nó trên registry, tham số 2: đối tượng math được
			// tạo mới ở trên
			registry.bind("model", model);
		} catch (RemoteException | java.rmi.AlreadyBoundException e1) {
			e1.printStackTrace();
		}
	}

}
