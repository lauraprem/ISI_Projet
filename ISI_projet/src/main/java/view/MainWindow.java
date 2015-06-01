package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import util.Picture;
import view.about.AboutLabel;
import model.manager.Manager;

public class MainWindow extends JFrame {

    private MenuFichier menuFile;
    private Drawing drawing;

    public MainWindow(Manager model) {
        super(AboutLabel.NOM_APPLICATION);

        getContentPane().setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        menuFile = new MenuFichier();
        setJMenuBar(menuFile);

//        drawing = new Drawing(model, Picture.BACKGROUND.getURL(),200);
        drawing = new Drawing(model, Picture.BACKGROUND.getURL(),0);
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
}
