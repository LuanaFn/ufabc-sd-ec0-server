package app;

import java.net.SocketException;
import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

import server.EchoServer;

public class RodaServer {
	
	static Logger log;
	
	public static void main(String[] args) {
		EchoServer server;
		try {
			server = new EchoServer();
			server.start();
			
		} catch (SocketException e) {
			log.logException(e, Level.SEVERE);
		}
		
	}
}
