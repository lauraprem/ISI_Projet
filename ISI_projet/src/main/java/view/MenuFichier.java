package view;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuFichier extends JMenuBar {

    private ArrayList<JMenuItem> listButon;

    public MenuFichier() {

        listButon = new ArrayList<JMenuItem>();

        JMenu menuFile = new JMenu(MenuLabel.MENU_FILE_FR); // on installe le premier menu
        this.add(menuFile);
        addMenuItem(menuFile, MenuLabel.NEW_FR, MenuLabel.NEW, KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
        addMenuItem(menuFile, MenuLabel.LOAD_FR, MenuLabel.LOAD, KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
        addMenuItem(menuFile, MenuLabel.SAVE_FR, MenuLabel.SAVE, KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        addMenuItem(menuFile, MenuLabel.SAVE_AS_FR, MenuLabel.SAVE_AS, KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK);
        menuFile.addSeparator();
        addMenuItem(menuFile, MenuLabel.RUN_FR, MenuLabel.RUN, KeyEvent.VK_R);
        addMenuItem(menuFile, MenuLabel.STOP_FR, MenuLabel.STOP, KeyEvent.VK_S);
        menuFile.addSeparator();
        addMenuItem(menuFile, MenuLabel.QUIT_FR, MenuLabel.QUIT, KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK);

        JMenu menuGraph = new JMenu(MenuLabel.MENU_GRAPH_FR);
        this.add(menuGraph);
        addMenuItem(menuGraph, MenuLabel.ADD_NODE_FR, MenuLabel.ADD_NODE, -1);
        menuGraph.addSeparator();
        addMenuItem(menuGraph, MenuLabel.ADD_FLAT_EDGE_FR, MenuLabel.ADD_FLAT_EDGE, -1);
        addMenuItem(menuGraph, MenuLabel.ADD_FLOODED_EDGE_FR, MenuLabel.ADD_FLOODED_EDGE, -1);
        addMenuItem(menuGraph, MenuLabel.ADD_STEEP_EDGE_FR, MenuLabel.ADD_STEEP_EDGE, -1);

        JMenu menuRobot = new JMenu(MenuLabel.MENU_ROBOT_FR);
        this.add(menuRobot);
        addMenuItem(menuRobot, MenuLabel.ADD_TOUT_TERRAIN_ROBOT_FR, MenuLabel.ADD_TOUT_TERRAIN_ROBOT, -1);
        addMenuItem(menuRobot, MenuLabel.ADD_CHENILLE_ROBOT_FR, MenuLabel.ADD_CHENILLE_ROBOT, -1);
        addMenuItem(menuRobot, MenuLabel.ADD_A_PATE_ROBOT_FR, MenuLabel.ADD_A_PATE_ROBOT, -1);

        JMenu menuElements = new JMenu(MenuLabel.MENU_ELEMENT_FR);
        this.add(menuElements);
        addMenuItem(menuElements, MenuLabel.ADD_FIRE_FR, MenuLabel.ADD_FIRE, -1);

        JMenu menuHelp = new JMenu(MenuLabel.MENU_AIDE_FR);
        this.add(menuHelp);
        addMenuItem(menuHelp, MenuLabel.HELP_FR, MenuLabel.HELP, -1);
        addMenuItem(menuHelp, MenuLabel.ABOUT_FR, MenuLabel.ABOUT, -1);

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
