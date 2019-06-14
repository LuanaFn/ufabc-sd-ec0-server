package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

public class TimerResposta extends TimerTask {

	private List<String> recebidos = new ArrayList<String>();
	private DatagramSocket socket;
	private DatagramPacket packet;
	String prefixo = "!////MENSAGEM COMECA AQUI////!";

	public void config(List recebidos, DatagramSocket socket, DatagramPacket packet) {
		this.recebidos.addAll(recebidos);
		this.socket = socket;
		this.packet = packet;
	}

	public void run() {

		if (!recebidos.isEmpty()) {
			Collections.sort(recebidos);

			try {

				//envia cada mensagem de volta ao cliente
				for (int i = 0; i < recebidos.size(); i++) {
					String salvo = recebidos.get(i);
					
					//captura apenas a mensagem
					String msg = salvo.split(prefixo)[1];
					
					InetAddress address = packet.getAddress();
					int port = packet.getPort();
					
					byte[] buf = msg.getBytes();

					packet = new DatagramPacket(buf, buf.length, address, port);

					socket.send(packet);
					
					recebidos.remove(i);
					
					System.out.println("Enviou a mensagem: " + msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
