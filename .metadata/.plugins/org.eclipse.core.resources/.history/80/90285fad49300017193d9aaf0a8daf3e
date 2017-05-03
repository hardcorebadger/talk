package trefethen.talk.server;

import trefethen.talk.networking.*;

public class TalkServer {
	
	private static CommunicationServer server;
	private static final int port = 8888;
	
	public static void main(String[] args) {
		server = new CommunicationServer(port);
		new Thread(server).start();
	}
	
}
