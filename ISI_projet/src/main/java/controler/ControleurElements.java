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
		int typeElement;
		typeElement = -1;
		
		switch (e.getActionCommand()) {
		case "Tout_terrain":			
			typeElement = 0;
			System.out.println("Tout_terrain");
			break;

		case "Chenille":
			typeElement = 1;
			System.out.println("Chenille");
			break;

		case "A_pates":
			typeElement = 2;
			System.out.println("A_pates");
			break;

		case "Add_Feu":
			typeElement = 3;
			System.out.println("Add_Feu");
			break;

		default:
			break;
		}
		vue.addMouseListener(new ControleurMap(vue,typeElement));
	}
	
}
