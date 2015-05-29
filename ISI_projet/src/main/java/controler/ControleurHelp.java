package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.manager.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.MainWindow;
import view.MenuLabel;

public class ControleurHelp implements ActionListener {
	private final static Logger logger = LogManager.getLogger();
	private Manager model;
	private MainWindow vue;

	public ControleurHelp(MainWindow vue,Manager model) {
		 this.model = model;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case MenuLabel.HELP:
			// TODO model
			logger.info(String.format("Action \"%s\" selected", MenuLabel.HELP_LABEL));
			break;

		case MenuLabel.ABOUT:
			// TODO model
			logger.info(String.format("Action \"%s\" selected", MenuLabel.ABOUT_LABEL));
			break;

		default:
			break;
		}
	}
}
