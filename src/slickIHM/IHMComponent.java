/**
 * 
 */
package slickIHM;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * Classe generique pour les composants d'IHM. <br/>
 * Attention, si les classes heritants de cette classe veulent avoir un render
 * particulier ou des actions specifiques aux events, il faut heriter des
 * methodes doRender et actionXXX
 * 
 * @author Remynoschka
 * 
 */
public abstract class IHMComponent extends MouseOverArea {
	protected boolean		disabled	= false;
	protected boolean		visible		= true;
	protected Ecran			ecran;
	protected IHMConteneur	conteneur;

	/**
	 * @param fenetre
	 * @param image
	 * @param x
	 * @param y
	 * @param ecran
	 * @throws SlickException
	 */
	public IHMComponent(Fenetre fenetre, int x, int y, Ecran ecran)
			throws SlickException {
		super(fenetre, new Image(0, 0), x, y);
		this.ecran = ecran;
	}

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
	public IHMComponent(Fenetre fenetre, int x, int y, int w, int h, Ecran ecran)
			throws SlickException {
		super(fenetre, new Image(0, 0), x, y, w, h);
		this.ecran = ecran;
	}

	/**
	 * Defini si le composant est visible ou non
	 * 
	 * @param val
	 *            : true = visible
	 */
	public void setVisible(boolean val) {
		visible = val;
	}

	/**
	 * Active le composant
	 */
	public void enable() {
		disabled = false;
	}

	/**
	 * Desactive le composant
	 */
	public void disable() {
		disabled = true;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isEnabled() {
		return !disabled;
	}

	public Ecran getEcran() {
		return ecran;
	}

	public void setConteneur(IHMConteneur conteneur) {
		this.conteneur = conteneur;
		if (conteneur != null)
			this.setLocation(conteneur.getX() + this.getX(), conteneur.getY()
					+ this.getY());
	}

	public IHMConteneur getConteneur() {
		return conteneur;
	}

	@Override
	public void render(GUIContext fenetre, Graphics g) {
		if (visible)
			doRender(fenetre, g);
	}

	/**
	 * Methode appelle par la methode render d'IHMComponent
	 * 
	 * @param fenetre
	 * @param g
	 * @return
	 */
	public void doRender(GUIContext fenetre, Graphics g) {
		super.render(fenetre, g);
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (mouseInside(x, y))
			if (Fenetre.FENETRE.getVueActuelle() == this.ecran) {
				if (isEnabled()) {
					actionMouseClicked(button, x, y, clickCount);
				}
			}
	}

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
	public void actionMouseClicked(int button, int x, int y, int clickCount) {
		super.mouseClicked(button, x, y, clickCount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.gui.MouseOverArea#mouseDragged(int, int, int, int)
	 */
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		if (Fenetre.FENETRE.getVueActuelle() == this.ecran) {
			if (isEnabled()) {
				actionMouseDragged(oldx, 0, newx, newy);
			}
		}
	}

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
	public void actionMouseDragged(int oldx, int oldy, int newx, int newy) {
		super.mouseDragged(oldx, oldy, newx, newy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.gui.MouseOverArea#mouseMoved(int, int, int, int)
	 */
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		if (Fenetre.FENETRE.getVueActuelle() == this.ecran) {
			if (isEnabled()) {
				actionMouseMoved(oldx, oldy, newx, newy);
			}
		}
	}

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
	public void actionMouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.gui.MouseOverArea#mousePressed(int, int, int)
	 */
	@Override
	public void mousePressed(int button, int mx, int my) {
		if (mouseInside(mx, my))
			if (Fenetre.FENETRE.getVueActuelle() == this.ecran) {
				if (!disabled) {
					actionMousePressed(button, mx, my);
				}
			}
	}

	/**
	 * Action effectuee quand un bouton de la souris est appuye
	 * 
	 * @param button
	 * @param mx
	 * @param my
	 * @see org.newdawn.slick.util.InputAdapter#mousePressed(int button, int mx,
	 *      int my
	 */
	public void actionMousePressed(int button, int mx, int my) {
		super.mousePressed(button, mx, my);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.gui.MouseOverArea#mouseReleased(int, int, int)
	 */
	@Override
	public void mouseReleased(int button, int mx, int my) {
		if (mouseInside(mx, my))
			if (Fenetre.FENETRE.getVueActuelle() == this.ecran) {
				if (!disabled) {
					actionMouseReleased(button, mx, my);
				}
			}
	}

	/**
	 * Action effectuee quand un bouton de la souris est relache
	 * 
	 * @param button
	 * @param mx
	 * @param my
	 * @see org.newdawn.slick.util.InputAdapter#mouseReleased(int button, int
	 *      mx, int my
	 */
	public void actionMouseReleased(int button, int mx, int my) {
		super.mouseReleased(button, mx, my);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.util.InputAdapter#mouseWheelMoved(int)
	 */
	@Override
	public void mouseWheelMoved(int change) {
		if (Fenetre.FENETRE.getVueActuelle() == this.ecran) {
			if (!disabled) {
				actionMouseWheelMoved(change);
			}
		}
	}

	/**
	 * Action effectuee quand la molette de la souris change d'etat
	 * 
	 * @param change
	 * @see org.newdawn.slick.util.InputAdapter#mouseWheelMoved(int)
	 */
	public void actionMouseWheelMoved(int change) {
		super.mouseWheelMoved(change);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.util.InputAdapter#keyPressed(int, char)
	 */
	@Override
	public void keyPressed(int key, char c) {
		if (Fenetre.FENETRE.getVueActuelle() == this.ecran) {
			if (!disabled) {
				actionKeyPressed(key, c);
			}
		}
	}

	/**
	 * Action effectuee quand une touche est pressee
	 * 
	 * @param key
	 * @param c
	 * @see org.newdawn.slick.util.InputAdapter#keyPressed(int, char)
	 */
	public void actionKeyPressed(int key, char c) {
		super.keyPressed(key, c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.util.InputAdapter#keyReleased(int, char)
	 */
	@Override
	public void keyReleased(int key, char c) {
		if (Fenetre.FENETRE.getVueActuelle() == this.ecran) {
			if (!disabled) {
				actionKeyReleased(key, c);
			}
		}
	}

	/**
	 * Action effectuee quand une touche est relachee
	 * 
	 * @param key
	 * @param c
	 * @see org.newdawn.slick.util.InputAdapter#keyReleased(int, char)
	 */
	public void actionKeyReleased(int key, char c) {
		super.keyReleased(key, c);
	}

	/**
	 * Test si la souris survole l'IHMComponent
	 * 
	 * @param mouseX
	 *            : position X de la souris
	 * @param mouseY
	 *            : position Y de la souris
	 * @return
	 */
	public boolean mouseInside(int mouseX, int mouseY) {
		return (mouseX > this.getX() && mouseX < this.getX() + getWidth()
				&& mouseY > this.getY() && mouseY < this.getY()
				+ this.getHeight());
	}
}
