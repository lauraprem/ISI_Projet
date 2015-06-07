package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.MenuLabel;

public class ControlerElements implements ActionListener {
	private final static Logger logger = LogManager.getLogger();
	private ControlerClic controleurClic;

	public ControlerElements(ControlerClic controleurClic) {
		this.controleurClic = controleurClic;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String typeElement = null;

		switch (e.getActionCommand()) {
		case MenuLabel.ADD_TOUT_TERRAIN_ROBOT:
			typeElement = MenuLabel.ADD_TOUT_TERRAIN_ROBOT;
			logger.info(String.format("Action \"%s\" selected",
					MenuLabel.ADD_TOUT_TERRAIN_ROBOT_FR));
			break;

		case MenuLabel.ADD_CHENILLE_ROBOT:
			typeElement = MenuLabel.ADD_CHENILLE_ROBOT;
			logger.info(String.format("Action \"%s\" selected",
					MenuLabel.ADD_CHENILLE_ROBOT_FR));
			break;

		case MenuLabel.ADD_A_PATE_ROBOT:
			typeElement = MenuLabel.ADD_A_PATE_ROBOT;
			logger.info(String.format("Action \"%s\" selected",
					MenuLabel.ADD_A_PATE_ROBOT_FR));
			break;

		case MenuLabel.ADD_FIRE:
			typeElement = MenuLabel.ADD_FIRE;
			logger.info(String.format("Action \"%s\" selected",
					MenuLabel.ADD_FIRE_FR));
			break;

		default:
			break;
		}

		controleurClic.setTypeElement(typeElement);
	}

}
