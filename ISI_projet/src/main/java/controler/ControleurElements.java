package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

public class ControleurElements implements ActionListener {
	// private EnvironnementTortue model; //TODO model
	private MainWindow vue;

	public ControleurElements(MainWindow vue) { // TODO model
	// this.model = model; //TODO model
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Tout_terrain":
			// TODO model
			System.out.println("Tout_terrain");
			break;

		case "Chenille":
			// TODO model
			System.out.println("Chenille");
			break;

		case "A_pates":
			// TODO model
			System.out.println("A_pates");
			break;

		case "Add_Feu":
			// TODO model
			System.out.println("Add_Feu");
			break;

		default:
			break;
		}
	}
}
