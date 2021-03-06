package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.gui.GUIManager;
import trefethen.talk.networking.CommunicationClient;

public class PacketRegister extends Packet {
	
	public String name;
	public String password;
	public int responseCode;
	
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
		GUIManager.asyncOnRegisterResponse(this);
	}

}
