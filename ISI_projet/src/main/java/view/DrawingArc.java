package view;

import java.awt.Color;
import java.awt.Graphics;

import model.graph.edge.Edge;
import model.graph.ground.GroundType;

public class DrawingArc{

	public DrawingArc(Edge model, Graphics graph) {
		if (graph == null) {
			return;
		}
		
		switch (model.getGround().getType()) {
		case FLAT:
			graph.setColor(Color.black);
			break;
		case STEEP:
			graph.setColor(Color.orange);
			break;
		case FLOODED: //TOTDO mettre innondé dans vue + addLast star + (stop + Pause)
			graph.setColor(Color.magenta);
			break;
			default:
				graph.setColor(Color.black);
				break;
		}
		graph.drawLine((int)model.getSource().getX(), (int)model.getSource().getY(),(int)model.getDestination().getX(), (int)model.getDestination().getY());
	}
}
