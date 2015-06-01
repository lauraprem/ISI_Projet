package view;

import model.graph.Node;
import util.Picture;

import javax.swing.*;
import java.awt.*;


public class DrawingFire {

    public DrawingFire(Node model, int magnificationFactor,Graphics graph) {
        if (graph == null) {
            return;
        }

        Image img = new ImageIcon(Picture.FIRE.getURL()).getImage();

        graph.drawImage(img, model.getX() - 15, model.getY() - 20, null);
    }
}
