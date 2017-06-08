package trefethen.talk.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NavBar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static ImageIcon cancelIcon = new ImageIcon("resources/gui/cancel.png");
	private static ImageIcon menuIcon =  new ImageIcon("resources/gui/menu.png");
	
	static {
		Image i = cancelIcon.getImage();
		cancelIcon = new ImageIcon(i.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		
		i = menuIcon.getImage();
		menuIcon = new ImageIcon(i.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
	}

	
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
		label.setFont(GUIFactory.monix);
		add(label, BorderLayout.CENTER);
		
		
		
		leftAction = new JButton(cancelIcon);
		leftAction.setBorder(BorderFactory.createEmptyBorder());
		leftAction.setPreferredSize(new Dimension(80,80));
		leftAction.setMaximumSize(new Dimension(80,80));
		leftAction.setMinimumSize(new Dimension(80,80));
		leftAction.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leftAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				GUIManager.popScreen();
		    }
		});
		add(createBox(leftAction), BorderLayout.WEST);
		add(createBox(null), BorderLayout.EAST);
	}
	
	private Box createBox(JComponent c) {
		Box box = new Box(0);
		box.setPreferredSize(new Dimension(80,80));
		box.setMaximumSize(new Dimension(80,80));
		box.setMinimumSize(new Dimension(80,80));
		if (c != null)
			box.add(c);
		return box;
	}
	
	public void setActionEnabled(boolean e) {
		leftAction.setEnabled(e);
	}

}
