package trefethen.talk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import trefethen.talk.client.TalkClient;
import trefethen.talk.packet.PacketLogin;
import trefethen.talk.packet.PacketLogout;

public class ScreenLogin extends Screen {
	
	private JTextField username;
	private JTextField password;
	private JButton login;
	private JButton register;
	
	private boolean shouldTriggerLogout = false;
	
	public ScreenLogin() {
		
	}

	@Override
	public void onOpen() {
		
	}

	@Override
	public void onEnable() {
		
		GUIManager.navBar.setActionEnabled(false);
		
		if (shouldTriggerLogout)
			TalkClient.client.addPacket(new PacketLogout());
		
		GUIManager.setNavBar("LOGIN");
		
		username = GUIFactory.createTextField("Username");
		password = GUIFactory.createPasswordField("Password");
		login = GUIFactory.createButton("Login");
		register = GUIFactory.createButton("Register");
		
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				TalkClient.client.addPacket(new PacketLogin(username.getText(), password.getText()));
		    }
		});
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				GUIManager.pushScreen(new ScreenRegister());
		    }
		});
		
		GUIManager.contentPanel.content.add(GUIFactory.wrap(username));
		GUIManager.contentPanel.content.add(GUIFactory.wrap(password));
		GUIManager.contentPanel.content.add(GUIFactory.wrap(login));
		GUIManager.contentPanel.content.add(GUIFactory.wrap(register));
		
	}

	@Override
	public void onDisable() {
		shouldTriggerLogout = true;
	}

	@Override
	public void onClose() {
		
	}

}
