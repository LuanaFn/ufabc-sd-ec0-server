package dto;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Recebido {
	String recebido;
	DatagramSocket socket;
	DatagramPacket packet;
	
	public Recebido(String recebido, DatagramSocket socket, DatagramPacket packet) {
		super();
		this.recebido = recebido;
		this.socket = socket;
		this.packet = packet;
	}
	
	public String getRecebido() {
		return recebido;
	}
	public void setRecebido(String recebido) {
		this.recebido = recebido;
	}
	public DatagramSocket getSocket() {
		return socket;
	}
	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}
	public DatagramPacket getPacket() {
		return packet;
	}
	public void setPacket(DatagramPacket packet) {
		this.packet = packet;
	}
	
}
