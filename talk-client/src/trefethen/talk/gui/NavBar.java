package trefethen.talk.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import trefethen.talk.client.TalkClient;
import trefethen.talk.packet.PacketLogin;

public class NavBar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JLabel label;
	public JButton leftAction;

	public NavBar() {
		setLayout(new BorderLayout());
		setMaximumSize(new Dimension(400, 80));
		setMinimumSize(new Dimension(400, 80));
		setPreferredSize(new Dimension(400, 80));
		setBackground(GUIFactory.white);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, GUIFactory.light));
		
		label = new JLabel("TALK", JLabel.CENTER);
		add(label, BorderLayout.CENTER);
		
		leftAction = new JButton("X");
		leftAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				GUIManager.popScreen();
		    }
		});
		add(leftAction, BorderLayout.WEST);
		
	}

}
