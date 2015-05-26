package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
public class MainWindow extends JFrame {

	private MenuFichier menuFile;
	private MenuHaut menuHaut;
	private Drawing dessin;

	public MainWindow() { //(Manager modele)
		super("Robocup Rescue");

		getContentPane().setLayout(new BorderLayout(10, 10));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		menuFile = new MenuFichier();
		setJMenuBar(menuFile);
		
//		menuHaut = new MenuHaut();
//		getContentPane().add(menuHaut, "North");
<<<<<<< HEAD
		
		Drawing drawing = new Drawing("src/main/resources/pictures/mapsixieme.jpg"); //(modele);
=======
		String imagePath = getClass().getClassLoader().getResource("pictures/mapsixieme.jpg").getFile();
		Drawing drawing = new Drawing(imagePath); //(modele);
>>>>>>> 8f5ec17f4a30802e1fa1163754219a4e8a4b2782
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

	public Drawing getDessin() {
		return dessin;
	}

	public void setDessin(Drawing dessin) {
		this.dessin = dessin;
	}
}
