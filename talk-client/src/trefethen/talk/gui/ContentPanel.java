package trefethen.talk.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public ContentPanel() {
		setLayout(new BorderLayout());
		setMaximumSize(new Dimension(400, 320));
		setBackground(GUIFactory.white);
	}

}
