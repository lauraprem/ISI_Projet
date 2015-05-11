package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

public class ControleurFile implements ActionListener {

	// private EnvironnementTortue model; //TODO model
	private MainWindow vue;

	public ControleurFile(MainWindow vue) { // TODO model
	// this.model = model; //TODO model
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Nouveau graph":
			// TODO model
			System.out.println("Nouveau graph");
			break;

		case "Charger graph":
			// TODO model
			System.out.println("Charger graph");
			break;

		case "Sauvegarder graph":
			// TODO model
			System.out.println("Sauvegarder graph");
			break;

		case "Quitter":
			System.exit(0);
			break;

		default:
			break;
		}
	}
}
