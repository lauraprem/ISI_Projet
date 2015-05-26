package controler;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.MainWindow;

public class ControleurMap implements MouseListener {

	// private EnvironnementTortue model; //TODO model
	private MainWindow vue;
	private int typeElement;

	public ControleurMap(MainWindow vue, int typeElement) { // TODO model
	// this.model = model; //TODO model
		this.vue = vue;
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
		Point p = e.getPoint();
		System.out.println(e.getPoint().toString());
				switch (typeElement) {
				case 0:
					// TODO model
					break;

				case 1:
					// TODO model
					break;
					
				case 2:
					// TODO model
					break;
					
				case 3:
					// TODO model
					break;
					
				case 4:
					// TODO model
					break;
					
				case 5:
					// TODO model
					break;
					
				case 6:
					// TODO model
					break;
					
				case 7:
					// TODO model
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
