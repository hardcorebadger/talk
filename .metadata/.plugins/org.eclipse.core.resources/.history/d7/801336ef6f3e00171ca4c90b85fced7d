package trefethen.talk.user;

import org.json.simple.JSONObject;

public class User {
	
	private String name;
	private String password;
	private int id;
	
	public User(String n, String p, int i) {
		name = n;
		password = p;
		id = i;
	}
	
	public boolean attemptLogin(String n, String p) {
		return (n.equals(name) && p.equals(password));
	}
	
	public void display() {
		System.out.println(name + ":" + password + ":" + id);
	}
	
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("Name", name);
		obj.put("Password", password);
		return obj;
	}
	
	public int getID() {
		return id;
	}
}
