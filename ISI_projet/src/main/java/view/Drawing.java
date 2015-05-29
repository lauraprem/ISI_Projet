package view;

import model.graph.Node;
import model.manager.Manager;

import javax.swing.*;

import java.awt.*;

public class Drawing extends JPanel implements Observer {

    private Image img;
    private Manager model;

    public Drawing(Manager model, String img) {
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
    	
    	 Graphics2D graph = (Graphics2D) g;
         
         // Epaisseur du trait
    	 graph.setStroke(new BasicStroke(2.0f));
         
        // Carte de fond
    	 graph.drawImage(img, 0, 0, null);

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
                new DrawingArc(model.getGraph().getAllEdges().get(i), g);
            }

    }

    public void showNodes(Graphics g) {
        if (model.getGraph().getAllNodes().size() > 0)
            for (int i = 0; i < model.getGraph().getAllNodes().size(); i++) {
                Node node = model.getGraph().getAllNodes().get(i);
                new DrawingNode(node, g);

                if (model.getGraph().getAllNodes().get(i).getFireLevel() > 0) showFires(node, g);
            }
    }

    public void showFires(Node n, Graphics g) {
        new DrawingFire(g, n);
    }

    public void showRobots(Graphics g) {
        if (model.getRobots().size() > 0)
            for (int i = 0; i < model.getRobots().size(); i++) {
                new DrawingRobot(g, model.getRobots().get(i));
            }
    }

    public void Update() {
        repaint();
    }
}
