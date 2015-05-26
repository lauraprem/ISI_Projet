package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.manager.Manager;
import view.MainWindow;

public class ControleurHelp implements ActionListener {

	 private Manager model;
	private MainWindow vue;

	public ControleurHelp(MainWindow vue,Manager model) {
		 this.model = model;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Help":
			// TODO model
			System.out.println("Help");
			break;

		case "About":
			// TODO model
			System.out.println("About");
			break;

		default:
			break;
		}
	}
}
