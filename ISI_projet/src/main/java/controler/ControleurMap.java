package controler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.graph.Node;
import model.graph.edge.Edge;
import model.manager.Manager;
import model.robot.Robot;
import view.MainWindow;

public class ControleurMap implements MouseListener {

	 private Manager model;
	private MainWindow vue;
	private int typeElement;

	public ControleurMap(MainWindow vue, Manager manager, int typeElement) {
		this.vue = vue;
		this.model = manager;
		this.typeElement = typeElement;
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
		Point point = e.getPoint();
		System.out.println(point.toString());
				switch (typeElement) {
				case 0:
					// TODO model.addRobot(new Robot()); Tout_terrain
					break;

				case 1:
					// TODO model.addRobot(new Robot()); Chenille
					break;
					
				case 2:
					// TODO model.addRobot(new Robot()); A_pates
					break;
					
				case 3:
					// TODO dans vue recherche node sélectionné
					// TODO model model.setFire(node)
					break;
					
				case 4:
					model.addNode(new Node("",point));
					break;
					
				case 5:
					// TODO voir selection deux points
					//model.addEdge(new Edge(_v1, _v2));
					//setType Add_Innonde
					break;
					
				case 6:
					// TODO voir selection deux points
					//model.addEdge(new Edge(_v1, _v2));
					//setType Add_arc_Escarp
					break;
					
				case 7:
					// TODO voir selection deux points
					//model.addEdge(new Edge(_v1, _v2));
					//setType Add_arc_Plat
					break;

				default:
					break;
				}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
