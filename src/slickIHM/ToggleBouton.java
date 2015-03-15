package slickIHM;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

/**
 * Cette classe cree un Toggle Button qui permet d'etre dans un groupe de
 * boutons dont 1 seul peut etre selectionne.
 * 
 * @author Remynoschka
 * 
 */
public class ToggleBouton extends BoutonGroupable {
	// TODO On peut ameliorer la taille des ToggleBoutons
	/**
	 * Largeur des boutons prochainement crees
	 */
	private static int	WIDTH	= 100;
	/**
	 * Hauteur des boutons prochainement crees
	 */
	private static int	HEIGHT	= 50;

	/**
	 * Image quand le bouton est active
	 */
	private Image		imageDown;

	/**
	 * Image quand les bouton n'est pas active
	 */
	private Image		imageUp;

	/**
	 * Image quand la souris passe au dessus du bouton quand il n'est pas active
	 */
	private Image		imageUpOver;

	/**
	 * 
	 * @param fenetre
	 * @param x
	 * @param y
	 * @param ecran
	 * @throws SlickException
	 */
	public ToggleBouton(Fenetre fenetre, int x, int y, Ecran ecran)
			throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran);
		texteFont = fenetre.getGraphics().getFont();
	}

	/**
	 * 
	 * @param fenetre
	 * @param x
	 * @param y
	 * @param ecran
	 * @param texte
	 * @throws SlickException
	 */
	public ToggleBouton(Fenetre fenetre, int x, int y, Ecran ecran, String texte)
			throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran, texte);
		texteFont = fenetre.getGraphics().getFont();
	}

	/**
	 * 
	 * @param fenetre
	 * @param x
	 * @param y
	 * @param ecran
	 * @param texte
	 * @param a
	 * @throws SlickException
	 */
	public ToggleBouton(Fenetre fenetre, int x, int y, Ecran ecran,
			String texte, Action a) throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran, texte, a);
		texteFont = fenetre.getGraphics().getFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.BoutonGroupable#deselectionner()
	 */
	@Override
	public void deselectionner() {
		super.deselectionner();
		setNormalImage(imageUp);
		setMouseOverImage(imageUpOver);
		setMouseDownImage(imageDown);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.BoutonGroupable#selectionner()
	 */
	@Override
	public void selectionner() {
		if (!selected) {
			setNormalImage(imageDown);
			setMouseOverImage(imageDown);
			setMouseDownImage(imageDown);
		}
		super.selectionner();
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
	 * Permet de savoir la largeur des boutons prochainement crees
	 * 
	 * @return la largeur des boutons
	 */
	public static int getButtonWidth() {
		return WIDTH;
	}

	/**
	 * Permet de savoir la hauter des boutons prochainement crees
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
		if (texteFont != null)
			g.setFont(texteFont);
		// dessin du texte
		g.drawString(texte, (float) (this.getX() + getWidth() / 2 - g.getFont()
				.getWidth(texte) / 2), this.getY() + getHeight() / 2
				- g.getFont().getHeight(texte) / 2);
	}

	// //////////// GETTER and SETTER ////////////

	/**
	 * @return the imageUpOver
	 */
	public Image getImageUpOver() {
		return imageUpOver;
	}

	/**
	 * @param imageUpOver
	 *            the imageUpOver to set
	 */
	public void setImageUpOver(Image imageUpOver) {
		this.imageUpOver = imageUpOver;
		if (!selected) {
			setMouseOverImage(imageUpOver);
		}

	}

	/**
	 * @return the imageDown
	 */
	public Image getImageDown() {
		return imageDown;
	}

	/**
	 * @param imageDown
	 *            the imageDown to set
	 */
	public void setImageDown(Image imageDown) {
		this.imageDown = imageDown;
		if (selected) {
			setNormalImage(imageDown);
		} else {
			setMouseDownImage(imageDown);
		}
	}

	/**
	 * @return the imageUp
	 */
	public Image getImageUp() {
		return imageUp;
	}

	/**
	 * @param imageUp
	 *            the imageUp to set
	 */
	public void setImageUp(Image imageUp) {
		this.imageUp = imageUp;
		if (selected) {
			setMouseDownImage(imageUp);
		} else {
			setNormalImage(imageUp);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.AbstractIHMComponent#actionMouseClicked(int, int, int, int)
	 */
	@Override
	public void actionMouseClicked(int button, int x, int y, int clickCount) {
		if (!selected) {
			selectionner();
			doAction();
		}
	}

}
