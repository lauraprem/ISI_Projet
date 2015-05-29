package view;

import model.graph.Node;
import model.robot.Robot;
import model.robot.specialized.RobotAPates;
import model.robot.specialized.RobotChenille;

import javax.swing.*;
import java.awt.*;

public class DrawingRobot {

    private Robot model;

    public DrawingRobot(Graphics graph, Robot model) {
        if (graph == null) {
            return;
        }

        String typePath = "pictures/RobotToutTerrain.gif";

        if (model instanceof RobotAPates) {
            typePath = "pictures/robotAPattes.gif";
        } else if (model instanceof RobotChenille) {
            typePath = "pictures/robotChenilles.gif";
        }

        String imagePath = getClass().getClassLoader().getResource(typePath).getFile();
        Image img = new ImageIcon(imagePath).getImage();

        Node node = model.getCurrentNode();
        graph.drawImage(img, node.getX() - 10, node.getY() - 5, null); // Position haut gauche
    }
}
