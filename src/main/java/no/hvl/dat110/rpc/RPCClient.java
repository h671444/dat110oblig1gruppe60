package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() {
		
		connection = msgclient.connect();
	}
	
	public void disconnect() {
		
		if(connection != null){
			connection.close();
		}
	}

	/*
	 Make a remote call on the method on the RPC server by sending an RPC request message and receive an RPC reply message
	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called
	 */

	public byte[] call(byte rpcid, byte[] param) {
		
		byte[] returnval = null;

		byte[] rpcmsg = RPCUtils.encapsulate(rpcid, param);

		Message request = new Message(rpcmsg);
		connection.send(request);

		Message response = connection.receive();

		returnval = RPCUtils.decapsulate(response.getData());

		return returnval;
		
	}

}
