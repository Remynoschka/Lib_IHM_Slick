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
	private static int	WIDTH	= 25;
	private static int	HEIGHT	= 25;
	protected Image		normalImg;
	protected Image		overImg;
	protected Image		checkedImg;
	protected Image		checkedOverImg;
	protected Image		clickedImg;
	protected Image		checkedClickedImg;

	public Checkbox(Fenetre fenetre, int x, int y, Ecran ecran)
			throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran);
	}

	public Checkbox(Fenetre fenetre, int x, int y, Ecran ecran, String texte)
			throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran, texte);
	}

	public Checkbox(Fenetre fenetre, int x, int y, Ecran ecran, String texte,
			Action a) throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran, texte, a);
	}

	public Checkbox(Fenetre fenetre, int x, int y, Ecran ecran, String texte,
			Action a, int txtPosition) throws SlickException {
		super(fenetre, x, y, WIDTH, HEIGHT, ecran, texte, a, txtPosition);
	}

	public Image getNormalImg() {
		return normalImg;
	}

	public void setNormalImg(Image normalImg) {
		this.normalImg = normalImg;
		if (!selected)
			setNormalImage(normalImg);
	}

	public Image getOverImg() {
		return overImg;
	}

	public void setOverImg(Image overImg) {
		this.overImg = overImg;
		if (!selected)
			setMouseOverImage(overImg);
	}

	public Image getCheckedImg() {
		return checkedImg;
	}

	public void setCheckedImg(Image checkedImg) {
		this.checkedImg = checkedImg;
		if (selected)
			setNormalImage(checkedImg);
	}

	public Image getCheckedOverImg() {
		return checkedOverImg;
	}

	public void setCheckedOverImg(Image checkedOverImg) {
		this.checkedOverImg = checkedOverImg;
		if (selected)
			setMouseOverImage(checkedOverImg);
	}

	public Image getClickedImg() {
		return clickedImg;
	}

	public void setClickedImg(Image clickedImg) {
		this.clickedImg = clickedImg;
		if (!selected)
			setMouseDownImage(clickedImg);
	}

	public Image getCheckedClickedImg() {
		return checkedClickedImg;
	}

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
