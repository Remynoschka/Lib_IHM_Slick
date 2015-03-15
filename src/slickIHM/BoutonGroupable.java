package slickIHM;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;

/**
 * Cette classe definit les methodes utilisees pour faire des boutons groupables
 * dans des GroupeBoutons
 * 
 * @author Remynoschka
 * 
 */
public abstract class BoutonGroupable extends AbstractIHMComponent {
	/**
	 * Le bouton est il selectionne
	 */
	protected boolean		selected;
	/**
	 * Le groupe auquel appartien le bouton
	 */
	protected GroupeBoutons	groupe;
	/**
	 * L'action effectue a l'appui sur le bouton
	 */
	protected Action		action;
	/**
	 * Le texte sur ce bouton
	 */
	protected String		texte		= "";
	/**
	 * La couleur du texte
	 */
	protected Color			texteColor	= Color.black;
	/**
	 * La police du texte
	 */
	protected Font			texteFont;

	/**
	 * 
	 * @param fenetre
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param ecran
	 * @throws SlickException
	 */
	public BoutonGroupable(Fenetre fenetre, int x, int y, int w, int h,
			Ecran ecran) throws SlickException {
		super(fenetre, x, y, w, h, ecran);
	}

	/**
	 * 
	 * @param fenetre
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param ecran
	 * @param texte
	 * @throws SlickException
	 */
	public BoutonGroupable(Fenetre fenetre, int x, int y, int w, int h,
			Ecran ecran, String texte) throws SlickException {
		super(fenetre, x, y, w, h, ecran);
		this.texte = texte;
	}

	/**
	 * 
	 * @param fenetre
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param ecran
	 * @param texte
	 * @param a
	 * @throws SlickException
	 */
	public BoutonGroupable(Fenetre fenetre, int x, int y, int w, int h,
			Ecran ecran, String texte, Action a) throws SlickException {
		super(fenetre, x, y, w, h, ecran);
		this.texte = texte;
		this.action = a;
	}

	/**
	 * Selectionne le bouton parmi le groupe de bouton. Ceci aura pour effet de
	 * deselectionner les autres boutons du groupe.
	 */
	public void selectionner() {
		if (!selected) {
			selected = true;
			if (groupe != null) {
				groupe.deselectAll(this);
			}
		} else if (groupe == null) {
			deselectionner();
		}
		if (groupe != null) {
			groupe.deselectAll(this);
		}
	}

	/**
	 * Deselectionne se bouton parmi son groupe.
	 */
	public void deselectionner() {
		selected = false;
	}

	/**
	 * Permet de savoir si le bouton est selectionne
	 * 
	 * @return true si le bouton est selectionne
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Definit le groupe de boutons pour ce bouton
	 * 
	 * @param groupe
	 */
	public void setGroupe(GroupeBoutons groupe) {
		this.groupe = groupe;
	}

	/**
	 * Renvoi le groupe de boutons auquel appartient ce bouton
	 * 
	 * @return le groupe de boutons auquel appartient ce bouton
	 */
	public GroupeBoutons getGroupe() {
		return groupe;
	}

	/**
	 * Definit l'action pour ce bouton
	 * 
	 * @param a
	 *            : l'action effectue lorsque ce bouton est utilise
	 */
	public void setAction(Action a) {
		this.action = a;
	}

	/**
	 * Effectue l'action du bouton
	 */
	public void doAction() {
		if (action != null)
			action.actionPerformed();
		else
			System.err.println("Aucune action définie");

	}

	/**
	 * Renvoi l'action associe au bouton
	 * 
	 * @return l'action associe au bouton
	 */
	public Action getAction() {
		return action;
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
}
