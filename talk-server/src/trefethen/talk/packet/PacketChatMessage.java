package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.chat.ChatManager;
import trefethen.talk.chat.Message;
import trefethen.talk.networking.CommunicationServlet;
import trefethen.talk.user.UserManager;

public class PacketChatMessage extends Packet {
	
	public int chatID;
	public String message;
	public String name;
	
	public PacketChatMessage() {
		this.id = 6;
	}
	
	public PacketChatMessage(Message m) {
		this();
		name = m.getName();
		message = m.getMessage();
	}

	@Override
	public void writeData(CommunicationServlet servlet) throws IOException {
		// TODO Auto-generated method stub
		servlet.getOutput().writeUTF(name);
		servlet.getOutput().writeUTF(message);
	}

	@Override
	public void readData(CommunicationServlet servlet) throws IOException {
		// TODO Auto-generated method stub
		chatID = servlet.getInput().readInt();
		message = servlet.getInput().readUTF();
		ChatManager.getChat(chatID).addMessage(UserManager.getUser(servlet), message);
	}

}
