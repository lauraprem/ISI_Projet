package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

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
        
        String imagePath = getClass().getClassLoader().getResource("pictures/mapsixieme.jpg").getFile();
//        drawing = new Drawing(model, imagePath);
        drawing = new Drawing(model, imagePath,200);
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
