package slickIHM;

import java.util.ArrayList;
import java.util.List;

import main.Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

/**
 * Cette classe permet de creer une popup au sein de l'ecran. Par défaut,
 * celles-ci dessinent un fond noir pour eviter la transparence avec d'autres
 * elements. Cependant, pour vos usage, je vous conseille de redefinir la
 * methode doRender() (tout en gardant un appel a super.doRender() pour tracer
 * les elements)
 * 
 * @author Remynoschka
 * 
 */
public abstract class Popup extends AbstractIHMComponent implements
		IHMConteneur {
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
		setNormalColor(Color.black);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMConteneur#addComponent(slickIHM.IHMComponent)
	 */
	@Override
	public void addComponent(IHMComponent element) {
		composants.add(element);
		element.setConteneur(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMConteneur#removeComponent(slickIHM.IHMComponent)
	 */
	@Override
	public void removeComponent(IHMComponent element) {
		composants.remove(element);
		element.setConteneur(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMConteneur#getComponents()
	 */
	@Override
	public List<IHMComponent> getComponents() {
		return composants;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * slickIHM.AbstractIHMComponent#doRender(org.newdawn.slick.gui.GUIContext,
	 * org.newdawn.slick.Graphics)
	 */
	@Override
	public void doRender(GUIContext fenetre, Graphics g) {
		super.doRender(fenetre, g);
		for (IHMComponent c : composants) {
			c.render(container, g);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.AbstractIHMComponent#actionKeyPressed(int, char)
	 */
	@Override
	public void actionKeyPressed(int key, char c) {
		super.actionKeyPressed(key, c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.AbstractIHMComponent#actionKeyReleased(int, char)
	 */
	@Override
	public void actionKeyReleased(int key, char c) {
		super.actionKeyReleased(key, c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.AbstractIHMComponent#actionMouseClicked(int, int, int, int)
	 */
	@Override
	public void actionMouseClicked(int button, int x, int y, int clickCount) {
		if (isEnabled())
			if (isMouseOver())
				super.actionMouseClicked(button, x, y, clickCount);
			else if (Main.DEBUG)
				System.out.println("Clic out of Popup");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.AbstractIHMComponent#actionMousePressed(int, int, int)
	 */
	@Override
	public void actionMousePressed(int button, int mx, int my) {
		if (isEnabled())
			if (mouseInside(mx, my))
				super.actionMousePressed(button, mx, my);
			else if (Main.DEBUG)
				System.out.println("Pressed out of Popup");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.AbstractIHMComponent#actionMouseReleased(int, int, int)
	 */
	@Override
	public void actionMouseReleased(int button, int mx, int my) {
		if (isEnabled())
			if (isMouseOver())
				super.actionMouseReleased(button, mx, my);
			else if (Main.DEBUG)
				System.out.println("Released out of Popup");
	}

	/**
	 * Ferme la popup
	 * 
	 * @param ecran
	 *            : l'ecran dans lequel est affichee la popup
	 */
	public void close(Ecran ecran) {
		if (Main.DEBUG)
			System.out.println("Close Popup");
		this.disable();
		this.setVisible(false);
		ecran.removeComponent(this);
	}

	/**
	 * Affiche la popup
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
		ecran.addComponent(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.AbstractIHMComponent#disable()
	 */
	@Override
	public void disable() {
		super.disable();
		for (IHMComponent fils : composants) {
			fils.disable();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.AbstractIHMComponent#enable()
	 */
	@Override
	public void enable() {
		super.enable();
		for (IHMComponent fils : composants) {
			fils.enable();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see slickIHM.IHMConteneur#someoneHasFocus()
	 */
	@Override
	public boolean someoneHasFocus() {
		for (IHMComponent fils : composants) {
			if (fils.hasFocus())
				return true;
		}
		return false;
	}

}
