package view;

import model.Manager;
import util.Picture;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private MenuFichier menuFile;
    private Drawing drawing;
    private Manager model;

    public MainWindow(Manager model) {
        super(MenuLabel.NOM_APPLICATION);

        getContentPane().setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        menuFile = new MenuFichier();
        setJMenuBar(menuFile);

//        drawing = new Drawing(model, Picture.BACKGROUND.getURL(),200);
        drawing = new Drawing(model, Picture.BACKGROUND.getURL(), 0);
        getContentPane().add(drawing, BorderLayout.CENTER);

        this.setResizable(false);
    }

    public MenuFichier getMenuFile() {
        return menuFile;
    }

    public void setMenuFile(MenuFichier menuFile) {
        this.menuFile = menuFile;
    }

    public Drawing getDessin() {
        return drawing;
    }

    public void setDessin(Drawing drawing) {
        this.drawing = drawing;
    }

	public Manager getModel() {
		return model;
	}

	public void setModel(Manager model) {
		this.model = model;
	}
}
