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
public abstract class AbstractIHMComponent extends MouseOverArea implements
		IHMComponent {
	/**
	 * Indique si le composant est active
	 */
	protected boolean		disabled					= false;
	/**
	 * Indique si le composant est visible
	 */
	protected boolean		visible						= true;
	/**
	 * L'ecran dans lequel est le composant
	 */
	protected Ecran			ecran;
	/**
	 * Le conteneur de ce composant
	 */
	protected IHMConteneur	conteneur;
	/**
	 * Indique si le composant peut etre active meme si un autre composant a le
	 * focus
	 */
	protected boolean		activeWhenSomeoneHasFocus	= false;

	/**
	 * @param fenetre
	 *            : La fenetre du jeu
	 * @param x
	 *            : coordonnee X
	 * @param y
	 *            : coordonnee Y
	 * @param ecran
	 *            : Ecran sur lequel sera affiche ce composant
	 * @throws SlickException
	 */
	public AbstractIHMComponent(Fenetre fenetre, int x, int y, Ecran ecran)
			throws SlickException {
		super(fenetre, new Image(0, 0), x, y);
		this.ecran = ecran;
	}

	/**
	 * @param fenetre
	 *            : La fenetre du jeu
	 * @param x
	 *            : coordonnee X
	 * @param y
	 *            : coordonnee Y
	 * @param w
	 *            : largeur du composant
	 * @param h
	 *            : hauteur du composant
	 * 
	 * @param ecran
	 *            : Ecran sur lequel sera affiche ce composant
	 * @throws SlickException
	 */
	public AbstractIHMComponent(Fenetre fenetre, int x, int y, int w, int h,
			Ecran ecran) throws SlickException {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#enable()
	 */
	@Override
	public void enable() {
		disabled = false;
	}

	@Override
	public void disable() {
		disabled = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#isVisible()
	 */
	@Override
	public boolean isVisible() {
		return visible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return !disabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#getEcran()
	 */
	@Override
	public Ecran getEcran() {
		return ecran;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#setConteneur(slickIHM.IHMConteneur)
	 */
	@Override
	public void setConteneur(IHMConteneur conteneur) {
		this.conteneur = conteneur;
		if (conteneur != null)
			this.setLocation(conteneur.getX() + this.getX(), conteneur.getY()
					+ this.getY());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#getConteneur()
	 */
	@Override
	public IHMConteneur getConteneur() {
		return conteneur;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.newdawn.slick.gui.MouseOverArea#render(org.newdawn.slick.gui.GUIContext
	 * , org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GUIContext fenetre, Graphics g) {
		if (visible)
			doRender(fenetre, g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#doRender(org.newdawn.slick.gui.GUIContext,
	 * org.newdawn.slick.Graphics)
	 */
	@Override
	public void doRender(GUIContext fenetre, Graphics g) {
		super.render(fenetre, g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.util.InputAdapter#mouseClicked(int, int, int, int)
	 */
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (mouseInside(x, y))
			if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
				if (isEnabled()) {
					actionMouseClicked(button, x, y, clickCount);
				}
			}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#actionMouseClicked(int, int, int, int)
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
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (isEnabled()) {
				if (!this.ecran.someoneHasFocus() || activeWhenSomeoneHasFocus) {
					actionMouseDragged(oldx, 0, newx, newy);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#actionMouseDragged(int, int, int, int)
	 */
	@Override
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
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (isEnabled()) {
				actionMouseMoved(oldx, oldy, newx, newy);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#actionMouseMoved(int, int, int, int)
	 */
	@Override
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
			if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
				if (!disabled) {
					if (!this.ecran.someoneHasFocus()
							|| activeWhenSomeoneHasFocus || hasFocus()) {
						actionMousePressed(button, mx, my);
					}
				}
			}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#actionMousePressed(int, int, int)
	 */
	@Override
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
			if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
				if (!disabled) {
					if (!this.ecran.someoneHasFocus()
							|| activeWhenSomeoneHasFocus || hasFocus()) {
						actionMouseReleased(button, mx, my);
					}
				}
			}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#actionMouseReleased(int, int, int)
	 */
	@Override
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
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (!disabled) {
				if (!this.ecran.someoneHasFocus() || activeWhenSomeoneHasFocus
						|| hasFocus()) {
					actionMouseWheelMoved(change);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#actionMouseWheelMoved(int)
	 */
	@Override
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
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (!disabled) {
				if (!this.ecran.someoneHasFocus() || activeWhenSomeoneHasFocus
						|| hasFocus()) {
					actionKeyPressed(key, c);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#actionKeyPressed(int, char)
	 */
	@Override
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
		if (Fenetre.getInstance().getVueActuelle() == this.ecran) {
			if (!disabled) {
				if (!this.ecran.someoneHasFocus() || activeWhenSomeoneHasFocus
						|| hasFocus()) {
					actionKeyReleased(key, c);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#actionKeyReleased(int, char)
	 */
	@Override
	public void actionKeyReleased(int key, char c) {
		super.keyReleased(key, c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMComponent#mouseInside(int, int)
	 */
	@Override
	public boolean mouseInside(int mouseX, int mouseY) {
		return (mouseX > this.getX() && mouseX < this.getX() + getWidth()
				&& mouseY > this.getY() && mouseY < this.getY()
				+ this.getHeight());
	}

	/**
	 * Permet de savoir si ce composant est actif quand un autre a le focus
	 * 
	 * @return true s'il peut etre actif
	 */
	public boolean isActiveWhenSomeoneHasFocus() {
		return activeWhenSomeoneHasFocus;
	}

	/**
	 * Rends actif le composant meme si un autre a le focus
	 * 
	 * @param val
	 */
	public void setActiveWhenSomeoneHasFocus(boolean val) {
		this.activeWhenSomeoneHasFocus = val;
	}
}
