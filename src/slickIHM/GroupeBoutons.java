package slickIHM;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet d'assembler des BoutonGroupable au sein des meme groupe
 * afin qu'ils aient des comportements lies
 * 
 * @author Remynoschka
 * 
 */
public class GroupeBoutons {
	/**
	 * Les boutons du groupe
	 */
	private List<BoutonGroupable>	boutons;

	public GroupeBoutons() {
		boutons = new ArrayList<BoutonGroupable>();
	}

	public GroupeBoutons(List<BoutonGroupable> liste) {
		this.boutons = liste;
	}

	/**
	 * Ajoute un bouton a ce groupe de bouton
	 * 
	 * @param bouton
	 *            : le bouton a ajouter
	 */
	public void add(BoutonGroupable bouton) {
		this.boutons.add(bouton);
		bouton.setGroupe(this);
	}

	/**
	 * Permet d'obtenir les boutons du groupe
	 * 
	 * @return les boutons dans le groupe
	 */
	public List<BoutonGroupable> getBoutons() {
		return boutons;
	}

	/**
	 * Deselectionne tout les boutons sauf celui passe en parametre qui
	 * correspond a celui selectionne
	 * 
	 * @param selected
	 *            : le bouton selectionne
	 */
	public void deselectAll(BoutonGroupable selected) {
		for (BoutonGroupable bouton : boutons) {
			if (bouton != selected)
				bouton.deselectionner();
		}
	}
}
