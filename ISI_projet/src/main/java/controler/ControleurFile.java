package controler;

import model.graph.graph.impl.Graph;
import model.manager.Manager;
import util.FileXML;
import view.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
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
			case "Nouveau graph":
				model.reset();
				break;
			case "Charger graph":
				temp = getFile();
				if(temp != null) f = temp;
				if(f != null) {
					FileXML fileXML = new FileXML();
					Graph newGraph = fileXML.chargerDocument(f);
					if(newGraph != null) {
						model.reset();
						model.setGraph(newGraph);
					}
				}
				break;
			case "Sauvegarder graph":
				if(f == null) {
					temp = getFile();
					if (temp != null) f = temp;
				}
				if(f != null) {
					FileXML fileXML = new FileXML();
					fileXML.sauvegarderDocument(f, model.getGraph());
				}
				break;
			case "Quitter":
				if (model != null) model.exitManager();
				System.exit(0);
				break;
			case "Run":
				if (model != null) model.start();
				break;
			case "Stop":
				if (model != null) model.pauseManager();
				break;
			default:
				break;
		}
	}

	private File getFile() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier graphe (xml)", "xml");
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(true);
		fc.setDragEnabled(true);
		int returnVal = fc.showOpenDialog(vue.getGlassPane());
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}
}
