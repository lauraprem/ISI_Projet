package controler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.manager.Manager;
import view.MainWindow;

public class main {
	
	public static void main(String[] args) {
//		Manager modele = new Manager((new IU ,null);
		Manager modele = null;
		MainWindow vue= new MainWindow();

		vue.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				super.windowClosing(arg0);
				System.exit(0);
			}
		});
		vue.pack();
		vue.setLocationRelativeTo(null);
		vue.setVisible(true);

		MainControler MainPrincipal = new MainControler(vue,modele);
	}

	
}
