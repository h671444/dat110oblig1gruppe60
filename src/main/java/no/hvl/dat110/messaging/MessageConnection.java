package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;


// FORSVINNER DETTE ETTER SONDRE+

//SOPP

public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) { //

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		byte[] data;
		
		// TODO - START
		// encapsulate the data contained in the Message and write to the output stream
		// When encapsulating the data, it adds extra data for when the data will be sent over the network, this will protect the message.
		data = MessageUtils.encapsulate(message);

		// after encapsulating the data, send the data out.
		try {
			outStream.write(data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// TODO - END

	}

	public Message receive() {

		Message message;
		byte[] data = new byte[128];
		
		// TODO - START
		// read a segment from the input stream and decapsulate data into a Message
		try {
			inStream.readFully(data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		//decapsulating the data is when you recieve a message data and you decrypt the message, encaps and decaps is similiar to hashing and salting.
		message = MessageUtils.decapsulate(data);
		// TODO - END
		
		return message;
		
	}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}