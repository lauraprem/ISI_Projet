package view;

import model.graph.edge.Edge;

import java.awt.*;

public class DrawingArc {

    public DrawingArc(Edge model, Graphics graph) {
        if (graph == null) {
            return;
        }
        graph.setColor(model.getGround().getType().getColor());
        graph.drawLine(model.getSource().getX(), model.getSource().getY(), model.getDestination().getX(), model.getDestination().getY());
    }
}
