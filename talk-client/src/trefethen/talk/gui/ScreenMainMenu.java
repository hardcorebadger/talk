package trefethen.talk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;

import trefethen.talk.client.TalkClient;
import trefethen.talk.packet.PacketChatHistory;
import trefethen.talk.packet.PacketLogin;
import trefethen.talk.packet.PacketUserChats;

public class ScreenMainMenu extends Screen {
	
	private boolean[] online;
	private Integer[] userIds;
	private Integer[] ids;
	private String[] names;
	private boolean shouldRefresh = false;
	
	public ScreenMainMenu(PacketUserChats p) {
		ids = p.ids;
		names = p.names;
		userIds = p.userIds;
		online = p.online;
	}

	@Override
	public void onOpen() {
		// TODO Auto-generated method stub
		GUIManager.pushNotification("Welcome back, " + GUIManager.username + "!");
	}

	@Override
	public void onEnable() {
		if (shouldRefresh) {
			TalkClient.client.addPacket(new PacketUserChats());
			return;
		}
		
		GUIManager.setNavBar("TALK");
		
		for (int i = 0; i < names.length; i++) {
			JButton b = GUIFactory.createChatButton(names[i], online[i]);
			b.setActionCommand(i+"");
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					int i = Integer.parseInt(actionEvent.getActionCommand());
					GUIManager.chatName = names[i];
					GUIManager.chatID = ids[i];
					TalkClient.client.addPacket(new PacketChatHistory(ids[i]));
				}
			});
			GUIManager.contentPanel.content.add(GUIFactory.wrap(b));
		}
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		shouldRefresh = true;
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub
		
	}
	
	public void refresh(PacketUserChats p) {
		ids = p.ids;
		names = p.names;
		userIds = p.userIds;
		online = p.online;
		shouldRefresh = false;
		GUIManager.contentPanel.content.removeAll();
		onEnable();
		GUIManager.refresh();
	}

	public void changeUserStatus(int userId, boolean o) {
		for (int i = 0; i < userIds.length; i++) {
			if (userIds[i] == userId)
				online[i] = o;
		}
		GUIManager.contentPanel.content.removeAll();
		onEnable();
		GUIManager.refresh();
	}

}
