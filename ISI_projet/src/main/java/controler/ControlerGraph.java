package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.MenuLabel;

public class ControlerGraph implements ActionListener {
	private final static Logger logger = LogManager.getLogger();
	private ControlerClic controleurClic;

	public ControlerGraph(ControlerClic controleurClic) {
		this.controleurClic = controleurClic;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String typeElement;
		typeElement = "";

		switch (e.getActionCommand()) {
		case MenuLabel.ADD_NODE:
			typeElement = MenuLabel.ADD_NODE;
			logger.info(String.format("Action \"%s\" selected",
					MenuLabel.ADD_NODE_FR));
			break;

		case MenuLabel.ADD_FLOODED_EDGE:
			typeElement = MenuLabel.ADD_FLOODED_EDGE;
			logger.info(String.format("Action \"%s\" selected",
					MenuLabel.ADD_FLOODED_EDGE_FR));
			break;

		case MenuLabel.ADD_STEEP_EDGE:
			typeElement = MenuLabel.ADD_STEEP_EDGE;
			logger.info(String.format("Action \"%s\" selected",
					MenuLabel.ADD_STEEP_EDGE_FR));
			break;

		case MenuLabel.ADD_FLAT_EDGE:
			typeElement = MenuLabel.ADD_FLAT_EDGE;
			logger.info(String.format("Action \"%s\" selected",
					MenuLabel.ADD_FLAT_EDGE_FR));
			break;

		default:
			break;
		}
		controleurClic.setTypeElement(typeElement);
	}
}
