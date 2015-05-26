package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

public class ControleurGraph implements ActionListener {
	// private EnvironnementTortue model; //TODO model
	private MainWindow vue;

	public ControleurGraph(MainWindow vue) { // TODO model
	// this.model = model; //TODO model
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int typeElement;
		typeElement = -1;
		
		switch (e.getActionCommand()) {
		case "Add_Noeud":
			typeElement = 4;
			System.out.println("Add_Noeud");
			break;

		case "Add_arc":
			typeElement = 5;
			System.out.println("Add_arc");
			break;

		case "Add_arc_Escarp":
			typeElement = 6;
			System.out.println("Add_arc_Escarp");
			break;

		case "Add_arc_Plat":
			typeElement = 7;
			System.out.println("Add_arc_Plat");
			break;

		default:
			break;
		}
		vue.addMouseListener(new ControleurMap(vue,typeElement)); // Singloton
	}
}