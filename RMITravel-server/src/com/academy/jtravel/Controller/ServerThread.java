package com.academy.jtravel.Controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.academy.Person;

public class ServerThread extends Thread {
	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// TODO Receive
		ObjectInputStream ois;
		ObjectOutputStream oos;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			Person person = (Person) ois.readObject();
			if (person != null) {
				System.out.println(
						"Person information:"
						+"\nName:" + person.getName()
						+"\nPasPort:" + person.getPassport()
						+"\nCMND:" + person.getCmnd()
						+"\nAddress:" + person.getAddress()
						+"\nPhone:" + person.getPhone()
						+"\nEmail:" + person.getEmail()
						+"\nTour:" + person.getTour()
						+"\nHotel:" + person.getHotel()
						+"\nService:" + person.getService()
						+"\nNumber Room:" + person.getNumberroom()
						+"\nVerhicle:" + person.getVerhicle()
						+"\nNumber Chair:" + person.getNumberchair()
						+"\nStatus:" + person.getStatus()
						);
			}

		} catch (Exception  e) {
			// TODO: handle exception
		}

	}

}
