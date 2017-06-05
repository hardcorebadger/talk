package trefethen.talk.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
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
	
	public static JButton createButton(String name) {
		JButton btn = new JButton(name);
		btn.setPreferredSize(standardDimension);
		btn.setMaximumSize(standardDimension);
		btn.setMinimumSize(standardDimension);
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		return btn;
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

}
