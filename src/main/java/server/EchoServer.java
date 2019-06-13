package main.java.server;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.log4j.Logger;

public class EchoServer extends Thread {
	private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];
    Logger log = Logger.getLogger(EchoServer.class);;
 
    public EchoServer() throws SocketException {
        socket = new DatagramSocket(4445);
    }
 
    public void run(){
        running = true;
 
        while (running) {
        	try{
            DatagramPacket packet 
              = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
             
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            String received 
              = new String(packet.getData(), 0, packet.getLength());
             
            if (received.equals("end")) {
                running = false;
                continue;
            }
            socket.send(packet);
        	}
        	catch (Exception ex)
        	{
        		log.error(ex);
        	}
        }
        socket.close();
    }
}
