package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.networking.CommunicationServlet;
import trefethen.talk.user.UserManager;

public class PacketLogin extends Packet {
	
	private String name;
	private String password;
	private int userID;
	
	public PacketLogin() {
		this.id = 1;
	}
	
	public PacketLogin(String n, String p) {
		this();
		name = n;
		password = p;
	}

	@Override
	public void writeData(CommunicationServlet servlet) throws IOException {
		servlet.getOutput().writeUTF(name);
		servlet.getOutput().writeUTF(password);
		servlet.getOutput().writeInt(userID);
	}

	@Override
	public void readData(CommunicationServlet servlet) throws IOException {
		name = servlet.getInput().readUTF();
		password = servlet.getInput().readUTF();
		// Attempt Login
		userID = UserManager.login(name, password, servlet);
		// Respond to login
		servlet.addPacket(this);
	}

}
