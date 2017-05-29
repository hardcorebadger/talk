package trefethen.talk.gui;

import java.util.Scanner;

import trefethen.talk.client.TalkClient;
import trefethen.talk.packet.PacketLogin;
import trefethen.talk.packet.PacketRegister;

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

}
