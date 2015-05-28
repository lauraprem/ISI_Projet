package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.graph.Node;


public class DrawingFire {

	public DrawingFire(Graphics graph,Node model) {
		if (graph == null) {
			return;
		}
		
		String imagePath = getClass().getClassLoader().getResource("pictures/Feu.gif").getFile();
		Image img = new ImageIcon(imagePath).getImage();
		
		graph.drawImage(img, model.getX()-10, model.getY()-13, null);
	}
}
