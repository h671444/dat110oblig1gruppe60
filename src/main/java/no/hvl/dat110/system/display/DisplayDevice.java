package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;


public class DisplayDevice {
	
	public static void main(String[] args) {
		
		System.out.println("Display server starting ...");

		// Create an instance of the RPC server
		RPCServer displayserver = new RPCServer(Common.DISPLAYPORT);

		// Create an instance of the DisplayImpl class
		// The byte value here should be the identifier for the display's write method
		DisplayImpl display = new DisplayImpl((byte)Common.WRITE_RPCID, displayserver);

		// Register the display implementation with the RPC server
		displayserver.register((byte)Common.WRITE_RPCID, display);

		// Start the server so it can process incoming RPC requests
		displayserver.run();

		// Stop the server when it's no longer needed
		displayserver.stop();
		
		System.out.println("Display server stopping ...");
		
	}
}
