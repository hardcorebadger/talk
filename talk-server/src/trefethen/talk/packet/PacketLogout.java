package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.networking.CommunicationServlet;
import trefethen.talk.user.UserManager;

public class PacketLogout extends Packet {
	
	public PacketLogout() {
		this.id = 3;
	}

	@Override
	public void writeData(CommunicationServlet servlet) throws IOException {
		
	}

	@Override
	public void readData(CommunicationServlet servlet) throws IOException {
		UserManager.logout(servlet);
	}

}
