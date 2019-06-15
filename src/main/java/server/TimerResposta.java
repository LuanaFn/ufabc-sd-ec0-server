package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

import dto.Recebido;

public class TimerResposta extends TimerTask {

	private List<Recebido> recebidos = new ArrayList<Recebido>();
	String prefixo = "!////MENSAGEM COMECA AQUI////!";

	public void recebe(String recebido, DatagramSocket socket, DatagramPacket packet) {
		this.recebidos.add(new Recebido(recebido, socket, packet));
	}

	public void run() {

		if (!recebidos.isEmpty()) {
			Collections.sort(recebidos, (r1, r2) -> r1.getRecebido().compareTo(r2.getRecebido()));

			try {

				//envia cada mensagem de volta ao cliente
				for (int i = 0; i < recebidos.size(); i++) {
					Recebido r = recebidos.get(i);
					
					String salvo = r.getRecebido();
					
					//captura apenas a mensagem
					String msg = salvo.split(prefixo)[1];
					
					InetAddress address = r.getPacket().getAddress();
					int port = r.getPacket().getPort();
					
					byte[] buf = msg.getBytes();

					DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);

					r.getSocket().send(packet);
					System.out.println("Enviou a mensagem: " + msg);
					
					recebidos.remove(i);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
