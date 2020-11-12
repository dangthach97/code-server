package com.academy.jtravel.Controller;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;
import java.util.ResourceBundle;

import com.academy.Compute;
import com.academy.Person;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class ClientController implements Initializable {
	private static final int PORT_NUMBER = 2020; // trùng cổng với server nhé
	private static final String HOST = "localhost";
	
	@FXML
	private ComboBox<String> cbbConnection;
	@FXML
	private ComboBox<String> cbbTour;
	@FXML
	private ComboBox<String> cbbHotel;
	@FXML
	private ComboBox<String> cbbService;
	@FXML
	private ComboBox<String> cbbVerhicle;
	@FXML
	private ComboBox<String> cbbOption;
	@FXML
	private TextField tfId, tfName, tfPassPort, tfCMND, tfAddress, tfPhone, tfEmail, tfNumberRoom, tfNumberChair;
	@FXML
	private Text txtMessage, txtMessageCheckUpdate;
	@FXML
	private Button btnSubmit, btnFormat, btnQuit;

	static String comConnection = "";
	static String comTour = "";
	static String comHotel = "";
	static String comService = "";
	static String comVerhicle = "";
	static String comOption = "";

	ObservableList<String> listConnect = FXCollections.observableArrayList("Server 1", "Server 2", "Server 3",
			"Server 4");
	ObservableList<String> listTour = FXCollections.observableArrayList("HN-HCM", "PY-DN", "DN-HN", "DN-HP");
	ObservableList<String> listHotel = FXCollections.observableArrayList("KS.CONG-DOAN", "KS.MUONG-THANH",
			"KS.HOANG-LONG", "KS.HAI-VAN");
	ObservableList<String> listService = FXCollections.observableArrayList("Tennis", "Taxi", "Dien thoai", "Wifi");
	ObservableList<String> listVerhicle = FXCollections.observableArrayList("Tau lua", "Xe khach", "May bay");
	ObservableList<String> listOption = FXCollections.observableArrayList("Registry", "Update", "Delete");
	private static Compute compute;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		btnSubmit.setDisable(true);
		cbbConnection.setItems(listConnect);
		cbbTour.setItems(listTour);
		cbbHotel.setItems(listHotel);
		cbbService.setItems(listService);
		cbbVerhicle.setItems(listVerhicle);
		cbbOption.setItems(listOption);

		// TODO set default value combobox
		cbbConnection.getSelectionModel().selectFirst();
		cbbConnection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				comConnection = newValue;
			}
		});

		cbbTour.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				comTour = newValue;
			}
		});

		cbbHotel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				comHotel = newValue;
			}
		});

		cbbService.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				comService = newValue;
			}
		});

		cbbVerhicle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				comVerhicle = newValue;
			}
		});

		cbbOption.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				comOption = newValue;
				if (newValue.equals("Registry")) {
					tfId.setVisible(false);
					tfName.textProperty().addListener((observableValue, oldName, newName) -> {
						btnSubmit.setDisable(newName.trim().isEmpty());
					});

				} else if (newValue.equals("Delete")) {
					tfId.setVisible(true);
					tfName.setDisable(true);
					tfPassPort.setDisable(true);
					tfCMND.setDisable(true);
					tfAddress.setDisable(true);
					tfNumberChair.setDisable(true);
					tfNumberRoom.setDisable(true);
					tfPhone.setDisable(true);
					tfEmail.setDisable(true);
					cbbHotel.setDisable(true);
					cbbService.setDisable(true);
					cbbTour.setDisable(true);
					cbbVerhicle.setDisable(true);
					btnSubmit.setDisable(true);
					tfId.textProperty().addListener((observableValue, oldId, newId) -> {
						btnSubmit.setDisable(newId.trim().isEmpty());
					});

				} else if (newValue.equals("Update")) {
					tfId.setVisible(true);
					tfName.setDisable(false);
					tfPassPort.setDisable(false);
					tfCMND.setDisable(false);
					tfAddress.setDisable(false);
					tfNumberChair.setDisable(false);
					tfNumberRoom.setDisable(false);
					tfPhone.setDisable(false);
					tfEmail.setDisable(false);
					cbbHotel.setDisable(false);
					cbbService.setDisable(false);
					cbbTour.setDisable(false);
					cbbVerhicle.setDisable(false);
					tfId.textProperty().addListener((observableValue, oldId, newId) -> {
						btnSubmit.setDisable(newId.trim().isEmpty());

						if (tfId.getText().toString().length() > 0) {
							txtMessageCheckUpdate.setVisible(true);
							tfId.setOnKeyPressed(new EventHandler<KeyEvent>() {
								@Override
								public void handle(KeyEvent ke) {
									if (ke.getCode().equals(KeyCode.ENTER)) {
										findOneById();
									}
								}
							});
						} else {
							txtMessageCheckUpdate.setVisible(false);
						}
					});
				} else {
					tfId.setVisible(false);
					tfName.setDisable(false);
					tfPassPort.setDisable(false);
					tfCMND.setDisable(false);
					tfAddress.setDisable(false);
					tfNumberChair.setDisable(false);
					tfNumberRoom.setDisable(false);
					tfPhone.setDisable(false);
					tfEmail.setDisable(false);
					cbbHotel.setDisable(false);
					cbbService.setDisable(false);
					cbbTour.setDisable(false);
					cbbVerhicle.setDisable(false);
				}
			}
		});

	}

	@FXML
	private void submit() {
		// TODO handler submit button
		switch (comOption) {
		case "Registry":
			save();
			break;
		case "Update":
			update();
			break;
		case "Delete":
			delete();
			break;
		default:
			break;
		}
	}

	// TODO Confirm exit before click button Quit
	@FXML
	public void confirmExit() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Do you want exit?");
		alert.setContentText("If you choose 'Yes', content is not save!");

		ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType buttonTypeCancel = new ButtonType("Cancle", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == buttonTypeYes)
			Platform.exit();
		else
			alert.close();
	}

	@FXML
	private void update() {
		long id = Long.parseLong(tfId.getText().toString());

		// Update person
		// Khi chọn 1 person trong bảng, thì sẽ hiển thị chi tiết của người đó và lấy tấ
		String connection = comConnection;
		String name = tfName.getText();
		String passport = tfPassPort.getText();
		String cmnd = tfCMND.getText();
		String address = tfAddress.getText();
		String phone = tfPhone.getText();
		String email = tfEmail.getText();
		String tour = comTour;
		String hotel = comHotel;
		String service = comService;
		String numberroom = tfNumberRoom.getText();
		String verhicle = comVerhicle;
		String numberchair = tfNumberChair.getText();
		String status = comOption;

		Person person = new Person();
		person.setName(name);
		person.setPassport(passport);
		person.setCmnd(cmnd);
		person.setAddress(address);
		person.setPhone(phone);
		person.setEmail(email);
		person.setTour(tour);
		person.setHotel(hotel);
		person.setService(service);
		person.setNumberroom(numberroom);
		person.setVerhicle(verhicle);
		person.setNumberchair(numberchair);
		person.setStatus(status);
		try {
			Registry registry = LocateRegistry.getRegistry(HOST, PORT_NUMBER);
			Compute compute = (Compute) registry.lookup("model");
			if (handlerMatch()) {
				// Alert confirm update
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Update");
				alert.setHeaderText("Please check again information :" + "\nName: " + name + ", " + "\nPassPort: "
						+ passport + "\nCMND: " + cmnd + "\nAddress: " + address + "\nPhone: " + phone + "\nEmail: "
						+ email + "\nTour: " + tour + "\nHotel: " + hotel + "\nService: " + service + "\nNumber Room: "
						+ numberroom + "\nVerhicle: " + verhicle + "\nNumber Chair: " + numberchair + "\nStatus: "
						+ status);

				ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
				ButtonType buttonTypeCancel = new ButtonType("Cancle", ButtonBar.ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

				Optional<ButtonType> result = alert.showAndWait();

				if (result.get() == buttonTypeYes) {
					compute.update(person, id);
					txtMessage.setText("Update ticked id : " + id + ".");
					tfId.setText("");
				} else
					alert.close();
			} else {
				tfEmail.setTooltip(new Tooltip("a-z0-9@gmail.com"));

			}

		} catch (Exception e) {
			System.out.println("Client: error update at " + e.getMessage());
		}
	}

	@FXML
	private void delete() {
		// TODO delete information
		long id = Long.parseLong(tfId.getText().toString());
		if (id > 0) {
			try {
				System.out.println("try :" + id);
				Registry registry = LocateRegistry.getRegistry(HOST, PORT_NUMBER);
				Compute compute = (Compute) registry.lookup("model");

				// Alert confirm delete
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Deleted");
				alert.setHeaderText("Do you want delete ticked id " + id + "?");

				ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
				ButtonType buttonTypeCancel = new ButtonType("Cancle", ButtonBar.ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

				Optional<ButtonType> result = alert.showAndWait();

				if (result.get() == buttonTypeYes)
					compute.delete(id);
				else
					alert.close();
				txtMessage.setText("Deleted ticked id : " + id + ".");
				tfId.setText("");
			} catch (Exception e) {
				System.out.println("ID " + id + " is not exit !");
			}
		}

	}

	@FXML
	private void save() {

		if (handlerMatch()) {
			// TODO save information
			String connection = comConnection;
			String name = tfName.getText();
			String passport = tfPassPort.getText();
			String cmnd = tfCMND.getText();
			String address = tfAddress.getText();
			String phone = tfPhone.getText();
			String email = tfEmail.getText();
			String tour = comTour;
			String hotel = comHotel;
			String service = comService;
			String numberroom = tfNumberRoom.getText();
			String verhicle = comVerhicle;
			String numberchair = tfNumberChair.getText();
			String status = comOption;

			// TODO Add Person
			Person person = new Person();
			person.setName(name);
			person.setPassport(passport);
			person.setCmnd(cmnd);
			person.setAddress(address);
			person.setPhone(phone);
			person.setEmail(email);
			person.setTour(tour);
			person.setHotel(hotel);
			person.setService(service);
			person.setNumberroom(numberroom);
			person.setVerhicle(verhicle);
			person.setNumberchair(numberchair);
			person.setStatus(status);
			try {
				Registry registry = LocateRegistry.getRegistry(HOST, PORT_NUMBER);
				Compute compute = (Compute) registry.lookup("model");
				if (compute.save(person)) {
					txtMessage.setText("Saved information!");
				}
			} catch (Exception e) {
				txtMessage.setText("Client: error save at " + e.getMessage());
			}

		}

	}

	@FXML
	private void findOneById() {
		// TODO Find ticked by Id
		long id = Long.parseLong(tfId.getText().toString());
		try {
			Registry registry = LocateRegistry.getRegistry(HOST, PORT_NUMBER);
			Compute compute = (Compute) registry.lookup("model");
			Person person = compute.findOneById(id);
			tfName.setText(person.getName());
			tfPassPort.setText(person.getPassport());
			tfCMND.setText(person.getCmnd());
			tfAddress.setText(person.getAddress());
			tfPhone.setText(person.getPhone());
			tfEmail.setText(person.getEmail());
			cbbTour.setValue(person.getTour());
			cbbHotel.setValue(person.getHotel());
			cbbService.setValue(person.getService());
			tfNumberRoom.setText(person.getNumberroom());
			cbbVerhicle.setValue(person.getVerhicle());
			tfNumberChair.setText(person.getNumberchair());
		} catch (Exception e) {
			txtMessage.setText("Error: id not exit! Please check again");
		}
	}

	// TODO Format form
	@FXML
	private void format() {
		tfId.setVisible(false);
		tfId.setText("");
		tfName.setText("");
		tfPassPort.setText("");
		tfCMND.setText("");
		tfAddress.setText("");
		tfPhone.setText("");
		tfEmail.setText("");
		cbbTour.setValue(null);
		cbbHotel.setValue(null);
		cbbService.setValue(null);
		tfNumberRoom.setText("");
		cbbVerhicle.setValue(null);
		tfNumberChair.setText("");
	}

	// TODO Handling standard email
	private boolean handlerMatch() {
		String alertStyles = "-fx-text-box-border: red ;-fx-focus-color: red;";
		String regexEmail = "^[a-z0-9](\\\\.?[a-z0-9]){5,}@g(oogle)?mail\\\\.com$";
		String regexPhone = "^0[1-9]{9}$";

		if (!tfEmail.getText().matches(regexEmail)) {
			tfEmail.setStyle(alertStyles);
			return false;
		} else if (!tfPhone.getText().matches(regexPhone)) {
			tfPhone.setStyle(alertStyles);
			return false;
		}

		return true;
	}

	// TODO Main
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.out.println("Setting the RMISecurityManager on System...");
			System.setProperty("java.security.policy", "file:./security.policy");
		}

		System.out.println("Started client");

		try {
			// Đi đến thằng registry ( với đường HOST và số nhà là PORT_NUMBER)
			Registry registry = LocateRegistry.getRegistry(HOST, PORT_NUMBER);
			compute = (Compute) registry.lookup("model");

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		System.out.println("Client is closed.");
	}

}
