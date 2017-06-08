package trefethen.talk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import trefethen.talk.client.TalkClient;
import trefethen.talk.packet.PacketLogin;
import trefethen.talk.packet.PacketRegister;

public class ScreenRegister extends Screen {
	
	private JTextField username;
	private JTextField password;
	private JTextField passwordConfirm;
	private JButton register;

	@Override
	public void onOpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		
		GUIManager.setNavBar("REGISTER");
		GUIManager.navBar.setActionEnabled(true);
		
		username = GUIFactory.createTextField("Username");
		password = GUIFactory.createPasswordField("Password");
		passwordConfirm = GUIFactory.createPasswordField("Repeat Password");
		register = GUIFactory.createButton("Register");
		
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (password.getText().equals(passwordConfirm.getText()))
					TalkClient.client.addPacket(new PacketRegister(username.getText(), password.getText()));
				else
					GUIManager.pushNotification("Passwords do not match.");
		    }
		});
		
		GUIManager.contentPanel.content.add(GUIFactory.wrap(username));
		GUIManager.contentPanel.content.add(GUIFactory.wrap(password));
		GUIManager.contentPanel.content.add(GUIFactory.wrap(passwordConfirm));
		GUIManager.contentPanel.content.add(GUIFactory.wrap(register));
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub
		
	}

}
