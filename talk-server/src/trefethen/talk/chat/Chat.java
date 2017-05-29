package trefethen.talk.chat;

import java.util.ArrayList;

public class Chat {
	
	private String name;
	private int id;
	private ArrayList<Message> messages;
	
	public Chat(String n, int i) {
		name = n;
		id = i;
		messages = new ArrayList<Message>();
	}

}
