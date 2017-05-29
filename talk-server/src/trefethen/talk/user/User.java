package trefethen.talk.user;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class User {
	
	private String name;
	private String password;
	private int id;
	private ArrayList<Integer> chats;
	
	public User(String n, String p, int i) {
		name = n;
		password = p;
		id = i;
		chats = new ArrayList<Integer>();
	}
	
	@SuppressWarnings("unchecked")
	public User(JSONObject userObject) {
        this((String) userObject.get("Name"), (String) userObject.get("Password"), ((Long) userObject.get("ID")).intValue());
        JSONArray chatsJSON = (JSONArray) userObject.get("Chats");
		Iterator<Long> iterator = chatsJSON.iterator();
        while (iterator.hasNext()) {
        	chats.add(iterator.next().intValue());
        }
	}
	
	public int attemptLogin(String n, String p) {
		if (n.equals(name)) {
			if (p.equals(password))
				return 1;
			else
				return -1;
		} else 
			return 0;
	}
	
	public void display() {
		System.out.print(name + ":" + password + ":" + id + ":[");
		Iterator<Integer> iterator = chats.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + ",");
		}
		System.out.println("]");
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("Name", name);
		obj.put("Password", password);
		obj.put("ID", id);
		
		JSONArray chatArray = new JSONArray();
		Iterator<Integer> iterator = chats.iterator();
		while (iterator.hasNext()) {
			chatArray.add(iterator.next());
		}		
		obj.put("Chats", chatArray);
		return obj;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Integer> getChats() {
		return chats;
	}
}
