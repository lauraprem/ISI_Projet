package controler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.MainWindow;

public class main {
	
	public static void main(String[] args) {
//		Manager modele = new manager();
		MainWindow vue= new MainWindow(); //(modele)

		vue.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				super.windowClosing(arg0);
				System.exit(0);
			}
		});
		vue.pack();
		vue.setVisible(true);

		MainControler controleurPrincipal = new MainControler(); //(modele, vue);
	}

	
}
