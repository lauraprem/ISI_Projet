package view;

import model.graph.Node;
import model.robot.Robot;

import javax.swing.*;
import java.awt.*;

public class DrawingRobot {
    public DrawingRobot(Robot model, Graphics graph) {
        if (graph == null) {
            return;
        }

        Image img = new ImageIcon(model.getURLImage()).getImage();

        Node node = model.getCurrentNode();
        graph.drawImage(img, node.getX() - 10, node.getY() - 5, null); // Position haut gauche
    }
}
