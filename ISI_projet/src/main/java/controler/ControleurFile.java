package controler;

import model.manager.Manager;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				model.reset();
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
				if (model != null) model.exitManager();
				System.exit(0);
				break;
			case "Run":
				if (model != null) model.start();
				break;
			case "Stop":
				if (model != null) model.pauseManager();
				break;
			default:
				break;
		}
	}
}
