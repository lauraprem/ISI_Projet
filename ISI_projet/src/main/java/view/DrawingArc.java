package view;

import model.graph.edge.Edge;

import java.awt.*;

public class DrawingArc {

    public DrawingArc(Edge model, int magnificationFactor, Graphics graph) {
        if (graph == null) {
            return;
        }

        switch (model.getGround().getType()) {
            case FLAT:
            	graph.setColor(Color.black);
                break;
            case STEEP:
            	graph.setColor(Color.orange);
                break;
            case FLOODED:
            	graph.setColor(Color.blue);
                break;
            default:
            	graph.setColor(Color.black);
                break;
        }
        
        graph.drawLine((int) model.getSource().getX(), (int) model.getSource().getY(), (int) model.getDestination().getX(), (int) model.getDestination().getY());
    }
}
