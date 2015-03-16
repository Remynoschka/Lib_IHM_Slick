/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slickIHM;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
		return (Ecran) ((StateBasedGame) this.game).getCurrentState();
	}

	/**
	 * Change la vue affichee dans la fenetre
	 * 
	 * @param idState
	 *            : l'id de l'Ecran representant cette vue
	 * @return l'id de l'etat que l'on quitte
	 */
	public int changerVueActuelle(int idState) {
		int leave = ((StateBasedGame) this.game).getCurrentStateID();
		((StateBasedGame) this.game).enterState(idState);
		return leave;
	}

	/**
	 * Permet d'avoir la fenetre du jeu.
	 * 
	 * @return la fenetre du jeu, null si aucune n'a ete cree
	 * @throws SlickException
	 */
	public static Fenetre getInstance() {
		return INSTANCE;
	}

	/**
	 * Permet d'avoir la fenetre du jeu. En cree une si aucune n'existe
	 * 
	 * @param jeu
	 *            la fenetre du jeu
	 * @return
	 */
	public static Fenetre newInstance(StateBasedGame jeu) {
		if (INSTANCE == null) {
			try {
				INSTANCE = new Fenetre(jeu, ConfigMonitors.getGraphicsDevice()
						.getDisplayMode().getWidth(), ConfigMonitors
						.getGraphicsDevice().getDisplayMode().getHeight());
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return INSTANCE;
	}

}
