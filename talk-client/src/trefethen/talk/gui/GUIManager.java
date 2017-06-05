package trefethen.talk.gui;

import java.awt.Color;
import java.awt.Container;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import trefethen.talk.client.TalkClient;
import trefethen.talk.packet.PacketChatHistory;
import trefethen.talk.packet.PacketChatMessage;
import trefethen.talk.packet.PacketLogin;
import trefethen.talk.packet.PacketRegister;
import trefethen.talk.packet.PacketUserChats;

public class GUIManager {
	
	private static JFrame frame;
	private static Container frameContainer;
	private static JLayeredPane layeredPane;
	
	public static Container mainContainer;
	
	public static NavBar navBar;
	public static ContentPanel contentPanel;
	
	private static Stack<Screen> screenStack = new Stack<>();
	
	public static String username;
	public static String chatName;
	public static int userID;
		
	public static void initialize() {
		frame = new JFrame("Talk");
		frame.setSize(400,400);
		
		frameContainer = frame.getContentPane();
		frameContainer.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setSize(400, 400);
		
		frameContainer.add(layeredPane);
		
		mainContainer = new Container();
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
		mainContainer.setSize(400, 400);
		
		layeredPane.add(mainContainer, new Integer(0));
		
		navBar = new NavBar();
		mainContainer.add(navBar);
		
		contentPanel = new ContentPanel();
		mainContainer.add(contentPanel);
		
		frame.setResizable(false);
		frame.setVisible(true);
		
		pushScreen(new ScreenLogin());
	}
	
	public static void pushScreen(Screen s) {
		if (!screenStack.isEmpty())
			screenStack.peek().onDisable();
		screenStack.push(s);
		contentPanel.content.removeAll();
		s.onEnable();
		refresh();
		s.onOpen();
	}
	
	public static void popScreen() {
		if (screenStack.isEmpty())
			return;
		Screen s = screenStack.pop();
		s.onDisable();
		contentPanel.content.removeAll();
		if (!screenStack.isEmpty())
			screenStack.peek().onEnable();
		refresh();
		s.onClose();
	}
	
	public static void setNavBar(String title) {
		navBar.label.setText(title);
	}
	
	public static void pushNotification(String s) {
		layeredPane.add(new Notification(s), new Integer(4000));
		frameContainer.validate();
	}
	
	public static void removeNotification(Notification n) {
		layeredPane.remove(n);
		refresh();
	}
	
	private static void refresh() {
		frameContainer.revalidate();
		frameContainer.repaint();
	}

	/*
	 * Response Handlers
	 */
	
	public static void asyncOnLoginResponse(PacketLogin p) {
		if (p.loginID == -1) {
			pushNotification("Password incorrect.");
		} else if (p.loginID == -2) {
			pushNotification("Username not found.");
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
			popScreen();
			pushNotification("Registration success!");
		} else {
			// Username Taken
			pushNotification("Username in use.");
		}
	}
	
	public static void asyncOnUserChatsResponse(PacketUserChats p) {
		pushScreen(new ScreenMainMenu(p));
	}
	
	public static void asyncOnChatHistoryResponse(PacketChatHistory p) {
		pushScreen(new ScreenChat(p));
	}
	
	public static void asynOnChatMessage(PacketChatMessage p) {

	}
}
