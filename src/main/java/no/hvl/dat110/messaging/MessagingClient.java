package no.hvl.dat110.messaging;


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import no.hvl.dat110.TODO;

public class MessagingClient {

	// name/IP address of the messaging server
	private String server;

	// server port on which the messaging server is listening
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	// setup of a messaging connection to a messaging server
	public MessageConnection connect () {

		// client-side socket for underlying TCP connection to messaging server
		Socket clientSocket;
		MessageConnection connection = null;

		try {
			// connect to messaging server using a TCP socket
			clientSocket = new Socket(server,port);

			// create and return a corresponding messaging connection
			connection = new MessageConnection(clientSocket);

		} catch (UnknownHostException e) {
			System.err.println("Host " + server + " could not be determined: " + e.getMessage()); //Unknown host error handling
		} catch (IOException e) {
			System.err.println("I/O error when connecting to the server: " + e.getMessage()); //I/O error handling
		}

		return connection;
	}
}
