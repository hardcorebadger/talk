package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.networking.CommunicationClient;

public class PacketBlank extends Packet {

	public PacketBlank() {
		this.id = 0;
	}

	@Override
	public void writeData(CommunicationClient servlet) throws IOException {
		// TODO Auto-generated method stub
		servlet.getOutput().writeInt(42);
	}

	@Override
	public void readData(CommunicationClient servlet) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(servlet.getInput().readInt());
	}
	
}
