package trefethen.talk.server;

import trefethen.talk.chat.ChatManager;
import trefethen.talk.networking.*;
import trefethen.talk.user.UserManager;

public class TalkServer {
	
	private static CommunicationServer server;
	private static final int port = 8888;
	
	public static void main(String[] args) {
		UserManager.loadUsers();
		ChatManager.loadChats();
		UserManager.displayUsers();
		ChatManager.displayChats();
		UserManager.saveUsers();
		ChatManager.saveChats();
		server = new CommunicationServer(port);
		new Thread(server).start();
	}
	
	
}
