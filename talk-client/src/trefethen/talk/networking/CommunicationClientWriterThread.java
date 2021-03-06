package trefethen.talk.networking;

public class CommunicationClientWriterThread extends Thread {

	private CommunicationClient client;

	public CommunicationClientWriterThread (CommunicationClient client) {
		
		this.client = client;

	}
	
	public void run() {
		while (client.isRunning()) {
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (client.isRunning())
				client.writePacket();
			else 
				break;
		}
		
	}

}
