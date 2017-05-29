package trefethen.talk.chat;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Chat {
	
	private String name;
	private int id;
	private ArrayList<Message> messages;
	
	public Chat(String n, int i) {
		name = n;
		id = i;
		messages = new ArrayList<Message>();
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
	
	public void display() {
		System.out.println("CHAT: " + name + ":" + id + "[");
		Iterator<Message> iterator = messages.iterator();
		while (iterator.hasNext()) {
			iterator.next().display();
		}
		System.out.println("]");
	}

}
