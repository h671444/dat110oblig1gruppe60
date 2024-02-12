package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCRemoteImpl;
import no.hvl.dat110.rpc.RPCUtils;
import no.hvl.dat110.rpc.RPCServer;

public class DisplayImpl extends RPCRemoteImpl {

	public DisplayImpl(byte rpcid, RPCServer rpcserver) {
		super(rpcid,rpcserver);
	}

	public void write(String message) {
		System.out.println("DISPLAY:" + message);
	}
	
	public byte[] invoke(byte[] param) {

		// Unmarshall the incoming parameters to get the message
		String message = RPCUtils.unmarshallString(param);

		// Call the local write method with the unmarshalled message
		write(message);

		// For a void return type, we return an empty array
		byte[] returnval = RPCUtils.marshallVoid();

		return returnval;
	}
}
