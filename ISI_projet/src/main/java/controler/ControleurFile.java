package controler;

import model.graph.graph.impl.Graph;
import model.manager.Manager;
import util.FileXML;
import view.MainWindow;
import view.MenuLabel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ControleurFile implements ActionListener {

	private Manager model;
	private MainWindow vue;
	private File f;

	public ControleurFile(MainWindow vue,Manager model) {
	 this.model = model;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File temp;
		switch (e.getActionCommand()) {
			case MenuLabel.NEW:
				model.reset();
				break;
			case MenuLabel.LOAD:
				temp = getFile(MenuLabel.LOAD_FR);
				if(temp != null) f = temp;
				if(f != null) {
					Graph newGraph = null;
					try{
						newGraph = FileXML.chargerDocument(f);
					}catch(Exception ex){
						// TODO Fenetre erreur => XML non valide
					}
					if(newGraph != null) {
						model.reset();
						model.setGraph(newGraph);
						model.pauseManager();
					}
				}
				break;
			case MenuLabel.SAVE:
				if(f == null) {
					temp = getFile(MenuLabel.SAVE_FR);
					if (temp != null) f = temp;
				}
				if(f != null) {
					FileXML.sauvegarderDocument(f, model.getGraph());
				}
				break;
			case MenuLabel.SAVE_AS:
				temp = getFile(MenuLabel.SAVE_AS_FR);
				if (temp != null) f = temp;
				if (f != null) {
					FileXML.sauvegarderDocument(f, model.getGraph());
				}
				break;
			case MenuLabel.QUIT:
				if (model != null) model.exitManager();
				System.exit(0);
				break;
			case MenuLabel.RUN:
				if (model != null) {
					if(!model.isAlive()) {
						model.unPauseManager();
						model.start();
					}
					else if(model.isPaused()) model.unPauseManager();
				}
				break;
			case MenuLabel.STOP:
				if (model != null) model.pauseManager();
				break;
			default:
				break;
		}
	}

	private File getFile(String stringButton) {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier graphe (xml)", "xml");
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(true);
		fc.setDragEnabled(true);
		File f = new File("data");
		if(!f.exists()) f.mkdir();
		fc.setCurrentDirectory(f);
		int returnVal = fc.showDialog(vue.getGlassPane(),stringButton);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}
}
