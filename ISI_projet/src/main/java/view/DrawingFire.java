package view;

import model.graph.Node;

import javax.swing.*;
import java.awt.*;


public class DrawingFire {

    public DrawingFire(Node model, int magnificationFactor,Graphics graph) {
        if (graph == null) {
            return;
        }

        String imagePath = getClass().getClassLoader().getResource("pictures/Feu.gif").getFile();
        Image img = new ImageIcon(imagePath).getImage();

        graph.drawImage(img, model.getX() - 10, model.getY() - 13, null);
    }
}
