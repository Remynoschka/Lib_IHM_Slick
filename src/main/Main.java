package main;

import org.newdawn.slick.SlickException;

import slickIHM.Fenetre;

public class Main {
	public static final boolean	DEBUG	= false;

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Fenetre.getInstance().start();

				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
