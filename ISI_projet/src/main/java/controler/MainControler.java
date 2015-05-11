package controler;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import view.MainWindow;

public class MainControler {
	// private Manager modele;
	private MainWindow vue;

	public MainControler(MainWindow vue) { // param model+vue
		this.vue = vue;

		this.addListenersView();
	}

	/**
	 * Ajout des ecouteurs (controleurs) sur les composants graphiques
	 */
	private void addListenersView() {

		for (JMenuItem item : vue.getMenuFile().getListButon()) {
			if (this.controleFile(item) == true) {
				item.addActionListener(new ControleurFile(vue));
			} else if (controleGraph(item) == true) {
				item.addActionListener(new ControleurGraph(vue));
			} else if (controleHelp(item) == true) {
				item.addActionListener(new ControleurHelp(vue));
			} else {
				item.addActionListener(new ControleurElements(vue));
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
		case "Help":
			return true;
		case "About":
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
		case "Nouveau graph":
			return true;
		case "Charger graph":
			return true;
		case "Sauvegarder graph":
			return true;
		case "Quitter":
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
		case "Add_Noeud":
			return true;
		case "Add_arc":
			return true;
		case "Add_arc_Escarp":
			return true;
		case "Add_arc_Plat":
			return true;
		}
		return false;
	}
}
