package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.manager.Manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.MainWindow;
import view.MenuLabel;
import view.about.AboutWindow;

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
			logger.info(String.format("Action \"%s\" selected", MenuLabel.HELP_LABEL));
			break;

		case MenuLabel.ABOUT:
			AboutWindow windowsHelp= new AboutWindow();
			windowsHelp.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent arg0) {
					windowsHelp.setVisible(false);
				}
			});
			windowsHelp.pack();
			windowsHelp.setLocationRelativeTo(null);
			windowsHelp.setVisible(true);
			logger.info(String.format("Action \"%s\" selected", MenuLabel.ABOUT_LABEL));
			break;

		default:
			break;
		}
	}
}
