package trefethen.talk.user;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import trefethen.talk.networking.CommunicationServlet;

public class UserManager {
	
	private static String userDataFile = "resources/users.json";
	
	private static HashMap<Integer, User> users = new HashMap<Integer, User>();
	private static HashMap<CommunicationServlet, Integer> connections = new HashMap<CommunicationServlet, Integer>();

	private static int nextID = 0;
	
	/*
	 * Public Interface
	 */
	
	@SuppressWarnings("unchecked")
	public static void loadUsers() {
		System.out.println("USER MANAGER : Loading Users...");
		JSONParser parser = new JSONParser();
		try {
            Object obj = parser.parse(new FileReader(userDataFile));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray userJSON = (JSONArray) jsonObject.get("Users");
            Iterator<JSONObject> iterator = userJSON.iterator();
            while (iterator.hasNext()) {
            	JSONObject userObject = iterator.next();
                User u = new User((String) userObject.get("Name"), (String) userObject.get("Password"), nextID);
                users.put(nextID, u);
                nextID++;
            }
            System.out.println("USER MANAGER : Users Loaded!");
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@SuppressWarnings("unchecked")
	public static void saveUsers() {
		System.out.println("USER MANAGER : Saving Users...");
		try {
			JSONArray usersArray = new JSONArray();
			Iterator<User> iterator = users.values().iterator();
			while (iterator.hasNext()) {
				usersArray.add(iterator.next().toJSON());
			}
			JSONObject obj = new JSONObject();
			obj.put("Users", usersArray);
			
			FileWriter file = new FileWriter(userDataFile);
			file.write(obj.toJSONString());
			file.close();
			System.out.println("USER MANAGER : Users Saved!");
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void displayUsers() {
		Iterator<User> iterator = users.values().iterator();
		while (iterator.hasNext()) {
			iterator.next().display();
		}
	}
	
	public static int login(String name, String password, CommunicationServlet s) {
		Iterator<User> iterator = users.values().iterator();
		while (iterator.hasNext()) {
			User u = iterator.next();
			if (u.attemptLogin(name,  password)) {
				connections.put(s, u.getID());
				return u.getID();
			}
		}
		return -1;
	}
	
	public static void register(String name, String password) {
		users.put(nextID, new User(name, password, nextID));
		nextID++;
	}
	
	/*
	 * Private Helpers
	 */
}