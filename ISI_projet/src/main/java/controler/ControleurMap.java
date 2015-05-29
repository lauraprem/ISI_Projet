package controler;

import model.graph.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;
import model.manager.Manager;
import model.robot.specialized.RobotAPates;
import model.robot.specialized.RobotChenille;
import model.robot.specialized.RobotToutTerrain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.MainWindow;

public class ControleurMap implements MouseListener {
	private final static Logger logger = LogManager.getLogger();
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

		// Recherche du noeud selectionne
		if (model.getGraph().getAllNodes().size() > 0)
			for (int i = 0; i < model.getGraph().getAllNodes().size(); i++) {
				if (point.getX() >= model.getGraph().getAllNodes().get(i).getX() - 5
						&& point.getX() <= model.getGraph().getAllNodes().get(i).getX() + 5
						&& point.getY() >= model.getGraph().getAllNodes().get(i).getY() - 5
						&& point.getY() <= model.getGraph().getAllNodes().get(i).getY() + 5) {
					if (n1 == null) {
						n1 = model.getGraph().getAllNodes().get(i);
					} else {
						n2 = model.getGraph().getAllNodes().get(i);
					}
					break;
				}
			}

		logger.trace(String.format("Click on \"%s\"", point.toString()));
		switch (typeElement) {
		case 0: // Add RobotToutTerrain
			if (n1 != null) {
				model.addRobot(new RobotToutTerrain(model.getGraph(), n1, null));
				n1 = null;
				n2 = null;
			}
			break;

		case 1: // Add RobotChenille
			if (n1 != null) {
				model.addRobot(new RobotChenille(model.getGraph(), n1, null));
				n1 = null;
				n2 = null;
			}
			break;

		case 2: // Add RobotAPates
			if (n1 != null) {
				model.addRobot(new RobotAPates(model.getGraph(), n1, null));
				n1 = null;
				n2 = null;
			}
			break;

		case 3: // Add d'un feu
			if (n1 != null) {
				n1.increaseFireLevel(10);
			}
			n1 = null;
			n2 = null;
			break;

		case 4: // Add Node
			model.addNode(new Node("", point));
			n1 = null;
			n2 = null;
			break;

		case 5: // Add edge Innonde
			if (n1 != null && n2 != null) {
				// Double value = PointUtil.getDistance(n1, n2);
				model.addEdge(new Edge(n1, n2, 10.0, new Ground(
						GroundType.FLOODED)));

				n1 = null;
				n2 = null;
			}
			break;

		case 6: // Add edge Escarp

			if (n1 != null && n2 != null) {
				model.addEdge(new Edge(n1, n2, 10.0, new Ground(
						GroundType.STEEP)));
				n1 = null;
				n2 = null;
			}
			break;

		case 7: // Add edge Plat

			if (n1 != null && n2 != null) {
				model.addEdge(new Edge(n1, n2, 10.0,
						new Ground(GroundType.FLAT)));
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