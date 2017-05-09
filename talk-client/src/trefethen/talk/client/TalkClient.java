package trefethen.talk.client;

import trefethen.talk.networking.CommunicationClient;

public class TalkClient {
	
	public static CommunicationClient client;
	public static Thread communicationThread;
	public static int userID;
	
	public static void main(String[] args) {
		client = new CommunicationClient("localhost", 8888);
		communicationThread = new Thread(client);
		communicationThread.start();
	}

}