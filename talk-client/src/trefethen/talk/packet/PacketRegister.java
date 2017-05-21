package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.networking.CommunicationClient;

public class PacketRegister extends Packet {
	
	private String name;
	private String password;
	private int responseCode;
	
	public PacketRegister() {
		this.id = 2;
	}
	
	public PacketRegister(String n, String p) {
		this();
		name = n;
		password = p;
	}

	@Override
	public void writeData(CommunicationClient servlet) throws IOException {
		servlet.getOutput().writeUTF(name);
		servlet.getOutput().writeUTF(password);
	}

	@Override
	public void readData(CommunicationClient servlet) throws IOException {
		name = servlet.getInput().readUTF();
		password = servlet.getInput().readUTF();
		responseCode = servlet.getInput().readInt();
		if (responseCode == 1) {
			// Register Successful
			System.out.println("success");
		} else {
			// Username Taken
			System.out.println("username in use");
		}
	}

}
