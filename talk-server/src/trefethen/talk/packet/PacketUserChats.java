package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.chat.ChatManager;
import trefethen.talk.networking.CommunicationServlet;
import trefethen.talk.user.User;
import trefethen.talk.user.UserManager;

public class PacketUserChats extends Packet {
	
	public boolean[] online;
	public Integer[] userIds;
	public Integer[] ids;
	public String[] names;
	
	public PacketUserChats() {
		this.id = 4;
	}
	
	public PacketUserChats(User u) {
		this();
		int userID = u.getID();
		ids = u.getChats().toArray(new Integer[u.getChats().size()]);
		names = new String[ids.length];
		userIds = new Integer[ids.length];
		online = new boolean[ids.length];
		for (int i = 0; i < ids.length; i++) {
			// could modify this for DM naming convention
			names[i] = ChatManager.getChat(ids[i]).getName();
			userIds[i] = -1;
			online[i] = true;
			if (names[i].charAt(0) == '&') {
				
				String[] split = names[i].split("&");
				
				int otherId = -1;
				if (split[1].equals(""+userID))
					otherId = Integer.parseInt(split[2]);
				else
					otherId = Integer.parseInt(split[1]);
				
				User other = UserManager.getUser(otherId);
				names[i] = other.getName();
				userIds[i] = otherId;
				online[i] = other.isOnline();
			}
		}
	}

	@Override
	public void writeData(CommunicationServlet servlet) throws IOException {
		servlet.getOutput().writeInt(ids.length);
		for (int i = 0; i < ids.length; i++) {
			servlet.getOutput().writeInt(ids[i]);
			servlet.getOutput().writeUTF(names[i]);
			servlet.getOutput().writeInt(userIds[i]);
			servlet.getOutput().writeBoolean(online[i]);
		}
	}

	@Override
	public void readData(CommunicationServlet servlet) throws IOException {
		// respond with the user chats
		ChatManager.removeListener(UserManager.getUser(servlet));
		servlet.addPacket(new PacketUserChats(UserManager.getUser(servlet)));
	}

}
