package trefethen.talk.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class BGColorButton extends JButton implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	private Color defaultColor;
	private Color mouseOverColor;
	private Color pressedColor;

		 
	public BGColorButton(String text, Color d, Color m, Color p) { 
	 
		super(text);
		setBackground(d);
		defaultColor = d;
		mouseOverColor = m;
		pressedColor = p;
		this.setOpaque(true);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(this);
	 
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this) {  this.setBackground(this.pressedColor); GUIManager.refresh();}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this) {  this.setBackground(this.defaultColor); GUIManager.refresh();}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this) {  this.setBackground(this.mouseOverColor); GUIManager.refresh();}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this) { this.setBackground(this.defaultColor); GUIManager.refresh();}
	}

}
