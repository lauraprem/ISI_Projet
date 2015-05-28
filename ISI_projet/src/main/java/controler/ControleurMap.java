package controler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.graph.Node;
import model.graph.edge.Edge;
import model.manager.Manager;
import model.robot.Robot;
import model.robot.specialized.RobotAPates;
import model.robot.specialized.RobotChenille;
import model.robot.specialized.RobotToutTerrain;
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
					//chercher du noeud du robot
					if(model.getGraph().getAllNodes().size()>0)
					for (int i = 0; i < model.getGraph().getAllNodes().size(); i++) {
						if(point.x >= model.getGraph().getAllNodes().get(i).x-5 &&
								point.x <= model.getGraph().getAllNodes().get(i).x+5 &&
								point.y >= model.getGraph().getAllNodes().get(i).y-5 &&
								point.y <= model.getGraph().getAllNodes().get(i).y+5){
							model.addRobot(new RobotToutTerrain(model.getGraph(),model.getGraph().getAllNodes().get(i),null));
							break;
						}
					}
					break;

				case 1:
					//chercher du noeud du robot
					if(model.getGraph().getAllNodes().size()>0)
					for (int i = 0; i < model.getGraph().getAllNodes().size(); i++) {
						if(point.x >= model.getGraph().getAllNodes().get(i).x-5 &&
								point.x <= model.getGraph().getAllNodes().get(i).x+5 &&
								point.y >= model.getGraph().getAllNodes().get(i).y-5 &&
								point.y <= model.getGraph().getAllNodes().get(i).y+5){
							model.addRobot(new RobotChenille(model.getGraph(),model.getGraph().getAllNodes().get(i),null));
							break;
						}
					}
					break;
					
				case 2:
					//chercher du noeud du robot
					if(model.getGraph().getAllNodes().size()>0)
					for (int i = 0; i < model.getGraph().getAllNodes().size(); i++) {
						if(point.x >= model.getGraph().getAllNodes().get(i).x-5 &&
								point.x <= model.getGraph().getAllNodes().get(i).x+5 &&
								point.y >= model.getGraph().getAllNodes().get(i).y-5 &&
								point.y <= model.getGraph().getAllNodes().get(i).y+5){
							model.addRobot(new RobotAPates(model.getGraph(),model.getGraph().getAllNodes().get(i),null));
							break;
						}
					}
					break;
					
				case 3:
					for (int i = 0; i < model.getGraph().getAllNodes().size(); i++) {
						if(point.x >= model.getGraph().getAllNodes().get(i).x-5 &&
								point.x <= model.getGraph().getAllNodes().get(i).x+5 &&
								point.y >= model.getGraph().getAllNodes().get(i).y-5 &&
								point.y <= model.getGraph().getAllNodes().get(i).y+5){
							model.getGraph().getAllNodes().get(i).increaseFireLevel(10); // mettre observer sur Robot
							break;
						}
					}
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

	public int getTypeElement() {
		return typeElement;
	}

	public void setTypeElement(int typeElement) {
		this.typeElement = typeElement;
	}
	
	
}
