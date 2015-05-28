package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.graph.Node;
import model.manager.Manager;

public class Drawing extends JPanel implements Observer {

	private Image img;
	private Manager model;

	public Drawing(Manager model,String img) {
		this(new ImageIcon(img).getImage());
		
		this.model = model;
		model.addObserver(this);
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
		
		// Carte de fond
		g.drawImage(img, 0, 0, null);
		
		// Dessine les arc
		showEdges(g);

		// Dessine les noeuds
		showNodes(g);

		// Dessine les Robots
		showRobots(g);
	}

	public void showEdges(Graphics g) {
		// TODO Test
//		Node n1 = new Node("",new Point(100,10));
//		n1.setFireLevel(10);
//		Node n2 = new Node("",new Point(100,200));
//		Edge e = new Edge(n1,n2);
//		e.setGround(new Ground(GroundType.FLAT));
		if( model.getGraph().getAllEdges().size()>0)
		for (int i = 0; i < model.getGraph().getAllEdges().size(); i++) {
			new DrawingArc(model.getGraph().getAllEdges().get(i),g);
		}
		
	}

	public void showNodes(Graphics g) {
//		Node n1 = new Node("",new Point(100,10));
//		n1.setFireLevel(10);
//		Node n2 = new Node("",new Point(100,200));
//		new DrawingNode(n1,g);
//		new DrawingNode(n2,g);
		if( model.getGraph().getAllNodes().size()>0)
		for (int i = 0; i < model.getGraph().getAllNodes().size(); i++) {
			Node node = model.getGraph().getAllNodes().get(i);
			new DrawingNode(node,g);
			
			if(model.getGraph().getAllNodes().get(i).getFireLevel() >0)showFires(node,g);
		}
	}

	public void showFires(Node n,Graphics g) {
//		Node n1 = new Node("",new Point(100,10));
		new DrawingFire(g,n);
	}

	public void showRobots(Graphics g) {
//		Node n1 = new Node("",new Point(100,10));
		
		if( model.getRobots().size()>0)
		for (int i = 0; i < model.getRobots().size(); i++) {
			new DrawingRobot(g,model.getRobots().get(i));
		}
	}

	public void Update() {
		repaint();
	}
}
