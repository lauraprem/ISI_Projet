package view;

import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MenuHaut extends JPanel {
	public static final Dimension VGAP = new Dimension(1, 5);
	public static final Dimension HGAP = new Dimension(5, 1);

	private ArrayList<JButton> listButon;

	public MenuHaut() {

		listButon = new ArrayList<JButton>();

		JToolBar toolBar = new JToolBar();
		this.addButton(toolBar, " Effacer ", "Effacer", null, this);

		toolBar.add(Box.createRigidArea(HGAP));
		this.add(toolBar);
		this.addButton(toolBar, " Ajouter noeud ", "Add_Noeud", null, this);
		this.addButton(toolBar, " Ajouter arc ", "Add_arc", null, this);
		this.addButton(toolBar, " Ajouter feu ", "Add_Feu", null, this);
		this.addButton(toolBar, " Ajouter robot ", "Add_Robot", null, this);
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
