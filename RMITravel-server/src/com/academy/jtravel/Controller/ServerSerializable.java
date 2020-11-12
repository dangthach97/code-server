package com.academy.jtravel.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSerializable {
	int count = 0;

	public void serve() {
		try {
			ServerSocket server = new ServerSocket(1985);
			System.out.println("Server is ready...");

			while (true) {

				// Accept
				Socket socket = server.accept();
				
				// Count Client connect
				System.out.println("Client : " + ++count);
				new ServerThread(socket).start();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[] args) { ServerSerializable server = new
	 * ServerSerializable(); server.serve(); }
	 */
}
