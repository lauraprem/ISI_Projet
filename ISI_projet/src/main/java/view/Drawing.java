package view;
// package logo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 * @author J. Ferber
 * @version 2.0
 */

public class Drawing extends JPanel implements Observer {
	
	public Drawing(String img) { //(modele)
		//modele.AjoutObservateur(this);
//		ImageIcon img = new ImageIcon("images/background.png").getImage());
//		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
//	    setPreferredSize(size);
//	    setMinimumSize(size);
//	    setMaximumSize(size);
//	    setSize(size);
		
		this.setBackground(Color.white);
		this.setSize(new Dimension(600, 400));
		this.setPreferredSize(new Dimension(600, 400));
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Color c = g.getColor();
		
		Dimension dim = getSize();
		g.setColor(Color.white);
		g.fillRect(0,0,dim.width, dim.height);
		g.setColor(c); 
	}

	public void Update() {
		repaint();
	}
}
