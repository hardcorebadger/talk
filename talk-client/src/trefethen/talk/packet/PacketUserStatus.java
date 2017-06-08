package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.gui.GUIManager;
import trefethen.talk.networking.CommunicationClient;

public class PacketUserStatus extends Packet {
	
	public String name;
	public int userId;
	public boolean online;
	
	public PacketUserStatus() {
		this.id = 7;
	}

	@Override
	public void writeData(CommunicationClient servlet) throws IOException {
		// TODO Auto-generated method stub
		servlet.getOutput().writeInt(userId);
		servlet.getOutput().writeBoolean(online);
		servlet.getOutput().writeUTF(name);
	}

	@Override
	public void readData(CommunicationClient servlet) throws IOException {
		// TODO Auto-generated method stub
		userId = servlet.getInput().readInt();
		online = servlet.getInput().readBoolean();
		name = servlet.getInput().readUTF();
		GUIManager.asyncOnUserStatus(this);
	}

}