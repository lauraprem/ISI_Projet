package view;

import model.graph.Node;

import java.awt.*;

public class DrawingNode {

    public DrawingNode(Node model, Graphics graph) {
        if (graph == null) {
            return;
        }
        graph.setColor(Color.lightGray);
        graph.fillOval(model.getX() - 8, model.getY() - 8, 16, 16);
    }
}
