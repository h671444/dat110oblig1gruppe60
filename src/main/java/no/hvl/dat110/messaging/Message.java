package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class Message {

	// the up to 127 bytes of data (payload) that a message can hold
	private byte[] data;

	// construction a Message with the data provided
	public Message(byte[] data) {

		// Check if data is null
		if(data == null) {
			throw new IllegalArgumentException(("Data cannot be null"));
		}
		// Check if the data length is more than 127 bytes
		if(data.length > 127) {
			throw new IllegalArgumentException(("Data cannot be longer than 127 bytes"));
		}

		// Passed requirements and assigns data to the field
		this.data = data;
		

	}

	public byte[] getData() {
		return this.data; 
	}

}
