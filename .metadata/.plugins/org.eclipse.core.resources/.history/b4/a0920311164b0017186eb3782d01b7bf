package trefethen.talk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Notification extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public Notification(String s) {
		setSize(400,30);
		setLocation(0, 0);
		setBackground(GUIFactory.light);
		add(new JLabel(s, JLabel.CENTER));
		Notification n = this;
		Timer t = new Timer(5000, new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				GUIManager.removeNotification(n);
		    }
		});
		t.start();
	}

}
