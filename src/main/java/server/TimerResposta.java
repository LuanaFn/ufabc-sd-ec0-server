package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
			
			if(recebidos.size() > 1)
				Collections.sort(recebidos, new Comparator<Recebido>() {
					public int compare(Recebido a, Recebido b) {
						return a.getRecebido().compareTo(b.getRecebido());
					}
				});

			try {

				//envia cada mensagem de volta ao cliente
				while (recebidos.size() > 0) {
					Recebido r = recebidos.remove(0);
					
					//captura apenas a mensagem
					String msg = r.getRecebido().split(prefixo)[1];
					
					InetAddress address = r.getPacket().getAddress();
					int port = r.getPacket().getPort();
					
					byte[] buf = msg.getBytes();

					DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);

					r.getSocket().send(packet);
					System.out.println("Enviou a mensagem: " + msg + " - que era a mensagem " + r.getRecebido().split(prefixo)[0]);
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
