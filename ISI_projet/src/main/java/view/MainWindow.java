package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
public class MainWindow extends JFrame {

	private MenuFichier menuFile;
	private MenuHaut menuHaut;

	public MainWindow() { //(Manager modele)
		super("Robocup Rescue");

		getContentPane().setLayout(new BorderLayout(10, 10));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		menuFile = new MenuFichier();
		setJMenuBar(menuFile);
		
//		menuHaut = new MenuHaut();
//		getContentPane().add(menuHaut, "North");
		String imagePath = getClass().getClassLoader().getResource("pictures/mapsixieme.jpg").getFile();
		Drawing drawing = new Drawing(imagePath); //(modele);
		getContentPane().add(drawing, "Center");
		
		this.setResizable(false);
	}

	public MenuFichier getMenuFile() {
		return menuFile;
	}

	public void setMenuFile(MenuFichier menuFile) {
		this.menuFile = menuFile;
	}

	public MenuHaut getMenuHaut() {
		return menuHaut;
	}

	public void setMenuHaut(MenuHaut menuHaut) {
		this.menuHaut = menuHaut;
	}
}
