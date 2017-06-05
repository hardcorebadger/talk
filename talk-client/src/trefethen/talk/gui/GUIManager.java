package trefethen.talk.gui;

import java.awt.Container;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import trefethen.talk.client.TalkClient;
import trefethen.talk.packet.PacketChatHistory;
import trefethen.talk.packet.PacketChatMessage;
import trefethen.talk.packet.PacketLogin;
import trefethen.talk.packet.PacketRegister;
import trefethen.talk.packet.PacketUserChats;

public class GUIManager {
	
	private static JFrame frame;
	private static Container frameContainer;
	
	private static NavBar navBar;
	public static ContentPanel contentPanel;
	
	private static Stack<Screen> screenStack = new Stack<>();
	
	public static String username;
	public static int userID;
		
	public static void initialize() {
		frame = new JFrame("Talk");
		frame.setSize(400,400);
		
		frameContainer = frame.getContentPane();
		frameContainer.setLayout(new BoxLayout(frameContainer,BoxLayout.Y_AXIS));
		frameContainer.setBackground(GUIFactory.white);
		
		navBar = new NavBar();
		frameContainer.add(navBar);
		
		contentPanel = new ContentPanel();
		frameContainer.add(contentPanel);
		
		frame.setResizable(false);
		frame.setVisible(true);
		
		pushScreen(new ScreenLogin());

	}
	
	public static void pushScreen(Screen s) {
		if (!screenStack.isEmpty())
			screenStack.peek().onDisable();
		screenStack.push(s);
		contentPanel.removeAll();
		s.onEnable();
		s.onOpen();
		frameContainer.validate();
	}
	
	public static void popScreen() {
		if (screenStack.isEmpty())
			return;
		Screen s = screenStack.pop();
		s.onDisable();
		s.onClose();
		contentPanel.removeAll();
		if (!screenStack.isEmpty())
			screenStack.peek().onEnable();
		frameContainer.validate();
	}
	
	public static void setNavBar(String title) {
		navBar.label.setText(title);
	}

	/*
	 * Response Handlers
	 */
	
	public static void asyncOnLoginResponse(PacketLogin p) {
		if (p.loginID == -1) {

		} else if (p.loginID == -2) {

		} else {
			// continue to main
			username = p.name;
			userID = p.loginID;
			
			TalkClient.client.addPacket(new PacketUserChats());
		}
	}
	
	public static void asyncOnRegisterResponse(PacketRegister p) {
		if (p.responseCode == 1) {
			// Register Successful
			
		} else {
			// Username Taken

		}
	}
	
	public static void asyncOnUserChatsResponse(PacketUserChats p) {

	}
	
	public static void asyncOnChatHistoryResponse(PacketChatHistory p) {

	}
	
	public static void asynOnChatMessage(PacketChatMessage p) {

	}
}