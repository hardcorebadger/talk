package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.chat.Chat;
import trefethen.talk.chat.ChatManager;
import trefethen.talk.networking.CommunicationServlet;
import trefethen.talk.user.UserManager;

public class PacketChatHistory extends Packet {
	
	public int chatID;
	public String[] usernames;
	public String[] messages;
	
	public PacketChatHistory() {
		this.id = 5;
	}
	
	public PacketChatHistory(Chat c) {
		this();
		usernames = c.getUsernameArray();
		messages = c.getMessagesArray();
	}
	
	@Override
	public void writeData(CommunicationServlet servlet) throws IOException {
		// write the chat history
		servlet.getOutput().writeInt(usernames.length);
		for (int i = 0; i < usernames.length; i++) {
			servlet.getOutput().writeUTF(usernames[i]);
			servlet.getOutput().writeUTF(messages[i]);
		}
	}

	@Override
	public void readData(CommunicationServlet servlet) throws IOException {
		// receive chat id
		chatID = servlet.getInput().readInt();
		Chat c = ChatManager.getChat(chatID);
		c.addListener(UserManager.getUser(servlet));
		servlet.addPacket(new PacketChatHistory(c));
	}

}
