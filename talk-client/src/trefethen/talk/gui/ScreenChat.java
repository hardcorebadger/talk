package trefethen.talk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import trefethen.talk.client.TalkClient;
import trefethen.talk.packet.PacketChatHistory;
import trefethen.talk.packet.PacketChatMessage;

public class ScreenChat extends Screen {
	
	private String[] messages;
	private String[] usernames;
	
	private JTextArea textArea;
	private JTextField input;
	
	public ScreenChat(PacketChatHistory p) {
		usernames = p.usernames;
		messages = p.messages;
	}

	@Override
	public void onOpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		GUIManager.setNavBar(GUIManager.chatName.toUpperCase());
		
		textArea = GUIFactory.createChatMessageArea();
		GUIManager.contentPanel.content.add(GUIFactory.scrollWrap(textArea));
		input = GUIFactory.createTextField("Type something...");
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				TalkClient.client.addPacket(new PacketChatMessage(GUIManager.chatID, input.getText()));
				input.setText("");
		    }
		});
		GUIManager.contentPanel.content.add(GUIFactory.wrap(input));

		String s = "";
		for (int i = 0; i < messages.length; i++) {
			s += usernames[i] + ": " + messages[i] + "\n";
		}
		textArea.setText(s);
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub
		
	}
	
	public void onMessage(PacketChatMessage m) {
		textArea.setText(textArea.getText()+m.name + ": " + m.message + "\n");
	}

}
