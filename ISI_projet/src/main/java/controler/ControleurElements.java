package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.manager.Manager;
import view.MainWindow;

public class ControleurElements implements ActionListener {
	 private Manager model;
	private MainWindow vue;
	private ControleurMap controleurMap;

	public ControleurElements(MainWindow vue,Manager model, ControleurMap controleurMap) {
	 this.model = model;
		this.vue = vue;
		this.controleurMap = controleurMap;
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
		
		controleurMap.setTypeElement(typeElement);
	}
	
}
