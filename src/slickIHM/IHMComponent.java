package slickIHM;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;

/**
 * Cette interface definit les composants. Ceux-ci peuvent etre mis dans des
 * IHMConteneur. Elle definit des methodes permettant de mieux controller les
 * interactions souris/clavier pour chaque ecran ainsi que la methode render.
 * 
 * @author Remynoschka
 * 
 */
public interface IHMComponent {

	/**
	 * Defini si le composant est visible ou non
	 * 
	 * @param val
	 *            : true = visible
	 */
	public void setVisible(boolean val);

	/**
	 * Active le composant
	 */
	public void enable();

	/**
	 * Desactive le composant
	 */
	public void disable();

	/**
	 * Indique si le composant est visible
	 * 
	 * @return true si le composant est visible
	 */
	public boolean isVisible();

	/**
	 * Indique si le composant est active
	 * 
	 * @return true si le composant est active
	 */
	public boolean isEnabled();

	/**
	 * Permet de savoir sur quel ecran est place ce composant
	 * 
	 * @return l'ecran sur lequel est ce composant
	 */
	public Ecran getEcran();

	/**
	 * Definit un conteneur pour le composant en question
	 * 
	 * @param conteneur
	 *            : l'IHMConteneur qui contiendra le composant en question
	 */
	public void setConteneur(IHMConteneur conteneur);

	/**
	 * Permet d'obtenir le conteneur de ce composant
	 * 
	 * @return le conteneur du composant
	 */
	public IHMConteneur getConteneur();

	/**
	 * Methode pour savoir si le composant a le focus. Si celui-ci le possede,
	 * cela peut bloquer certaines interactions avec d'autres composants.
	 * 
	 * @return true si le composant a le focus
	 */
	public boolean hasFocus();

	/**
	 * Methode appelle par la methode render d'IHMComponent
	 * 
	 * @param fenetre
	 * @param g
	 */
	public void doRender(GUIContext fenetre, Graphics g);

	public void render(GUIContext fenetre, Graphics g);

	/**
	 * Action effectuee quand un clic de souris a lieu
	 * 
	 * @param button
	 * @param x
	 * @param y
	 * @param clickCount
	 * @see org.newdawn.slick.util.InputAdapter#mouseClicked(int button, int x,
	 *      int y, int clickCount)
	 */
	public void actionMouseClicked(int button, int x, int y, int clickCount);

	/**
	 * Action effectuee quand un drag de souris a lieu
	 * 
	 * @param oldx
	 * @param oldy
	 * @param newx
	 * @param newy
	 * @see org.newdawn.slick.util.InputAdapter#mouseDragged(int oldx, int oldy,
	 *      int newx, int newy)
	 */
	public void actionMouseDragged(int oldx, int oldy, int newx, int newy);

	/**
	 * Action effectuee quand un mouvement de souris a lieu
	 * 
	 * @param oldx
	 * @param oldy
	 * @param newx
	 * @param newy
	 * @see org.newdawn.slick.util.InputAdapter#mouseMoved(int oldx, int oldy,
	 *      int newx, int newy)
	 */
	public void actionMouseMoved(int oldx, int oldy, int newx, int newy);

	/**
	 * Action effectuee quand un bouton de la souris est appuye
	 * 
	 * @param button
	 * @param mx
	 * @param my
	 * @see org.newdawn.slick.util.InputAdapter#mousePressed(int button, int mx,
	 *      int my
	 */
	public void actionMousePressed(int button, int mx, int my);

	/**
	 * Action effectuee quand un bouton de la souris est relache
	 * 
	 * @param button
	 * @param mx
	 * @param my
	 * @see org.newdawn.slick.util.InputAdapter#mouseReleased(int button, int
	 *      mx, int my
	 */
	public void actionMouseReleased(int button, int mx, int my);

	/**
	 * Action effectuee quand la molette de la souris change d'etat
	 * 
	 * @param change
	 * @see org.newdawn.slick.util.InputAdapter#mouseWheelMoved(int)
	 */
	public void actionMouseWheelMoved(int change);

	/**
	 * Action effectuee quand une touche est pressee
	 * 
	 * @param key
	 * @param c
	 * @see org.newdawn.slick.util.InputAdapter#keyPressed(int, char)
	 */
	public void actionKeyPressed(int key, char c);

	/**
	 * Action effectuee quand une touche est relachee
	 * 
	 * @param key
	 * @param c
	 * @see org.newdawn.slick.util.InputAdapter#keyReleased(int, char)
	 */
	public void actionKeyReleased(int key, char c);

	/**
	 * Test si la souris survole l'IHMComponent
	 * 
	 * @param mouseX
	 *            : position X de la souris
	 * @param mouseY
	 *            : position Y de la souris
	 * @return
	 */
	public boolean mouseInside(int mouseX, int mouseY);

}
