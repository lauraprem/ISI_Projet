package model;

import view.Observer;


/**
 *
 * @author Corinne
 */

public interface Observable {
	public void AjoutObservateur(Observer o);

	public void SupprimerObservateur(Observer o);

	// Dès raffraichissement.
	public void NotifierObservateur();
}
