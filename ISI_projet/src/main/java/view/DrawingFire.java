package view;

import java.awt.Graphics;

import model.graph.IGraph;


public class DrawingFire {
	
	private IGraph model;

	public DrawingFire(IGraph model) {
		super();
		this.model = model;
	}

	public void drawArc(Graphics graph) {
		if (graph == null) {
			return;
		}

		// Dessine l'arc
//		for (Iterator<Segment> it = tortue.getListSegments().iterator(); it
//				.hasNext();) {
//			Segment seg = (Segment) it.next();
//			if (graph == null) {
//				return;
//			}
//			graph.setColor(seg.getColor());
//			graph.drawLine(seg.getPtStart().x, seg.getPtStart().y, seg.getPtEnd().x, seg.getPtEnd().y);
//		}
	}
}
