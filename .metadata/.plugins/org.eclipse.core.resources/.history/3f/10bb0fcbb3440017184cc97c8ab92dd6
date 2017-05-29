package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.chat.ChatManager;
import trefethen.talk.networking.CommunicationServlet;
import trefethen.talk.user.User;
import trefethen.talk.user.UserManager;

public class PacketUserChats extends Packet {
	
	private Integer[] ids;
	private String[] names;
	
	public PacketUserChats() {
		this.id = 4;
	}
	
	public PacketUserChats(User u) {
		this();
		ids = u.getChats().toArray(new Integer[u.getChats().size()]);
		names = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			// could modify this for DM naming convention
			names[i] = ChatManager.getChat(ids[i]).getName();
		}
	}

	@Override
	public void writeData(CommunicationServlet servlet) throws IOException {
		servlet.getOutput().writeInt(ids.length);
		for (int i = 0; i < ids.length; i++) {
			servlet.getOutput().writeInt(ids[i]);
			servlet.getOutput().writeUTF(names[i]);
		}
	}

	@Override
	public void readData(CommunicationServlet servlet) throws IOException {
		// respond with the user chats
		servlet.addPacket(new PacketUserChats(UserManager.getUser(servlet)));
	}

}
