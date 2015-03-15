/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slickIHM;

import main.Jeu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

/**
 * La fenetre du jeu
 * 
 * @author Remynoschka
 */
public class Fenetre extends AppGameContainer {
	/**
	 * Variable globale contenant la fenetre du jeu
	 */
	private static Fenetre				INSTANCE;
	/**
	 * Les informations sur le(s) moniteur(s)
	 */
	public static final ConfigMonitors	config	= ConfigMonitors
														.getConfiguration();

	/**
	 * Cree une nouvelle fenetre
	 * 
	 * @param game
	 * @param width
	 * @param height
	 * @throws SlickException
	 */
	private Fenetre(Game game, int width, int height) throws SlickException {
		super(game, width, height, true);

	}

	/**
	 * Permet d'avoir l'ecran actuellement affiche
	 * 
	 * @return l'Ecran actuellement visible
	 */
	public Ecran getVueActuelle() {
		return (Ecran) Jeu.INSTANCE.getCurrentState();
	}

	/**
	 * Change la vue affichee dans la fenetre
	 * 
	 * @param idState
	 *            : l'id de l'Ecran representant cette vue
	 * @return l'id de l'etat que l'on quitte
	 */
	public int changerVueActuelle(int idState) {
		int leave = Jeu.INSTANCE.getCurrentStateID();
		Jeu.INSTANCE.enterState(idState);
		return leave;
	}

	/**
	 * Permet d'avoir la fenetre du jeu. En cree une s'il n'y en a pas
	 * 
	 * @return la fenetre du jeu
	 * @throws SlickException
	 */
	public static Fenetre getInstance(){
		if (INSTANCE == null) {
			try {
				INSTANCE = new Fenetre(Jeu.INSTANCE, ConfigMonitors
						.getGraphicsDevice().getDisplayMode().getWidth(),
						ConfigMonitors.getGraphicsDevice().getDisplayMode()
								.getHeight());
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return INSTANCE;
	}

}
