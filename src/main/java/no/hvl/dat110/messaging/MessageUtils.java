package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {

		// TODO - START
		byte[] segment = new byte[SEGMENTSIZE]; // lager en array av byte, størrelsen til SEGMENTSIZE
		byte[] payloadData = message.getData(); // lager en array av byte som inneholder dataen til meldingen


		if(payloadData.length > SEGMENTSIZE - 1){
			throw new UnsupportedOperationException("payload er for stor for encapsulation");
		}

		segment[0] = (byte) payloadData.length;
		// setter første byten til lengden av payloadData

		System.arraycopy(payloadData, 0, segment, 1, payloadData.length);
		//kopierer payloadData til segmentet, starter fra index 1
		

		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer
		// TODO - END

		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {
		// TODO - START
		// decapsulate segment and put received payload data into a message



		//henter lengden av payloadData fra byten til det første segmentet
		int payloadLength = segment[0];

		byte[] payloadData = Arrays.copyOfRange(segment, 1, payloadLength + 1);
		//henter ut payload data fra segmentet, starter fra index 1

		Message message = new Message(payloadData);

		
		// TODO - END
		
		return message;
		
	}
	
}
