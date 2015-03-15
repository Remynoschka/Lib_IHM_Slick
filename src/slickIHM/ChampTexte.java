package slickIHM;

import org.newdawn.slick.Font;
import org.newdawn.slick.gui.GUIContext;

/**
 * Cette classe permet de creer un champ texte simple
 * 
 * @author Remynoschka
 * @see ExtendedTextField
 */
public class ChampTexte extends ExtendedTextField {

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
	public ChampTexte(GUIContext fenetre, Font font, int x, int y, int width,
			int height, Ecran ecran) {
		super(fenetre, font, x, y, width, height, ecran);
	}

}
