package trefethen.talk.networking;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import trefethen.talk.client.TalkClient;
import trefethen.talk.packet.Packet;

public class CommunicationClient implements Runnable {
	
	private String host;
	private int port;
	private Socket socket;
	private volatile Thread reader;
	private volatile Thread writer;
	private volatile DataInputStream input;
	private volatile DataOutputStream output;
	private boolean running = false;
	private ArrayList<Packet> packetQue = new ArrayList<Packet>();
	
	public CommunicationClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(host, port);
			socket.setTcpNoDelay(true);
			output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), 5120));
			input = new DataInputStream(socket.getInputStream());
			writer = new CommunicationClientWriterThread(this);
			reader = new CommunicationClientReaderThread(this);
			running = true;
			writer.start();
			reader.start();
			System.out.println("Client initialized");
			TalkClient.onConnect();
		} catch (UnknownHostException e) {
			System.out.println("Don't know about host: " + host + ".");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Couldn't get I/O for the connection");
			e.printStackTrace();
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public DataInputStream getInput() {
		return input;
	}
	
	public DataOutputStream getOutput() {
		return output;
	}
	
	public void addPacket(Packet packet) {
		packetQue.add(packet);
	}
	
	public void readPacket() {
		Packet.readPacket(this);
	}
	
	public boolean writePacket() {
		try {
			if (packetQue.size() > 0) {
				Packet packet = packetQue.get(0);
				if (packet != null) {
					packetQue.remove(0);
					Packet.writePacket(packet, this);
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to write packet");
			e.printStackTrace();
		}
		return false;
	}
	
	public void disconnect() {
		try {
			System.out.println("Disconnecting client");
			output.flush();
		    output.close();
			input.close();
			socket.close();
			running = false;
			packetQue.clear();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
