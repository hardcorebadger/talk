package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.networking.CommunicationServlet;
import trefethen.talk.user.UserManager;

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
	public void writeData(CommunicationServlet servlet) throws IOException {
		servlet.getOutput().writeUTF(name);
		servlet.getOutput().writeUTF(password);
		servlet.getOutput().writeInt(responseCode);
	}

	@Override
	public void readData(CommunicationServlet servlet) throws IOException {
		name = servlet.getInput().readUTF();
		password = servlet.getInput().readUTF();
		if (UserManager.register(name, password))
			responseCode = 1;
		else
			responseCode = -1;
		servlet.addPacket(this);
			
	}

}
