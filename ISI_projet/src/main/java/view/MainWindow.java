package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.manager.Manager;
public class MainWindow extends JFrame {

	private MenuFichier menuFile;
	private MenuHaut menuHaut;
	private Drawing drawing;

	public MainWindow(Manager model) {
		super("Robocup Rescue");

		getContentPane().setLayout(new BorderLayout(10, 10));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		menuFile = new MenuFichier();
		setJMenuBar(menuFile);
		
//		menuHaut = new MenuHaut();
//		getContentPane().add(menuHaut, "North");
		String imagePath = getClass().getClassLoader().getResource("pictures/mapsixieme.jpg").getFile();
		drawing = new Drawing(model,imagePath);
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
		return drawing;
	}

	public void setDessin(Drawing drawing) {
		this.drawing = drawing;
	}
}
