package view;
// package logo;

import model.graph.Node;

import java.awt.*;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 *
 * @author J. Ferber
 * @version 2.0
 */

public class DrawingNode {

    public DrawingNode(Node model, Graphics graph) {
        if (graph == null) {
            return;
        }
        graph.setColor(Color.black);
        graph.drawOval((int) model.getX() - 5, (int) model.getY() - 5, 10, 10);
    }
}
