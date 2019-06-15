package app;

import java.net.SocketException;

import server.EchoServer;

public class RodaServer {
	
	public static void main(String[] args) {
		EchoServer server;
		try {
			server = new EchoServer();
			server.start(); 
			
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
}
