package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.gui.CmdLineUI;
import trefethen.talk.networking.CommunicationClient;

public class PacketChatMessage extends Packet {
	
	public int chatID;
	public String message;
	public String name;
	
	public PacketChatMessage() {
		this.id = 6;
	}
	
	public PacketChatMessage(int cid, String msg) {
		this();
		chatID = cid;
		message = msg;
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		// TODO Auto-generated method stub
		client.getOutput().writeInt(chatID);
		client.getOutput().writeUTF(message);
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		// TODO Auto-generated method stub
		name = client.getInput().readUTF();
		message = client.getInput().readUTF();
		CmdLineUI.asynOnChatMessage(this);
	}

}