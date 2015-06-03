package view.about;

import javax.swing.*;
import java.awt.*;

public class AboutWindow extends JFrame {

    public AboutWindow() {
        super("A propos de " + AboutLabel.NOM_APPLICATION);
        Dimension size = new Dimension(300, 200);
        this.setPreferredSize(size);
        this.getContentPane().setLayout(new BorderLayout(10, 10));

        JLabel labelTitle = new JLabel(AboutLabel.NOM_APPLICATION + " " + AboutLabel.VERSION, 0);
        labelTitle.setFont(new Font("Verdana", 1, 20));
        this.add(labelTitle, BorderLayout.NORTH);

        JLabel labelDesrcDuties = new JLabel(AboutLabel.HTLM + AboutLabel.DESCRIPTION_LABEL + "<br>" + AboutLabel.DUTIES + "." + AboutLabel.HTLM_CLOSE);
        labelDesrcDuties.setFont(new Font("Verdana", 1, 12));
        this.add(labelDesrcDuties, BorderLayout.CENTER);

        JLabel labelAutor = new JLabel(AboutLabel.HTLM + AboutLabel.AUTOR + AboutLabel.HTLM_CLOSE);
        labelAutor.setFont(new Font("Verdana", 1, 12));
        this.add(labelAutor, BorderLayout.SOUTH);

//        panelAbout = new JPanelAbout();
//        this.add(panelAbout);
//        JTextArea(Test, int l, int c);
//        getContentPane().setLayout(new BorderLayout(10, 10));
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
    }
}
