package trefethen.talk.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

import trefethen.talk.client.*;
import trefethen.talk.networking.CommunicationClient;

public abstract class Packet {
	
	protected int id;
	protected int userID;
	
	public static void writePacket(Packet packet, CommunicationClient client) {
		packet.userID = TalkClient.userID;
		DataOutputStream output = client.getOutput();
		try {
			output.writeInt(packet.id);
			output.writeInt(packet.userID);
			packet.writeData(client);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Packet readPacket(CommunicationClient client) {
		try {
			DataInputStream input = client.getInput();
			int id = input.readInt();
			int userID = input.readInt();
			Packet packet = getPacket(id);
			if (packet == null)
				System.out.println("No packet found with id: " + id);
			packet.userID = userID;
			packet.id = id;
			packet.readData(client);
			return packet;
			
		} catch (IOException e) {
			System.out.println("Failed to read");
			if (client.isRunning()) 
				client.disconnect();				
		} 
		return null;
	}
	
	public static void writeString(String string, DataOutputStream output) throws IOException {
        if (string.length() > 32767)
            throw new IOException("String too big");
        else {
            output.writeShort(string.length());
            output.writeChars(string);
        }
    }

    public static String readString(int length, DataInputStream input) throws IOException {
    	short lengthRead = input.readShort();
        if (lengthRead > length)
            throw new IOException("Received string length longer than maximum allowed (" + lengthRead + " > " + length + ")");
        else if (lengthRead < 0)
            throw new IOException("Received string length is less than zero! Weird string!");
        else {
            StringBuilder builder = new StringBuilder();
            for (int x = 0; x < lengthRead; ++x) {
            	builder.append(input.readChar());
            }
            return builder.toString();
        }
    }
    
    @SuppressWarnings("rawtypes")
	public static Packet getPacket(int id) {
        try {
            Class packetClass = (Class)packetMap.get(id);
            return packetClass == null ? null : (Packet)packetClass.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Packet not found with id " + id);
            return null;
        }
    }
	
	public abstract void writeData(CommunicationClient client) throws IOException;
	
	public abstract void readData(CommunicationClient client) throws IOException;
	
	@SuppressWarnings("rawtypes")
	private static HashMap<Integer, Class> packetMap = new HashMap<Integer, Class>();
	
	static {
		
		packetMap.put(0, PacketBlank.class);
		packetMap.put(1, PacketLogin.class);
		packetMap.put(2, PacketRegister.class);
		packetMap.put(3, PacketLogout.class);
		
		packetMap.put(4, PacketUserChats.class);
		packetMap.put(5, PacketChatHistory.class);
		packetMap.put(6, PacketChatMessage.class);
		
		packetMap.put(7, PacketUserStatus.class);
		
	}
	
}
