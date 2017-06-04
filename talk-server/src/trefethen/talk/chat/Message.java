package trefethen.talk.chat;

import org.json.simple.JSONObject;

import trefethen.talk.user.User;
import trefethen.talk.user.UserManager;;

public class Message {
	
	private User sender;
	private String message;
	
	public Message(User u, String m) {
		sender = u;
		message = m;
	}
	
	public Message(JSONObject obj) {
		int i = ((Long)obj.get("Sender")).intValue();
		sender = UserManager.getUser(i);
		message = (String) obj.get("Message");
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("Sender", sender.getID());
		obj.put("Message", message);
		return obj;
	}
	
	public void display() {
		System.out.println(sender.getName() + " : " + message);
	}
	
	public String getName() {
		return sender.getName();
	}
	
	public String getMessage() {
		return message;
	}

}
