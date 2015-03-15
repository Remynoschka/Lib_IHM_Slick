package slickIHM;

import java.util.regex.Pattern;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;

/**
 * Cette classe permet de creer des champs avec le texte masque par des '*'
 * 
 * @author Remynoschka
 * 
 */
public class ChampPassword extends ExtendedTextField {
	/**
	 * Expression reguliere definissant des caracteres
	 */
	protected static final Pattern	PWD_REGEX	= Pattern.compile("[^\\*]");

	/**
	 * 
	 * @param fenetre
	 * @param font
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param ecran
	 */
	public ChampPassword(GUIContext fenetre, Font font, int x, int y,
			int width, int height, Ecran ecran) {
		super(fenetre, font, x, y, width, height, ecran);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.ExtendedTextField#drawTexte(org.newdawn.slick.Graphics,
	 * int)
	 */
	@Override
	protected void drawTexte(Graphics g, int tx) {
		String pwd = getText();
		setText(PWD_REGEX.matcher(getText()).replaceAll("*"));
		g.translate(tx + 2, 0);
		g.drawString(getText(), x + 1, y + 1);
		setText(pwd);
	}

}
