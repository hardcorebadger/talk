package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.chat.Chat;
import trefethen.talk.networking.CommunicationServlet;

public class PacketChatHistory extends Packet {
	
	public PacketChatHistory() {
		this.id = 5;
	}
	
	public PacketChatHistory(Chat c) {
		this();
	}

	@Override
	public void writeData(CommunicationServlet servlet) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readData(CommunicationServlet servlet) throws IOException {
		// TODO Auto-generated method stub
		
	}

}