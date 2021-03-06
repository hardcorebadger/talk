package trefethen.talk.server;

import java.util.Scanner;

import trefethen.talk.chat.ChatManager;
import trefethen.talk.networking.*;
import trefethen.talk.user.UserManager;

public class TalkServer {
	
	public static Scanner input = new Scanner(System.in);
	private static CommunicationServer server;
	private static final int port = 8888;
	
	public static void main(String[] args) {
		UserManager.loadUsers();
		ChatManager.loadChats();
		server = new CommunicationServer(port);
		new Thread(server).start();
		String cmd = input.nextLine();
		if (cmd.equals("s")) {
			UserManager.saveUsers();
			ChatManager.saveChats();
			System.exit(1);
		}
	}
	
	
}
