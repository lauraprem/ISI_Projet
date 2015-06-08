package view.help;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import view.MenuLabel;

public class HelpWindow extends JFrame {

	public HelpWindow() {
		super("Help de " + MenuLabel.NOM_APPLICATION);
		Dimension size = new Dimension(300, 200);
		this.setPreferredSize(size);
		this.getContentPane().setLayout(new BorderLayout(10, 10));

		JLabel labelTitle = new JLabel(MenuLabel.NOM_APPLICATION + " aide", 0);
		labelTitle.setFont(new Font("Verdana", 1, 20));
		this.add(labelTitle, BorderLayout.NORTH);

		JLabel labelDesrcDuties = new JLabel(HelpLabel.HTLM
				+ HelpLabel.INDICATION + "<br>"
				+ HelpLabel.HTLM_CLOSE);
		labelDesrcDuties.setFont(new Font("Verdana", 1, 12));
		this.add(labelDesrcDuties, BorderLayout.CENTER);
		this.setResizable(false);
	}
}
