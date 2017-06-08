package trefethen.talk.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Notification extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int alpha = 255;
	
	public Notification(String s) {
		setSize(400,30);
		setLocation(0, 0);
		setBackground(GUIFactory.light);
		JLabel l = new JLabel(s, JLabel.CENTER);
		l.setBackground(GUIFactory.dark);
		l.setFont(GUIFactory.clearSans);
		add(l);
		Notification n = this;
		Timer t = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				alpha -= 5;
				if (alpha <= 100)
					GUIManager.removeNotification(n);
				else {
					Color c = new Color(GUIFactory.light.getRed(), GUIFactory.light.getGreen(), GUIFactory.light.getBlue(), alpha);
					setBackground(c);
					c = new Color(GUIFactory.dark.getRed(), GUIFactory.dark.getGreen(), GUIFactory.dark.getBlue(), alpha);
					l.setBackground(GUIFactory.dark);
					GUIManager.refresh();
				}
		    }
		});
		t.start();
	}

}
