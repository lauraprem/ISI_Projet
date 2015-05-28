package view;

import java.awt.Event;
import java.awt.event.InputEvent;
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
		addMenuItem(menuFile, "Nouveau", "Nouveau graph", KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
		addMenuItem(menuFile, "Ouvrir", "Charger graph", KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
		addMenuItem(menuFile, "Enregistrer", "Sauvegarder graph", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
		addMenuItem(menuFile, "Enregistrer sous...", "Sauvegarder graph sous", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK);
		menuFile.addSeparator();
		addMenuItem(menuFile, "Démarrer", "Run", KeyEvent.VK_R);
		addMenuItem(menuFile, "Arrêter", "Stop", KeyEvent.VK_S);
		menuFile.addSeparator();
		addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK);

		JMenu menuGraph = new JMenu("Menu graph");
		this.add(menuGraph);
		addMenuItem(menuGraph, "ajout noeud", "Add_Noeud", -1);
		addMenuItem(menuGraph, "ajout arc plat", "Add_arc_Plat", -1);
		addMenuItem(menuGraph, "ajout arc innondé", "Add_arc_Innond", -1);
		addMenuItem(menuGraph, "ajout arc escarpé", "Add_arc_Escarp", -1);
		
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
	public void addMenuItem(JMenu m, String label, String command, int key, int ctrlModifier) {
		JMenuItem menuItem;
		menuItem = new JMenuItem(label);
		m.add(menuItem);

		menuItem.setActionCommand(command);
		if (key > 0) {
			if (key != KeyEvent.VK_DELETE && ctrlModifier != 0)
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key,
						ctrlModifier, false));
			else
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
		}
		listButon.add(menuItem);
	}

	public void addMenuItem(JMenu m, String label, String command, int key) {
		addMenuItem(m, label, command, key, 0);
	}

	public ArrayList<JMenuItem> getListButon() {
		return listButon;
	}

	public void setListButon(ArrayList<JMenuItem> listButon) {
		this.listButon = listButon;
	}
}
