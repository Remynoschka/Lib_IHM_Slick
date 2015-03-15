package slickIHM;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

/**
 * Cette classe permet de creer un espace pouvant contenir d'autres elements.
 * 
 * @author Remynoschka
 * 
 */
public abstract class Panel extends AbstractIHMComponent implements
		IHMConteneur {
	/**
	 * La liste des composants fils
	 */
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
	public Panel(Fenetre fenetre, int x, int y, int w, int h, Ecran ecran)
			throws SlickException {
		super(fenetre, x, y, w, h, ecran);
		composants = new ArrayList<>();
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
		// element.setConteneur(null);
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
		super.disable();
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
