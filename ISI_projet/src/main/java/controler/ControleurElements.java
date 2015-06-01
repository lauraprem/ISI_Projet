package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.manager.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.MainWindow;
import view.MenuLabel;

public class ControleurElements implements ActionListener {
	private final static Logger logger = LogManager.getLogger();
	private Manager model;
	private MainWindow vue;
	private ControleurMap controleurMap;

	public ControleurElements(MainWindow vue,Manager model, ControleurMap controleurMap) {
	 this.model = model;
		this.vue = vue;
		this.controleurMap = controleurMap;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int typeElement;
		typeElement = -1;
		
		switch (e.getActionCommand()) {
		case MenuLabel.ADD_TOUT_TERRAIN_ROBOT:
			typeElement = 0;
			logger.info(String.format("Action \"%s\" selected", MenuLabel.ADD_TOUT_TERRAIN_ROBOT_FR));
			break;

		case MenuLabel.ADD_CHENILLE_ROBOT:
			typeElement = 1;
			logger.info(String.format("Action \"%s\" selected", MenuLabel.ADD_CHENILLE_ROBOT_FR));
			break;

		case MenuLabel.ADD_A_PATE_ROBOT:
			typeElement = 2;
			logger.info(String.format("Action \"%s\" selected", MenuLabel.ADD_A_PATE_ROBOT_FR));
			break;

		case MenuLabel.ADD_FIRE:
			typeElement = 3;
			logger.info(String.format("Action \"%s\" selected", MenuLabel.ADD_FIRE_FR));
			break;
			
		default:
			break;
		}
		
		controleurMap.setTypeElement(typeElement);
	}
	
}
