package controler;

import model.Manager;
import model.graph.*;
import model.pathSearch.impl.Djikstra;
import model.robot.specialized.RobotAPates;
import model.robot.specialized.RobotChenille;
import model.robot.specialized.RobotToutTerrain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.PointUtil;
import view.MenuLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControlerClic implements MouseListener {
    private final static Logger logger = LogManager.getLogger();
    private Manager model;
    private String typeElement;
    private Node n1;
    private Node n2;

    public ControlerClic(Manager manager) {
        this.model = manager;
        this.typeElement = "";
        resetCurrentNodes();
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = new Point(e.getPoint());

        // Recherche du noeud selectionne
        if (model.getGraph().getAllNodes().size() > 0)
            for (int i = 0; i < model.getGraph().getAllNodes().size(); i++) {
                if (point.getX() >= model.getGraph().getAllNodes().get(i)
                        .getX() - 5
                        && point.getX() <= model.getGraph().getAllNodes()
                        .get(i).getX() + 5
                        && point.getY() >= model.getGraph().getAllNodes()
                        .get(i).getY() - 5
                        && point.getY() <= model.getGraph().getAllNodes()
                        .get(i).getY() + 5) {
                    if (n1 == null) {
                        n1 = model.getGraph().getAllNodes().get(i);
                    } else {
                        if (n1.getId() != model.getGraph().getAllNodes().get(i).getId())
                            n2 = model.getGraph().getAllNodes().get(i);
                    }
                    break;
                }
            }

        logger.trace(String.format("Click on \"%s\"", point.toString()));
        switch (typeElement) {
            case MenuLabel.ADD_TOUT_TERRAIN_ROBOT:
                if (n1 != null) {
                    model.addRobot(new RobotToutTerrain(model.getGraph(), n1,
                            new Djikstra(), model));
                    resetCurrentNodes();
                }
                break;

            case MenuLabel.ADD_CHENILLE_ROBOT:
                if (n1 != null) {
                    model.addRobot(new RobotChenille(model.getGraph(), n1,
                            new Djikstra(), model));
                    resetCurrentNodes();
                }
                break;

            case MenuLabel.ADD_A_PATE_ROBOT:
                if (n1 != null) {
                    model.addRobot(new RobotAPates(model.getGraph(), n1,
                            new Djikstra(), model));
                    resetCurrentNodes();
                }
                break;

            case MenuLabel.ADD_FIRE:
                if (n1 != null) {
                    model.increaseFireLevel(n1);
                }
                resetCurrentNodes();
                break;

            case MenuLabel.ADD_NODE:
                Node node = new Node(point);
                model.addNode(node);
                resetCurrentNodes();
                break;

            case MenuLabel.ADD_FLOODED_EDGE:
                if (n1 != null && n2 != null) {
                    model.addEdge(new Edge(n1, n2, PointUtil.getDistance(n1, n2),
                            new Ground(GroundType.FLOODED)));

                    resetCurrentNodes();
                }
                break;

            case MenuLabel.ADD_STEEP_EDGE:

                if (n1 != null && n2 != null) {
                    model.addEdge(new Edge(n1, n2, PointUtil.getDistance(n1, n2),
                            new Ground(GroundType.STEEP)));
                    resetCurrentNodes();
                }
                break;

            case MenuLabel.ADD_FLAT_EDGE:

                if (n1 != null && n2 != null) {
                    model.addEdge(new Edge(n1, n2, PointUtil.getDistance(n1, n2),
                            new Ground(GroundType.FLAT)));
                    resetCurrentNodes();
                }
                break;

            default:
                break;
        }
    }

    private void resetCurrentNodes() {
        n1 = null;
        n2 = null;
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    public String getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(String typeElement) {
        this.typeElement = typeElement;
    }

}
