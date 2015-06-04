package view;

import model.graph.Node;
import model.graph.Point;
import model.robot.Robot;

import javax.swing.*;
import java.awt.*;

public class DrawingRobot {
    public DrawingRobot(Robot model, Graphics graph) {
        if (graph == null) {
            return;
        }

        Image img = new ImageIcon(model.getURLImage()).getImage();

        Point position = model.getPosition();
        graph.drawImage(img, position.getX() - 10, position.getY() - 5, null); // Position haut gauche
    }
}
