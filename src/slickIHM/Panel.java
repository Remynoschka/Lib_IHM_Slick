package slickIHM;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public abstract class Panel extends IHMComponent implements IHMConteneur {
	protected List<IHMComponent>	composants;

	public Panel(Fenetre fenetre, int x, int y, int w, int h, Ecran ecran) throws SlickException {
		super(fenetre, x, y, w, h, ecran);
		composants = new ArrayList<>();
	}

	@Override
	public void addComponent(IHMComponent element) {
		composants.add(element);
		element.setConteneur(this);
	}

	@Override
	public void removeComponent(IHMComponent element) {
		composants.remove(element);
		//element.setConteneur(null);
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
	public void disable() {
		super.disable();
		for (IHMComponent fils : composants){
			fils.disable();
		}
	}
	
	@Override
	public void enable() {
		super.disable();
		for (IHMComponent fils : composants){
			fils.enable();
		}
	}
}
