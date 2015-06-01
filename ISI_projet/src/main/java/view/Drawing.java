package view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.graph.Node;
import model.manager.Manager;

public class Drawing extends JPanel implements Observer {

	private Image img;
	private Manager model;
	private int magnificationFactor;

	public Drawing(Manager model, String img) {
		this(new ImageIcon(img).getImage(),0);
		this.magnificationFactor = 0;

		this.model = model;
		model.addObserver(this);
	}

	public Drawing(Manager model, String img, int magnificationFactor) {
		this(new ImageIcon(img).getImage(),magnificationFactor);
		this.magnificationFactor = magnificationFactor;
		
		this.model = model;
		model.addObserver(this);
	}

	public Drawing(Image img, int magnificationFactor) {
		this.img = img;
		Dimension size = new Dimension(
				img.getWidth(null) + magnificationFactor, img.getHeight(null)
						+ magnificationFactor);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {

		Graphics2D graph = (Graphics2D) g;

		// Epaisseur du trait
		graph.setStroke(new BasicStroke(2.0f));

		// Carte de fond
		g.drawImage(img, 0, 0, img.getWidth(null) + magnificationFactor,
				img.getHeight(null) + magnificationFactor, this);

		// Dessine les arc
		showEdges(graph);

		// Dessine les noeuds
		showNodes(graph);

		// Dessine les Robots
		showRobots(graph);
	}

	public void showEdges(Graphics g) {
		if (model.getGraph().getAllEdges().size() > 0)
			for (int i = 0; i < model.getGraph().getAllEdges().size(); i++) {
				new DrawingArc(model.getGraph().getAllEdges().get(i),
						magnificationFactor, g);
			}

	}

	public void showNodes(Graphics g) {
		if (model.getGraph().getAllNodes().size() > 0)
			for (int i = 0; i < model.getGraph().getAllNodes().size(); i++) {
				Node node = model.getGraph().getAllNodes().get(i);
				new DrawingNode(node, magnificationFactor, g);

				if (model.getGraph().getAllNodes().get(i).getFireLevel() > 0)
					showFires(node, g);
			}
	}

	public void showFires(Node n, Graphics g) {
		new DrawingFire(n,magnificationFactor,g);
	}

	public void showRobots(Graphics g) {
		if (model.getRobots().size() > 0)
			for (int i = 0; i < model.getRobots().size(); i++) {
				new DrawingRobot(model.getRobots().get(i), magnificationFactor,
						g);
			}
	}

	public void Update() {
		repaint();
	}

	public int getMagnificationFactor() {
		return magnificationFactor;
	}

	public void setMagnificationFactor(int magnificationFactor) {
		this.magnificationFactor = magnificationFactor;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
}
