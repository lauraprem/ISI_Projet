package view;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuFichier extends JMenuBar {
	
	private ArrayList<JMenuItem> listButon;

	public MenuFichier() {
		
		listButon = new ArrayList<JMenuItem>();
		
		JMenu menuFile = new JMenu("File"); // on installe le premier menu
		this.add(menuFile);
		addMenuItem(menuFile, "Nouveau graph", "Nouveau graph", KeyEvent.VK_N);
		addMenuItem(menuFile, "Charger graph", "Charger graph", KeyEvent.VK_C);
		addMenuItem(menuFile, "Sauvegarder graph", "Sauvegarder graph", KeyEvent.VK_S);
		addMenuItem(menuFile, "Démarrer", "Run", KeyEvent.VK_R, false);
		addMenuItem(menuFile, "Arrêter", "Stop", KeyEvent.VK_S, false);
		addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

		JMenu menuGraph = new JMenu("Menu graph");
		this.add(menuGraph);
		addMenuItem(menuGraph, "ajout noeud", "Add_Noeud", -1);
		addMenuItem(menuGraph, "ajout arc normal", "Add_arc", -1);
		addMenuItem(menuGraph, "ajout arc escarpé", "Add_arc_Escarp", -1);
		addMenuItem(menuGraph, "ajout arc plat", "Add_arc_Plat", -1);
		
		JMenu menuRobot = new JMenu("Ajout Robot");
		this.add(menuRobot);
		addMenuItem(menuRobot, "tout terrain", "Tout_terrain", -1);
		addMenuItem(menuRobot, "chenille", "Chenille", -1);
		addMenuItem(menuRobot, "à pates", "A_pates", -1);
		
		JMenu menuElements = new JMenu("Ajout élements");
		this.add(menuElements);
		addMenuItem(menuElements,  "un feu", "Add_Feu", -1);

		JMenu menuHelp = new JMenu("Aide");
		this.add(menuHelp);
		addMenuItem(menuHelp, "Aide", "Help", -1);
		addMenuItem(menuHelp, "A propos", "About", -1);

	}
	public void addMenuItem(JMenu m, String label, String command, int key, Boolean ctrl) {
		JMenuItem menuItem;
		menuItem = new JMenuItem(label);
		m.add(menuItem);

		menuItem.setActionCommand(command);
		if (key > 0) {
			if (key != KeyEvent.VK_DELETE && ctrl)
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key,
						Event.CTRL_MASK, false));
			else
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
		}
		listButon.add(menuItem);
	}

	public void addMenuItem(JMenu m, String label, String command, int key) {
		addMenuItem(m, label, command, key, true);
	}

	public ArrayList<JMenuItem> getListButon() {
		return listButon;
	}

	public void setListButon(ArrayList<JMenuItem> listButon) {
		this.listButon = listButon;
	}
}
