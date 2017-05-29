package trefethen.talk.gui;

import java.util.Scanner;

import trefethen.talk.client.TalkClient;
import trefethen.talk.packet.PacketLogin;
import trefethen.talk.packet.PacketRegister;
import trefethen.talk.packet.PacketUserChats;

public class CmdLineUI {
	
	public static Scanner input = new Scanner(System.in);
	private static int userID = -1;
	
	public static void startCmdLineUI() {
		System.out.println("=== Welcome to Talk CLI ===");
		login();
	}
	
	/*
	 * Main GUI Screens
	 */
	
	private static void login() {
		boolean reqSubmitted = false;
		while (!reqSubmitted) {
			System.out.print("Need an [account] or [login]? : ");
			String answ = input.nextLine();
			if (answ.equals("account")) {
				register();
				reqSubmitted = true;
			}
			else if (answ.equals("login")) {
				System.out.print("Username: ");
				String username = input.nextLine();
				System.out.print("Password: ");
				String password = input.nextLine();
				TalkClient.client.addPacket(new PacketLogin(username, password));
				System.out.println("Attempting Login...");
				reqSubmitted = true;
			} else
				System.out.println("Invalid Input!");
		}
	}
	
	private static void register() {
		System.out.print("Username: ");
		String username = input.nextLine();
		System.out.print("Password: ");
		String password = input.nextLine();
		TalkClient.client.addPacket(new PacketRegister(username, password));
		System.out.println("Attempting Registration...");
	}
	
	private static void mainMenu(String[] chatNames, Integer[] chatIDs) {
		System.out.println("=== Welcome to Talk Main Menu ===");
		System.out.println("Pick a chat from the list below to join:");
		for (int i = 0; i < chatNames.length; i++) {
			System.out.println(chatIDs[i] + " : " + chatNames[i]);
		}
		System.out.print("Enter Chat: ");
		int id = Integer.parseInt(input.nextLine());
//		TalkClient.client.addPacket(new PacketChatHistory(id));
	}
	
	/*
	 * Response Handlers
	 */
	
	public static void asyncOnLoginResponse(PacketLogin p) {
		if (p.loginID == -1) {
			System.out.println("Password Incorrect, try again");
			login();
		} else if (p.loginID == -2) {
			System.out.println("Username not found, try again");
			login();
		} else {
			System.out.println("Login Success, user id: " + p.loginID);
			userID = p.loginID;
			// continue to main
			TalkClient.client.addPacket(new PacketUserChats());
		}
	}
	
	public static void asyncOnRegisterResponse(PacketRegister p) {
		if (p.responseCode == 1) {
			// Register Successful
			System.out.println("Registration Successful, now try to log in.");
			login();
		} else {
			// Username Taken
			System.out.println("Username in use, try again");
			login();
		}
	}
	
	public static void asyncOnUserChatsResponse(PacketUserChats p) {
		mainMenu(p.names, p.ids);
	}

}
