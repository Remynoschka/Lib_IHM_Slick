package slickIHM;

import java.util.List;

/**
 * Cette interface defini les methodes necessaires pour les classes permettant
 * de contenir des elements d'interface graphique (en dehors de la fenetre). Ces
 * elements doivent etre contenus sous forme de liste. Pensez egalement a faire
 * la methode de rendu <br/>
 * Attention, les elements sont places en coordonnees relatives a leur
 * conteneur lorsqu'ils sont ajoutes a celui-ci.
 * 
 * @author Remynoschka
 * 
 */
public interface IHMConteneur {

	/**
	 * Ajoute un composant au conteneur
	 * 
	 * @param element
	 *            : l'element a ajouter
	 */
	public void addComponent(IHMComponent element);

	/**
	 * Retire un composant au conteneur
	 * 
	 * @param element
	 */
	public void removeComponent(IHMComponent element);

	/**
	 * @return la liste des composants dans le conteneur
	 */
	public List<IHMComponent> getComponents();

	public int getX();

	public int getY();

	public int getWidth();

	public int getHeight();

}
