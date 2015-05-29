package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuHaut extends JPanel {
    public static final Dimension VGAP = new Dimension(1, 5);
    public static final Dimension HGAP = new Dimension(5, 1);

    private ArrayList<JButton> listButon;

    public MenuHaut() {

        listButon = new ArrayList<JButton>();

        JToolBar toolBar = new JToolBar();
        this.addButton(toolBar, MenuLabel.ERASE_LABEL, MenuLabel.ERASE, null, this);

        toolBar.add(Box.createRigidArea(HGAP));
        this.add(toolBar);
        this.addButton(toolBar, MenuLabel.ADD_NODE_LABEL, MenuLabel.ADD_NODE, null, this);
        this.addButton(toolBar, MenuLabel.ADD_EDGE_LABEL, MenuLabel.ADD_EDGE, null, this);
        this.addButton(toolBar, MenuLabel.ADD_FIRE_LABEL, MenuLabel.ADD_FIRE, null, this);
        this.addButton(toolBar, MenuLabel.ADD_ROBOT_LABEL, MenuLabel.ADD_ROBOT, null, this);
    }

    public ArrayList<JButton> getListButon() {
        return listButon;
    }

    public void setListButon(ArrayList<JButton> listButon) {
        this.listButon = listButon;
    }

    public JButton addButton(JComponent p, String name, String tooltiptext,
                             String imageName, Object objet) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton) p.add(new JButton(name));
        } else {
            java.net.URL u = objet.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon(u);
                b = (JButton) p.add(new JButton(im));
            } else
                b = (JButton) p.add(new JButton(name));
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0, 0, 0, 0));
        listButon.add(b);
        return b;
    }
}
