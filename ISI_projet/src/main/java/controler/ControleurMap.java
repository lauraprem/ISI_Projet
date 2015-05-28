package controler;

import model.graph.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;
import model.manager.Manager;
import model.robot.specialized.RobotAPates;
import model.robot.specialized.RobotChenille;
import model.robot.specialized.RobotToutTerrain;
import view.MainWindow;

public class ControleurMap implements MouseListener {

	private Manager model;
	private MainWindow vue;
	private int typeElement;
	private Node n1;
	private Node n2;

	public ControleurMap(MainWindow vue, Manager manager, int typeElement) {
		this.vue = vue;
		this.model = manager;
		this.typeElement = typeElement;
		n1 = null;
		n2 = null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point point = new Point(e.getPoint());
		System.out.println(point.toString());
		switch (typeElement) {
		case 0: // Ajout RobotToutTerrain
			if (n1 !=null) {
				model.addRobot(new RobotToutTerrain(model.getGraph(), n1, null));
				n1 = null;
				n2 = null;
			}
			break;

		case 1: // Ajout RobotChenille
			if (n1 !=null) {
				model.addRobot(new RobotChenille(model.getGraph(), n1, null));
				n1 = null;
				n2 = null;
			}
			break;

		case 2: // Ajout RobotAPates
			if (n1 !=null) {
				model.addRobot(new RobotAPates(model.getGraph(), n1, null));
				n1 = null;
				n2 = null;
			}
			break;

		case 3: // Ajout d'un feu
			if (n1 !=null) {
				n1.increaseFireLevel(10);
			}
			n1 = null;
			n2 = null;
			break;

		case 4: // Ajout d'un noeud
			model.addNode(new Node("", point));
			n1 = null;
			n2 = null;
			break;

		case 5: // TODO Add edge Innonde on model
			if (n1 !=null && n2 != null) {

				// TODO Calcul distance entre les points
				double valuation = 10;
				model.addEdge(new Edge(n1, n2, valuation, new Ground(GroundType.FLOODED)));

				n1 = null;
				n2 = null;
			}
			break;

		case 6: // TODO Add edge Escarp on model

			if (n1 !=null && n2 != null) {

				// TODO Calcul distance entre les points
				double valuation = 10;
				model.addEdge(new Edge(n1, n2, valuation, new Ground(GroundType.STEEP)));
				n1 = null;
				n2 = null;
			}
			break;

		case 7: // TODO Add edge Plat on model

			if (n1 !=null && n2 != null) {

				// TODO Calcul distance entre les points
				double valuation = 10;
				model.addEdge(new Edge(n1, n2, valuation, new Ground(GroundType.FLAT)));
				n1 = null;
				n2 = null;
			}
			break;

		default:
			break;
		}
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	public int getTypeElement() {
		return typeElement;
	}

	public void setTypeElement(int typeElement) {
		this.typeElement = typeElement;
	}

}
