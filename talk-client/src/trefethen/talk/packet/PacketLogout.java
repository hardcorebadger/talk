package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.networking.CommunicationClient;

public class PacketLogout extends Packet {
	
	public PacketLogout() {
		this.id = 3;
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		System.out.println("logout packet");
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		
	}

}
