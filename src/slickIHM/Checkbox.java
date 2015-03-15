package slickIHM;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Classe permettant de faire des cases a cocher.
 * 
 * @author Remynoschka
 * 
 */
public class Checkbox extends BoutonTxtAlignable {
	/**
	 * Largeur de la case
	 */
	private static int	WIDTH	= 25;
	/**
	 * Hauteur de la case
	 */
	private static int	HEIGHT	= 25;
	/**
	 * Image quand la case est non coche
	 */
	protected Image		normalImg;
	/**
	 * Image quand la case est non coche et survolee
	 */
	protected Image		overImg;
	/**
	 * Image quand la case est coche
	 */
	protected Image		checkedImg;
	/**
	 * Image quand la case est coche et survolee
	 */
	protected Image		checkedOverImg;
	/**
	 * Image quand la case est non coche et cliquee
	 */
	protected Image		clickedImg;
	/**
	 * Image quand la case est coche et cliquee
	 */
	protected Image		checkedClickedImg;

	/**
	 * 
	 * @param fenetre
	 * @param x
	 * @param y
	 * @param ecran
	 * @throws SlickException
	 */
	public Checkbox(Fenetre fenetre, int x, int y, Ecran ecran)
			throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran);
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
	public Checkbox(Fenetre fenetre, int x, int y, Ecran ecran, String texte)
			throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran, texte);
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
	public Checkbox(Fenetre fenetre, int x, int y, Ecran ecran, String texte,
			Action a) throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran, texte, a);
	}

	/**
	 * 
	 * @param fenetre
	 * @param x
	 * @param y
	 * @param ecran
	 * @param texte
	 * @param a
	 * @param txtPosition
	 * @throws SlickException
	 */
	public Checkbox(Fenetre fenetre, int x, int y, Ecran ecran, String texte,
			Action a, int txtPosition) throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran, texte, a, txtPosition);
	}

	/**
	 * 
	 * @return l'image quand la case est non coche
	 */
	public Image getNormalImg() {
		return normalImg;
	}

	/**
	 * 
	 * @param normalImg
	 */
	public void setNormalImg(Image normalImg) {
		this.normalImg = normalImg;
		if (!selected)
			setNormalImage(normalImg);
	}

	/**
	 * 
	 * @return
	 */
	public Image getOverImg() {
		return overImg;
	}

	/**
	 * 
	 * @param overImg
	 */
	public void setOverImg(Image overImg) {
		this.overImg = overImg;
		if (!selected)
			setMouseOverImage(overImg);
	}

	/**
	 * 
	 * @return
	 */
	public Image getCheckedImg() {
		return checkedImg;
	}

	/**
	 * 
	 * @param checkedImg
	 */
	public void setCheckedImg(Image checkedImg) {
		this.checkedImg = checkedImg;
		if (selected)
			setNormalImage(checkedImg);
	}

	/**
	 * 
	 * @return
	 */
	public Image getCheckedOverImg() {
		return checkedOverImg;
	}

	/**
	 * 
	 * @param checkedOverImg
	 */
	public void setCheckedOverImg(Image checkedOverImg) {
		this.checkedOverImg = checkedOverImg;
		if (selected)
			setMouseOverImage(checkedOverImg);
	}

	/**
	 * 
	 * @return
	 */
	public Image getClickedImg() {
		return clickedImg;
	}

	/**
	 * 
	 * @param clickedImg
	 */
	public void setClickedImg(Image clickedImg) {
		this.clickedImg = clickedImg;
		if (!selected)
			setMouseDownImage(clickedImg);
	}

	/**
	 * 
	 * @return
	 */
	public Image getCheckedClickedImg() {
		return checkedClickedImg;
	}

	/**
	 * 
	 * @param checkedClickedImg
	 */
	public void setCheckedClickedImg(Image checkedClickedImg) {
		this.checkedClickedImg = checkedClickedImg;
		if (selected)
			setMouseDownImage(checkedClickedImg);
	}

	/**
	 * Modifie la taille des checkbox prochainement crees
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

	/*
	 * (non-Javadoc)
	 * @see slickIHM.BoutonGroupable#selectionner()
	 */
	@Override
	public void selectionner() {
		if (!selected) {
			System.out.println("selectionne");
			setNormalImage(checkedImg);
			setMouseOverImage(checkedOverImg);
			setMouseDownImage(checkedClickedImg);
		}
		super.selectionner();
	}

	/*
	 * (non-Javadoc)
	 * @see slickIHM.BoutonGroupable#deselectionner()
	 */
	@Override
	public void deselectionner() {
		if (selected) {
			System.out.println("deselectionne");
			setNormalImage(normalImg);
			setMouseOverImage(overImg);
			setMouseDownImage(clickedImg);
		}
		super.deselectionner();
	}

}
