package controler;

import model.Manager;
import view.MainWindow;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        Manager modele = new Manager();
        MainWindow vue = new MainWindow(modele);

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

        MainControler MainPrincipal = new MainControler(vue, modele);
    }


}