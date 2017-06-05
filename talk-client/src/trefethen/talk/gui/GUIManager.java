package trefethen.talk.gui;

import java.awt.Container;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class GUIManager {
	
	private static JFrame frame;
	private static Container frameContainer;
	
	private static NavBar navBar;
	public static ContentPanel contentPanel;
	
	private static Stack<Screen> screenStack = new Stack<>();
		
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
		
	}
	
	public static void pushScreen(Screen s) {
		if (!screenStack.isEmpty())
			screenStack.peek().onDisable();
		screenStack.push(s);
		contentPanel.removeAll();
		s.onOpen();
		frameContainer.validate();
	}
	
	public static void popScreen() {
		if (screenStack.isEmpty())
			return;
		Screen s = screenStack.pop();
		s.onClose();
		contentPanel.removeAll();
		if (!screenStack.isEmpty())
			screenStack.peek().onEnable();
		frameContainer.validate();
	}
	
	public static void setNavBar(String title) {
		navBar.label.setText(title);
	}

}
