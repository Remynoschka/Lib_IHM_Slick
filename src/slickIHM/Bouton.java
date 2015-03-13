package slickIHM;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

/**
 * Une classe implementant les simples boutons.<br/>
 * Les variables globales WIDTH et HEIGHT configurent la taille de la zone de
 * detection de la souris sur le bouton.
 * 
 * @author Remynoschka
 * 
 */
public class Bouton extends IHMComponent {
	private String		texte		= "";
	private Action		action;
	private int			mnemonic	= -1;
	private Color		texteColor	= Color.black;
	private Font		texteFont;
	// TODO taille des boutons (y'a mieux, tu verra plus tard)
	private static int	WIDTH		= 100;
	private static int	HEIGHT		= 50;

	/**
	 * 
	 * @param fenetre
	 *            : la fenetre contenant le bouton
	 * @param x
	 * @param y
	 * @param texte
	 *            : le texte du bouton
	 * @param a
	 *            : l'action executee au clic
	 * @param etat
	 *            : l'ecran auquel appartient le bouton
	 * @param mnemonic
	 *            : le raccourcis clavier
	 * @throws SlickException
	 */
	public Bouton(Fenetre fenetre, int x, int y, String texte, Action a,
			Ecran ecran, int mnemonic) throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran);
		build(fenetre, x, y, texte, a, ecran);
		this.mnemonic = mnemonic;
	}

	/**
	 * 
	 * @param fenetre
	 *            : la fenetre contenant le bouton
	 * @param x
	 * @param y
	 * @param texte
	 *            : le texte du bouton
	 * @param a
	 *            : l'action executee au clic
	 * @param etat
	 *            : l'ecran auquel appartient le bouton
	 * @throws SlickException
	 */
	public Bouton(Fenetre fenetre, int x, int y, String texte, Action a,
			Ecran ecran) throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran);
		build(fenetre, x, y, texte, a, ecran);
	}

	private void build(Fenetre fenetre, int x, int y, String texte, Action a,
			Ecran ecran) {
		this.texte = texte;
		this.action = a;
		texteFont = fenetre.getGraphics().getFont();
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void setMnemonic(int mnemonic) {
		this.mnemonic = mnemonic;
	}

	public void doAction() {
		if (action != null)
			action.actionPerformed();
		else
			System.err.println("Aucune action définie");

	}

	@Override
	public void doRender(GUIContext fenetre, Graphics g) {
		super.doRender(fenetre, g);
		if (isEnabled())
			g.setColor(new Color(36, 36, 36));
		else
			g.setColor(texteColor);
		g.setFont(texteFont);
		// dessin du texte
		g.drawString(texte, (float) (this.getX() + getWidth() / 2 - g.getFont()
				.getWidth(texte) / 2), this.getY() + getHeight() / 2
				- g.getFont().getHeight(texte) / 2);
	}

	@Override
	public void actionKeyReleased(int key, char c) {
		if (key == this.mnemonic) {
			doAction();
		}
	}

	@Override
	public void actionMouseClicked(int button, int x, int y, int clickCount) {
		doAction();
	}

	/**
	 * Modifie la couleur du texte sur le bouton
	 * 
	 * @param texteColor
	 */
	public void setTexteColor(Color texteColor) {
		this.texteColor = texteColor;
	}

	/**
	 * Modifie la police du texte sur le bouton
	 * 
	 * @param texteFont
	 */
	public void setTexteFont(Font texteFont) {
		this.texteFont = texteFont;
	}

	/**
	 * Definit le texte du bouton
	 * 
	 * @param texte
	 *            : le texte du bouton
	 */
	public void setTexte(String texte) {
		this.texte = texte;
	}

	/**
	 * Modifie la taille des boutons prochainement crees
	 * 
	 * @param w
	 *            : largeur
	 * @param h
	 *            : hauteur
	 */
	public static void setDimensions(int w, int h) {
		WIDTH = w;
		HEIGHT = h;
	}

	/**
	 * 
	 * @return la largeur des boutons
	 */
	public static int getButtonWidth() {
		return WIDTH;
	}

	/**
	 * 
	 * @return la hauteur des boutons
	 */
	public static int getButtonHeight() {
		return HEIGHT;
	}

	@Override
	public void setNormalImage(Image image) {
		super.setNormalImage(image.getScaledCopy(WIDTH, HEIGHT));
	}

	@Override
	public void setMouseOverImage(Image image) {
		super.setMouseOverImage(image.getScaledCopy(WIDTH, HEIGHT));
	}

	@Override
	public void setMouseDownImage(Image image) {
		super.setMouseDownImage(image.getScaledCopy(WIDTH, HEIGHT));
	}
}
