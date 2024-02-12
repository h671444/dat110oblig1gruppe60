package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {

		// Marshall the string parameter into a byte array
		byte[] request = RPCUtils.marshallString(message);

		// Make a remote call to the RPC server
		byte[] response = rpcclient.call((byte) Common.WRITE_RPCID, request);

		if (response == null) {
			throw new RuntimeException("RPC call to write method failed, no response received.");
		}
		
	}
}
