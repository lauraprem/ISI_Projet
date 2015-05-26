package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.graph.Node;
import model.graph.edge.Edge;
import model.robot.Robot;

public class DrawingRobot{
	
	private Robot model;

	public DrawingRobot(Graphics graph,Node model) {
			if (graph == null) {
				return;
			}
			
			String typePath = "pictures/robotAPattes.gif";
			typePath = "pictures/robotChenilles.gif";
			typePath = "pictures/RobotToutTerrain.gif";

			
			String imagePath = getClass().getClassLoader().getResource(typePath).getFile();
			Image img = new ImageIcon(imagePath).getImage();
			
			graph.drawImage(img, model.x-10, model.y-5, null); // Position haut gauche
		}
		
//		switch (model.g) {
//		case FLAT:
//			graph.setColor(Color.black);
//			break;
//		case STEEP:
//			graph.setColor(Color.orange);
//			break;
//		case FLOODED: //TOTDO mettre innondé dans vue + add star + (stop + Pause)
//			graph.setColor(Color.magenta);
//			break;
//			default:
//				graph.setColor(Color.black);
//				break;
//		}
		
//		String imagePath = getClass().getClassLoader().getResource(typePath).getFile();
//		Image img = new ImageIcon(imagePath).getImage();
//		
//		graph.drawImage(img, 0, 0, null);
//	}
}
