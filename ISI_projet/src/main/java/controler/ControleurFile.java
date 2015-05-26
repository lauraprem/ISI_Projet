package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.manager.Manager;
import view.MainWindow;

public class ControleurFile implements ActionListener {

	 private Manager model;
	private MainWindow vue;

	public ControleurFile(MainWindow vue,Manager model) {
	 this.model = model;
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
