package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

public class ControleurHelp implements ActionListener {

	// private EnvironnementTortue model; //TODO model
	private MainWindow vue;

	public ControleurHelp(MainWindow vue) { // TODO model
		// this.model = model; //TODO model
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
