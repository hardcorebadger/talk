package trefethen.talk.chat;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import trefethen.talk.packet.PacketChatMessage;
import trefethen.talk.user.User;

public class Chat {
	
	private String name;
	private int id;
	private ArrayList<Message> messages;
	private ArrayList<User> listeners;
	
	public Chat(String n, int i) {
		name = n;
		id = i;
		messages = new ArrayList<Message>();
		listeners = new ArrayList<User>();
	}
	
	@SuppressWarnings("unchecked")
	public Chat(JSONObject obj) {
        this((String) obj.get("Name"), ((Long)obj.get("ID")).intValue());
        JSONArray messagesJSON = (JSONArray) obj.get("Messages");
		Iterator<JSONObject> iterator = messagesJSON.iterator();
        while (iterator.hasNext()) {
        	messages.add(new Message(iterator.next()));
        }
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("Name", name);
		obj.put("ID", id);
		
		JSONArray messageArray = new JSONArray();
		Iterator<Message> iterator = messages.iterator();
		while (iterator.hasNext()) {
			messageArray.add(iterator.next().toJSON());
		}		
		obj.put("Messages", messageArray);
		return obj;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void display() {
		System.out.println("CHAT: " + name + ":" + id + "[");
		Iterator<Message> iterator = messages.iterator();
		while (iterator.hasNext()) {
			iterator.next().display();
		}
		System.out.println("]");
	}
	
	public String[] getUsernameArray() {
		String[] usernames = new String[messages.size()];
		for (int i = 0; i < usernames.length; i++) {
			usernames[i] = messages.get(i).getName();
		}
		return usernames;
	}
	
	public String[] getMessagesArray() {
		String[] msg = new String[messages.size()];
		for (int i = 0; i < msg.length; i++) {
			msg[i] = messages.get(i).getMessage();
		}
		return msg;
	}
	
	public void addMessage(User u, String m) {
		Message msg = new Message(u,m);
		messages.add(msg);
		Iterator<User> iterator = listeners.iterator();
		while (iterator.hasNext()) {
			iterator.next().sendPacket(new PacketChatMessage(msg));
		}
	}

	public void addListener(User user) {
		listeners.add(user);
	}
	
	public void removeListener(User u) {
		listeners.remove(u);
	}

}
