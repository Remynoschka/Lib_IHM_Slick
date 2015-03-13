package slickIHM;

import java.util.ArrayList;
import java.util.List;

import main.Main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

/**
 * Cette classe permet de creer une popup au sein de l'ecran
 * 
 * @author Remynoschka
 * 
 */
public abstract class Popup extends IHMComponent implements IHMConteneur {
	protected List<IHMComponent>	composants;

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
	public Popup(Fenetre fenetre, int x, int y, int w, int h, Ecran ecran)
			throws SlickException {
		super(fenetre, x, y, w, h, ecran);
		composants = new ArrayList<>();
		setVisible(false);
		disable();
	}

	@Override
	public void addComponent(IHMComponent element) {
		composants.add(element);
		element.setConteneur(this);		
	}

	@Override
	public void removeComponent(IHMComponent element) {
		composants.remove(element);
		element.setConteneur(null);
	}

	@Override
	public List<IHMComponent> getComponents() {
		return composants;
	}

	@Override
	public void doRender(GUIContext fenetre, Graphics g) {
		super.doRender(fenetre, g);
		for (IHMComponent c : composants) {
			c.render(container, g);
		}
	}

	@Override
	public void actionKeyPressed(int key, char c) {
		super.actionKeyPressed(key, c);
	}

	@Override
	public void actionKeyReleased(int key, char c) {
		super.actionKeyReleased(key, c);
	}

	@Override
	public void actionMouseClicked(int button, int x, int y, int clickCount) {
		if (isEnabled())
			if (isMouseOver())
				super.actionMouseClicked(button, x, y, clickCount);
			else if (Main.DEBUG)
				System.out.println("Clic out of Popup");
	}

	@Override
	public void actionMousePressed(int button, int mx, int my) {
		if (isEnabled())
			if (isMouseOver())
				super.actionMousePressed(button, mx, my);
			else if (Main.DEBUG)
				System.out.println("Pressed out of Popup");
	}

	@Override
	public void actionMouseReleased(int button, int mx, int my) {
		if (isEnabled())
			if (isMouseOver())
				super.actionMouseReleased(button, mx, my);
			else if (Main.DEBUG)
				System.out.println("Released out of Popup");
	}

	/**
	 * ferme la popup
	 */
	public void close() {
		if (Main.DEBUG)
			System.out.println("Close Popup");
		this.disable();
		this.setVisible(false);
	}

	/**
	 * montre la popup
	 * 
	 * @param ecran
	 *            : l'ecran dans lequel est affichee la popup
	 */
	public void show(Ecran ecran) {
		this.disable();
		if (Main.DEBUG)
			System.out.println("Show Popup");
		this.setVisible(true);
		this.enable();
	}

	@Override
	public void disable() {
		super.disable();
		for (IHMComponent fils : composants) {
			fils.disable();
		}
	}

	@Override
	public void enable() {
		super.enable();
		for (IHMComponent fils : composants) {
			fils.enable();
		}
	}

}
