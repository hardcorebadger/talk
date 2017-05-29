package trefethen.talk.gui;

import java.util.Scanner;

import trefethen.talk.client.TalkClient;
import trefethen.talk.packet.PacketLogin;

public class CmdLineUI {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void startCmdLineUI() {
		System.out.println("=== Welcome to Talk CLI ===");
		login();
	}
	
	/*
	 * Main GUI Screens
	 */
	
	private static void login() {
		boolean loginSubmitted = false;
		while (!loginSubmitted) {
			System.out.print("Need an [account] or [login]? : ");
			String answ = input.nextLine();
			if (answ.equals("account"))
				register();
			else if (answ.equals("login")) {
				System.out.print("Username: ");
				String username = input.nextLine();
				System.out.print("Password: ");
				String password = input.nextLine();
				TalkClient.client.addPacket(new PacketLogin(username, password));
				loginSubmitted = true;
			} else
				System.out.println("Invalid Input!");
		}
	}
	
	private static void register() {
		
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
			// continue to main
		}
	}

}
