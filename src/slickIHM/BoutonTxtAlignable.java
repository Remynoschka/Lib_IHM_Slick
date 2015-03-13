package slickIHM;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

/**
 * Classe abstraite definissant des boutons ou l'on peut placer le texte a un
 * endroit autour du bouton. Il est conseille d'utiliser les constantes de cette
 * classe pour positioner le texte
 * 
 * @author Remynoschka
 * 
 */
public abstract class BoutonTxtAlignable extends BoutonGroupable {

	public static final int	TXT_POS_LEFT	= 0;
	public static final int	TXT_POS_UP		= 1;
	public static final int	TXT_POS_DOWN	= 2;
	public static final int	TXT_POS_RIGHT	= 3;

	protected int			txtPosition;
	protected int			spacing			= 10;

	public int getSpacing() {
		return spacing;
	}

	/**
	 * Definit l'espace entre le bouton et le texte
	 * 
	 * @param spacing
	 *            : l'espace en pixels
	 */
	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	public BoutonTxtAlignable(Fenetre fenetre, int x, int y, int w, int h,
			Ecran ecran) throws SlickException {
		super(fenetre, x, y, w, h, ecran);
	}

	public BoutonTxtAlignable(Fenetre fenetre, int x, int y, int w, int h,
			Ecran ecran, String texte) throws SlickException {
		super(fenetre, x, y, w, h, ecran, texte);
	}

	public BoutonTxtAlignable(Fenetre fenetre, int x, int y, int w, int h,
			Ecran ecran, String texte, Action a) throws SlickException {
		super(fenetre, x, y, w, h, ecran, texte, a);
	}

	public BoutonTxtAlignable(Fenetre fenetre, int x, int y, int w, int h,
			Ecran ecran, String texte, Action a, int txtPosition)
			throws SlickException {
		super(fenetre, x, y, w, h, ecran, texte, a);
		this.txtPosition = txtPosition;
	}

	/**
	 * Definit la position du texte par rapport au bouton.
	 * 
	 * @param txtPosition
	 *            : la position du texte
	 */
	public void setTxtPosition(int txtPosition) {
		this.txtPosition = txtPosition;
	}

	@Override
	public void doRender(GUIContext fenetre, Graphics g) {
		super.doRender(fenetre, g);
		if (isEnabled())
			g.setColor(new Color(36, 36, 36));
		else
			g.setColor(texteColor);
		if (texteFont != null)
			g.setFont(texteFont);
		switch (txtPosition) {
			case TXT_POS_DOWN:
				g.drawString(texte, (float) (this.getX() + getWidth() / 2 - g
						.getFont().getWidth(texte) / 2), this.getY()
						+ getHeight() + spacing);
				break;
			case TXT_POS_UP:
				g.drawString(texte, (float) (this.getX() + getWidth() / 2 - g
						.getFont().getWidth(texte) / 2), this.getY() - spacing - g.getFont().getHeight(texte));
				break;
			case TXT_POS_LEFT:
				g.drawString(texte, (float) (this.getX()
						- g.getFont().getWidth(texte) - spacing), this.getY()
						+ getHeight() / 2 - g.getFont().getHeight(texte) / 2);
				break;
			case TXT_POS_RIGHT:
				g.drawString(
						texte,
						(float) (this.getX() + getWidth() + spacing),
						this.getY() + getHeight() / 2
								- g.getFont().getHeight(texte) / 2);
				break;
		}

	}
	@Override
	public void actionMouseClicked(int button, int x, int y, int clickCount) {
		selectionner();
	}
}
