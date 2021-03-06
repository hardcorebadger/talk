package trefethen.talk.networking;

public class CommunicationClientReaderThread extends Thread  {

	private CommunicationClient client;
	
	public CommunicationClientReaderThread (CommunicationClient client) {
		this.client = client;
	}

	public void run(){

		while (client.isRunning()) {
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (client.isRunning())
				client.readPacket();
			else 
				break;
		}
		
	}
	
}
