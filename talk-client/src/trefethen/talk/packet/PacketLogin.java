package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.networking.CommunicationClient;

public class PacketLogin extends Packet {
	
	private String name;
	private String password;
	
	public PacketLogin() {
		this.id = 1;
	}
	
	public PacketLogin(String n, String p) {
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
		userID = servlet.getInput().readInt();
		if (userID == -1) {
			// Login Failed
			System.out.println("Login Failed");
		} else {
			// Login Success
			System.out.println("Login Success, user id: " + userID);
		}
	}

}