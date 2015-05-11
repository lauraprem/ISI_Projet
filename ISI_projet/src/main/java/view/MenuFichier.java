package view;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuFichier extends JMenuBar {

	JMenu menuFile;
	JMenu menuCommandes;
	JMenu menuHelp;
	
	private ArrayList<JMenuItem> listButon;

	public MenuFichier() {
		
		listButon = new ArrayList<JMenuItem>();
		
		menuFile = new JMenu("File"); // on installe le premier menu
		this.add(menuFile);
		addMenuItem(menuFile, "Nouveau graph", "Nouveau graph", KeyEvent.VK_N);
		addMenuItem(menuFile, "Charger", "Charger", KeyEvent.VK_C);
		addMenuItem(menuFile, "Sauvegarder", "Sauvegarder", KeyEvent.VK_S);
		addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

		menuCommandes = new JMenu("Ajouter"); // on installe le premier
		this.add(menuCommandes);
		addMenuItem(menuCommandes, "un noeud", "Add_Noeud", -1);
		addMenuItem(menuCommandes, "un robot", "Add_Robot", -1);
		addMenuItem(menuCommandes, "un feu", "Add_Feu", -1);

		menuHelp = new JMenu("Aide");
		this.add(menuHelp);
		addMenuItem(menuHelp, "Aide", "Help", -1);
		addMenuItem(menuHelp, "A propos", "About", -1);

	}

	public void addMenuItem(JMenu m, String label, String command, int key) {
		JMenuItem menuItem;
		menuItem = new JMenuItem(label);
		m.add(menuItem);

		menuItem.setActionCommand(command);
		if (key > 0) {
			if (key != KeyEvent.VK_DELETE)
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key,
						Event.CTRL_MASK, false));
			else
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
		}
		listButon.add(menuItem);
	}

	public ArrayList<JMenuItem> getListButon() {
		return listButon;
	}

	public void setListButon(ArrayList<JMenuItem> listButon) {
		this.listButon = listButon;
	}
}
