/**
 * 
 */
package slickIHM;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Classe generique pour les ecrans. Elle oblige un comportement par defaut
 * lorsque l'on entre et sort de l'etat.
 * 
 * <b>Attention :</b> Ne pas faire contenir d'autres Ecrans
 * 
 * @author Remynoschka
 * 
 */
public abstract class Ecran extends BasicGameState implements IHMConteneur {
	protected List<IHMComponent>	composants;

	/**
	 * Cree un nouvel Ecran
	 */
	public Ecran() {
		composants = new ArrayList<>();
	}

	@Override
	public void render(GameContainer container, StateBasedGame jeu, Graphics g)
			throws SlickException {
		for (IHMComponent c : composants) {
			c.render(container, g);
		}
	}

	/**
	 * Methode appellee lorsqu'on entre dans l'ecran
	 */
	public abstract void enterBehaviour();

	/**
	 * Methode appellee lorsqu'on sort de l'ecran
	 */
	public abstract void leaveBehaviour();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.newdawn.slick.state.BasicGameState#enter(org.newdawn.slick.GameContainer
	 * , org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		enterBehaviour();
		super.enter(container, game);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.newdawn.slick.state.BasicGameState#leave(org.newdawn.slick.GameContainer
	 * , org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.leave(container, game);
		leaveBehaviour();
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
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public int getWidth() {
		return Fenetre.FENETRE.getWidth();
	}

	@Override
	public int getHeight() {
		return Fenetre.FENETRE.getHeight();
	}
}
