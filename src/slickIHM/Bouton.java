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
public class Bouton extends AbstractIHMComponent {
	/**
	 * Le texte du bouton
	 */
	private String		texte		= "";
	/**
	 * L'action effectuee lors de l'appui du bouton
	 */
	private Action		action;
	/**
	 * Le raccourcis clavier
	 */
	private int			mnemonic	= -1;
	/**
	 * Couleur du texte
	 */
	private Color		texteColor	= Color.black;
	/**
	 * Police du texte
	 */
	private Font		texteFont;
	// TODO taille des boutons (y'a mieux, tu verra plus tard)
	/**
	 * Largeur des boutons prochainement crees
	 */
	private static int	WIDTH		= 100;
	/**
	 * Hauteur des boutons prochainement crees
	 */
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
	 *            : l'action executee au clic sur le bouton
	 * @param ecran
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
	 * @param ecran
	 *            : l'ecran auquel appartient le bouton
	 * @throws SlickException
	 */
	public Bouton(Fenetre fenetre, int x, int y, String texte, Action a,
			Ecran ecran) throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran);
		build(fenetre, x, y, texte, a, ecran);
	}

	/**
	 * Cette methode centralise la construction du bouton
	 * 
	 * @param fenetre
	 * @param x
	 * @param y
	 * @param texte
	 * @param a
	 * @param ecran
	 */
	private void build(Fenetre fenetre, int x, int y, String texte, Action a,
			Ecran ecran) {
		this.texte = texte;
		this.action = a;
		texteFont = fenetre.getGraphics().getFont();
	}

	/**
	 * Definit l'action effectue par ce bouton
	 * 
	 * @param action
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	/**
	 * Cette methode definit la touche activant ce bouton
	 * 
	 * @param mnemonic
	 */
	public void setMnemonic(int mnemonic) {
		this.mnemonic = mnemonic;
	}

	/**
	 * Cette methode lance l'action du bouton
	 */
	public void doAction() {
		if (action != null)
			action.actionPerformed();
		else
			System.err.println("Aucune action définie");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * slickIHM.AbstractIHMComponent#doRender(org.newdawn.slick.gui.GUIContext,
	 * org.newdawn.slick.Graphics)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.AbstractIHMComponent#actionKeyReleased(int, char)
	 */
	@Override
	public void actionKeyReleased(int key, char c) {
		if (key == this.mnemonic) {
			doAction();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.AbstractIHMComponent#actionMouseClicked(int, int, int, int)
	 */
	@Override
	public void actionMouseClicked(int button, int x, int y, int clickCount) {
		doAction();
	}

	/**
	 * Modifie la couleur du texte sur le bouton
	 * 
	 * @param texteColor
	 *            : la couleur du texte
	 */
	public void setTexteColor(Color texteColor) {
		this.texteColor = texteColor;
	}

	/**
	 * Modifie la police du texte sur le bouton
	 * 
	 * @param texteFont
	 *            : la police du texte
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
	 * Cette methode permet de savoir quel est actuellement la largeur des
	 * boutons prochainement crees
	 * 
	 * @return la largeur des boutons
	 */
	public static int getButtonWidth() {
		return WIDTH;
	}

	/**
	 * Cette methode permet de savoir quel est actuellement la hauteur des
	 * boutons prochainement crees
	 * 
	 * @return la hauteur des boutons
	 */
	public static int getButtonHeight() {
		return HEIGHT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.newdawn.slick.gui.MouseOverArea#setNormalImage(org.newdawn.slick.
	 * Image)
	 */
	@Override
	public void setNormalImage(Image image) {
		super.setNormalImage(image.getScaledCopy(WIDTH, HEIGHT));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.newdawn.slick.gui.MouseOverArea#setMouseOverImage(org.newdawn.slick
	 * .Image)
	 */
	@Override
	public void setMouseOverImage(Image image) {
		super.setMouseOverImage(image.getScaledCopy(WIDTH, HEIGHT));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.newdawn.slick.gui.MouseOverArea#setMouseDownImage(org.newdawn.slick
	 * .Image)
	 */
	@Override
	public void setMouseDownImage(Image image) {
		super.setMouseDownImage(image.getScaledCopy(WIDTH, HEIGHT));
	}
}
