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
public abstract class BoutonGroupable extends IHMComponent {
	protected boolean		selected;
	protected GroupeBoutons	groupe;
	protected Action		action;
	protected String		texte		= "";
	protected boolean		pressed;
	protected Color			texteColor	= Color.black;
	protected Font			texteFont;

	public BoutonGroupable(Fenetre fenetre, int x, int y, int w, int h,
			Ecran ecran) throws SlickException {
		super(fenetre, x, y, w, h, ecran);
	}

	public BoutonGroupable(Fenetre fenetre, int x, int y, int w, int h,
			Ecran ecran, String texte) throws SlickException {
		super(fenetre, x, y, w, h, ecran);
		this.texte = texte;
	}

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
