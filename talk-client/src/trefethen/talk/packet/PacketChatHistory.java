package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.gui.GUIManager;
import trefethen.talk.networking.CommunicationClient;


public class PacketChatHistory extends Packet {
	
	public int chatID;
	public String[] usernames;
	public String[] messages;
	
	public PacketChatHistory() {
		this.id = 5;
	}
	
	public PacketChatHistory(int i) {
		this.id = 5;
		chatID = i;
	}


	@Override
	public void writeData(CommunicationClient servlet) throws IOException {
		// Client sends up a chat id
		servlet.getOutput().writeInt(chatID);
	}

	@Override
	public void readData(CommunicationClient servlet) throws IOException {
		// Read in chat History
		usernames = new String[servlet.getInput().readInt()];
		messages = new String[usernames.length];
		for (int i = 0; i < usernames.length; i++) {
			usernames[i] = servlet.getInput().readUTF();
			messages[i] = servlet.getInput().readUTF();
		}
		GUIManager.asyncOnChatHistoryResponse(this);
	}

}
