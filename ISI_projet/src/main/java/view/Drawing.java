package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Drawing extends JPanel implements Observer {

	private Image img;

	public Drawing(String img) {
		// modele.AjoutObservateur(this);
		this(new ImageIcon(img).getImage());
	}

	public Drawing(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);

		// Dessine les arc
		showEdges(g);

		// Dessine les noeuds
		showNodes(g);

		// Dessine les feu
		showFires(g);

		// Dessine les Robots
		showRobots(g);
	}

	public void showEdges(Graphics g) {
	}

	public void showNodes(Graphics g) {
	}

	public void showFires(Graphics g) {
	}

	public void showRobots(Graphics g) {
	}

	public void Update() {
		repaint();
	}
}