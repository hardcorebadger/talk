package trefethen.talk.client;

import trefethen.talk.networking.CommunicationClient;
import trefethen.talk.packet.PacketLogin;
import trefethen.talk.packet.PacketLogout;

public class TalkClient {
	
	public static CommunicationClient client;
	public static Thread communicationThread;
	public static int userID;
	
	public static void main(String[] args) {
		client = new CommunicationClient("localhost", 8888);
		communicationThread = new Thread(client);
		communicationThread.start();
		Runtime.getRuntime().addShutdownHook(new Thread() {
	      public void run() {
	    	  client.addPacket(new PacketLogout());
	      }
	    });
	}
	
	public static void onConnect() {
		client.addPacket(new PacketLogin("Jake", "test"));
	}

}
