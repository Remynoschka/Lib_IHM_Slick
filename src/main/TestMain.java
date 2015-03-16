package main;

import org.newdawn.slick.SlickException;

import slickIHM.Fenetre;

public class TestMain {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Fenetre.newInstance(TestJeu.INSTANCE).start();

				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
