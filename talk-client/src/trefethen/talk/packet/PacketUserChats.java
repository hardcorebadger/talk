package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.gui.GUIManager;
import trefethen.talk.networking.CommunicationClient;

public class PacketUserChats extends Packet {
	
	public boolean[] online;
	public Integer[] userIds;
	public Integer[] ids;
	public String[] names;
	
	public PacketUserChats() {
		this.id = 4;
	}

	@Override
	public void writeData(CommunicationClient servlet) throws IOException {
		
	}

	@Override
	public void readData(CommunicationClient servlet) throws IOException {
		ids = new Integer[servlet.getInput().readInt()];
		names = new String[ids.length];
		userIds = new Integer[ids.length];
		online = new boolean[ids.length];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = servlet.getInput().readInt();
			names[i] = servlet.getInput().readUTF();
			userIds[i] = servlet.getInput().readInt();
			online[i] = servlet.getInput().readBoolean();
		}
		GUIManager.asyncOnUserChatsResponse(this);
	}

}
