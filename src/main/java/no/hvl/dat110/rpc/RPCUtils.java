package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		

		
		// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format
		byte[] rpcmsg = new byte[payload.length + 1];
		rpcmsg[0] = rpcid;

		System.arraycopy(payload, 0, rpcmsg,1, payload.length);


		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {

		// Decapsulate the rpcid and payload in a byte array according to the RPC message syntax
		byte[] payload = new byte[rpcmsg.length - 1];

		System.arraycopy(rpcmsg,1,payload, 0,payload.length);

		
		return payload;
		
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		byte[] encoded = str.getBytes();
		

		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		
		String decoded = new String(data);
		
		return decoded;
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = new byte[0];

		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {

		//Nothing to do
	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {

		ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
		buffer.putInt(x);
		byte[] encoded = buffer.array();
		
		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {

		ByteBuffer buffer = ByteBuffer.wrap(data);
		int decoded = buffer.getInt();
		
		return decoded;
		
	}
}
