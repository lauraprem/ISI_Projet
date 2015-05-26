package view;

import java.awt.Graphics;

<<<<<<< HEAD
import model.graph.IGraph;
=======
import model.graph.graph.IUndirectedGraph;
>>>>>>> 8f5ec17f4a30802e1fa1163754219a4e8a4b2782


public class DrawingFire {
	
<<<<<<< HEAD
	private IGraph model;

	public DrawingFire(IGraph model) {
=======
	private IUndirectedGraph model;

	public DrawingFire(IUndirectedGraph model) {
>>>>>>> 8f5ec17f4a30802e1fa1163754219a4e8a4b2782
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
