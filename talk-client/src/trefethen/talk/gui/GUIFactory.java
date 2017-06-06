package trefethen.talk.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUIFactory {
	
	public static Color white = Color.white;
	public static Color light = new Color(230,231,232);
	public static Dimension standardDimension = new Dimension(400,50);
	
	public static JTextField createTextField() {
		JTextField text = new JTextField(20);
//		text.setMaximumSize(standardDimension);
		return text;
	}
	
	public static JPasswordField createPasswordField() {
		JPasswordField text = new JPasswordField(20);
//		text.setMaximumSize(standardDimension);
		return text;
	}
	
	public static JButton createChatButton(String name, boolean online) {
		System.out.println(online);
		JButton btn = new JButton(name);
		btn.setPreferredSize(standardDimension);
		btn.setMaximumSize(standardDimension);
		btn.setMinimumSize(standardDimension);
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		if (!online) {
			btn.setText("("+name+")");
		}
		return btn;
	}
	
	public static JButton createButton(String name) {
		JButton btn = new JButton(name);
		btn.setPreferredSize(standardDimension);
		btn.setMaximumSize(standardDimension);
		btn.setMinimumSize(standardDimension);
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		return btn;
	}
	
	public static JPanel createChatMessage(String username, String message) {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(400,30));
		p.setMinimumSize(new Dimension(400,30));
		p.add(new JLabel((""+username.charAt(0)).toUpperCase(), JLabel.LEFT), BorderLayout.WEST);
		p.add(new JLabel(message, JLabel.LEFT), BorderLayout.CENTER);
		return p;
	}
	
	public static JTextArea createChatMessageArea() {
		JTextArea a = new JTextArea();
		a.setMinimumSize(new Dimension(400,250));
		return a;
	}
	
	public static JScrollPane scrollWrap(JComponent c) {
		JScrollPane a = new JScrollPane();
		a.setViewportView(c);
		a.setPreferredSize(new Dimension(400,250));
		a.setMaximumSize(new Dimension(400,250));
		a.setMinimumSize(new Dimension(400,250));
		return a;
	}
	
	public static Box wrap(JComponent c) {
		Box box = new Box(0);
		box.setPreferredSize(standardDimension);
		box.setMaximumSize(standardDimension);
		box.setMinimumSize(standardDimension);
//		box.setLayout(new GridLayout(1,1));
		box.add(c);
		return box;
	}
	
	public static Box wrap(JComponent c, Dimension d) {
		Box box = new Box(0);
		box.setPreferredSize(d);
		box.setMaximumSize(d);
		box.setMinimumSize(d);
//		box.setLayout(new GridLayout(1,1));
		box.add(c);
		return box;
	}

}
