/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slickIHM;

import main.Jeu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;

/**
 * La fenetre du jeu
 * 
 * @author Remynoschka
 */
public class Fenetre extends AppGameContainer {
	public static Fenetre				FENETRE;
	public static final ConfigMonitors	config	= ConfigMonitors
														.getConfiguration();

	/**
	 * Creates new form Fenetre
	 */
	private Fenetre(Game game, int width, int height) throws SlickException {
		super(game, width, height, true);

	}

	/**
	 * 
	 * @return l'Ecran actuellement visible
	 */
	public GameState getVueActuelle() {
		return Jeu.INSTANCE.getCurrentState();
	}

	/**
	 * Change la vue affichee dans la fenetre
	 * 
	 * @param idState
	 *            : l'id d'un GameState representant cette vue
	 * @return l'id de l'etat que l'on quitte
	 */
	public int changerVueActuelle(int idState) {
		int leave = Jeu.INSTANCE.getCurrentStateID();
		Jeu.INSTANCE.enterState(idState);
		return leave;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetre.FENETRE = new Fenetre(Jeu.INSTANCE, ConfigMonitors
							.getGraphicsDevice().getDisplayMode().getWidth(),
							ConfigMonitors.getGraphicsDevice().getDisplayMode()
									.getHeight());

					FENETRE.start();

				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		});
	}
	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
