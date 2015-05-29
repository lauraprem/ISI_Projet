package controler;

import model.manager.Manager;
import view.MainWindow;
import view.MenuLabel;

import javax.swing.*;

public class MainControler {
	 private Manager model;
	private MainWindow vue;

	public MainControler(MainWindow vue, Manager model) {
		this.vue = vue;
		this.model = model;
		this.addListenersView();
	}

	/**
	 * Ajout des ecouteurs (controleurs) sur les composants graphiques
	 */
	private void addListenersView() {
		
		ControleurMap controleurMap = new ControleurMap(vue,model,-1);
		vue.getDessin().addMouseListener(controleurMap);
		
		for (JMenuItem item : vue.getMenuFile().getListButon()) {
			if (this.controleFile(item) == true) {
				item.addActionListener(new ControleurFile(vue,model));
			} else if (controleGraph(item) == true) {
				item.addActionListener(new ControleurGraph(vue,model,controleurMap));
			} else if (controleHelp(item) == true) {
				item.addActionListener(new ControleurHelp(vue,model));
			} else {
				item.addActionListener(new ControleurElements(vue,model,controleurMap));
			}
			
			

		}
		// for (JButton boutton : vue.getMenuHaut().getListButon()) {
		// if (this.controleFile(boutton) == true) {
		// boutton.addActionListener(new ControleurElements(vue));
		// } else {
		//
		// }
		// }
	}

	/**
	 * Indique si la commande de l'item sert à donner une information sur le
	 * logiciel à l'utilisateur, il comprend l'action Add_Noeud, Add_arc,
	 * Add_arc_Escarp et Add_arc_Plat.
	 * 
	 * @param item
	 * @return vrai si l'item à une commande parmis celle ci-dessus
	 */
	private boolean controleHelp(JMenuItem item) {
		switch (item.getActionCommand()) {
		case MenuLabel.HELP:
			return true;
		case MenuLabel.ABOUT:
			return true;
		}
		return false;
	}

	/**
	 * Indique si la commande de l'item sert à la gestion de graph dans ça
	 * globalité, il comprend l'action Nouveau graph, Charger graph, Sauvegarder
	 * graph et Quitter.
	 * 
	 * @param item
	 * @return vrai si l'item à une commande parmis celle ci-dessus
	 */
	private boolean controleFile(JMenuItem item) {
		switch (item.getActionCommand()) {
			case MenuLabel.NEW:
				return true;
			case MenuLabel.LOAD:
				return true;
			case MenuLabel.SAVE:
				return true;
			case MenuLabel.SAVE_AS:
				return true;
			case MenuLabel.QUIT:
				return true;
			case MenuLabel.RUN:
				return true;
			case MenuLabel.STOP:
				return true;
		}
		return false;
	}

	/**
	 * Indique si la commande de l'item sert à la gestion de graph, des noeuds
	 * et des arcs, il comprend l'action Add_Noeud, Add_arc, Add_arc_Escarp et
	 * Add_arc_Plat.
	 * 
	 * @param item
	 * @return vrai si l'item à une commande parmis celle ci-dessus
	 */
	private boolean controleGraph(JMenuItem item) {
		switch (item.getActionCommand()) {
		case MenuLabel.ADD_NODE:
			return true;
		case MenuLabel.ADD_FLOODED_EDGE:
			return true;
		case MenuLabel.ADD_STEEP_EDGE:
			return true;
		case MenuLabel.ADD_FLAT_EDGE:
			return true;
		}
		return false;
	}
}
