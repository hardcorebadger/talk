package trefethen.talk.chat;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ChatManager {
	
	private static String chatDataFile = "resources/chats.json";
	
	private static HashMap<Integer, Chat> chats = new HashMap<Integer, Chat>();
	
	private static int nextID = 0;
	
	@SuppressWarnings("unchecked")
	public static void loadChats() {
		System.out.println("CHAT MANAGER : Loading Chats...");
		JSONParser parser = new JSONParser();
		try {
            Object obj = parser.parse(new FileReader(chatDataFile));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray chatsJSON = (JSONArray) jsonObject.get("Chats");
            Iterator<JSONObject> iterator = chatsJSON.iterator();
            while (iterator.hasNext()) {
            	JSONObject chatObject = iterator.next();
            	Chat c = new Chat(chatObject);                
            	chats.put(c.getID(), c);
                nextID = Math.max(nextID, c.getID() + 1); // not the safest but hey you can't delete users so
            }
            System.out.println("CHAT MANAGER : Chats Loaded!");
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@SuppressWarnings("unchecked")
	public static void saveChats() {
		System.out.println("CHAT MANAGER : Saving Chats...");
		try {
			JSONArray usersArray = new JSONArray();
			Iterator<Chat> iterator = chats.values().iterator();
			while (iterator.hasNext()) {
				usersArray.add(iterator.next().toJSON());
			}
			JSONObject obj = new JSONObject();
			obj.put("Chats", usersArray);
			
			FileWriter file = new FileWriter(chatDataFile);
			file.write(obj.toJSONString());
			file.close();
			System.out.println("CHAT MANAGER : Chats Saved!");
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void displayChats() {
		Iterator<Chat> iterator = chats.values().iterator();
		while (iterator.hasNext()) {
			iterator.next().display();
		}
	}
	
	public static Chat getChat(int id) {
		return chats.get(id);
	}

}
