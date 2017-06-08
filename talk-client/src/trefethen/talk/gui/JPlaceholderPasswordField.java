package trefethen.talk.gui;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPasswordField;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class JPlaceholderPasswordField extends JPasswordField {

	private String ph;

	public JPlaceholderPasswordField(String ph) {
		this.ph = ph;
	}
	
	public JPlaceholderPasswordField() {
		this.ph = null;
	}

	
	@Override
	public String getText() {
		@SuppressWarnings("deprecation")
		String text = super.getText();

		if (text.trim().length() == 0 && ph != null) {
			text = ph;
		}

		return text;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		
		if (super.getText().length() > 0 || ph == null) {
			return;
		}
		
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(super.getDisabledTextColor());
		g2.drawString(ph, 40, g.getFontMetrics().getMaxAscent() + 20);
	}
}