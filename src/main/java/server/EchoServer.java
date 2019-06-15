package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Timer;

public class EchoServer extends Thread {
	private DatagramSocket socket;
	private boolean running;
	private byte[] buf = new byte[256];
	Timer timer = new Timer();
	TimerResposta timerR = new TimerResposta();

	public EchoServer() throws SocketException {
		
		timer.schedule(timerR, 0, 10000);
		socket = new DatagramSocket(4445);
	}

	public void run() {
		running = true;

		while (running) {
			try {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);

				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				String received = new String(packet.getData(), 0, packet.getLength());

				System.out.println("Recebeu a mensagem: " + received);

				if (received.equals("end")) {
					running = false;
					continue;
				}
				
				timerR.recebe(received, socket, packet);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		socket.close();
	}
}
