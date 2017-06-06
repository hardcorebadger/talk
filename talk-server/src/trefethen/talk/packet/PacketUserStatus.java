package trefethen.talk.packet;

import java.io.IOException;

import trefethen.talk.networking.CommunicationServlet;
import trefethen.talk.user.User;

public class PacketUserStatus extends Packet {
	
	public String name;
	public int userId;
	public boolean online;
	
	public PacketUserStatus() {
		this.id = 7;
	}
	
	public PacketUserStatus(User u) {
		this();
		userId = u.getID();
		online = u.isOnline();
		name = u.getName();
	}

	@Override
	public void writeData(CommunicationServlet servlet) throws IOException {
		// TODO Auto-generated method stub
		servlet.getOutput().writeInt(userId);
		servlet.getOutput().writeBoolean(online);
		servlet.getOutput().writeUTF(name);
	}

	@Override
	public void readData(CommunicationServlet servlet) throws IOException {
		// TODO Auto-generated method stub
		userId = servlet.getInput().readInt();
		online = servlet.getInput().readBoolean();
		name = servlet.getInput().readUTF();
	}

}
