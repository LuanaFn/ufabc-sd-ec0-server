package main.java.app;

import java.net.SocketException;

import org.apache.log4j.Logger;

import main.java.server.EchoServer;

public class RodaServer {
	
	static Logger log = Logger.getLogger(RodaServer.class);
	
	public static void main(String[] args) {
		EchoServer server;
		try {
			server = new EchoServer();
			server.start();
			
		} catch (SocketException e) {
			log.error(e);
		}
		
	}
}
