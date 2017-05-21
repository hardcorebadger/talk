package trefethen.talk.server;

import trefethen.talk.networking.*;
import trefethen.talk.user.UserManager;

public class TalkServer {
	
	private static CommunicationServer server;
	private static final int port = 8888;
	
	public static void main(String[] args) {
		UserManager.loadUsers();
		UserManager.displayUsers();
		server = new CommunicationServer(port);
		new Thread(server).start();
	}
	
	
}
