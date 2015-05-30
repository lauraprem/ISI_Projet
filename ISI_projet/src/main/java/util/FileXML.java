package util;

import java.io.File;

import model.graph.graph.impl.Graph;
/**
 * classe permettant de sauvegarder
 * un graphe dans un fichier XML ou de charger un graphe
 * � partir d'un fichier XML
 * @author gael,corinne,alexandre,laura
 *
 */
public class FileXML {


//	<osm>
//	<node id="14" x="227.0" y="105.0" type="NORMAL" />
//	<node id="11" x="189.0" y="184.0" type="NORMAL" />
//	<node id="3" x="103.0" y="278.0" type="INCENDIE" />
//	...
//	<edge nd1="2" nd2="20" type="ESCARPE" />
//	<edge nd1="15" nd2="14" type="PLAT" />
//	<edge nd1="16" nd2="17" type="PLAT" />
//	...
//	</osm>
	/**
	 * constructeur priv�e(toutes les m�thodes de la classes sont static)
	 */
	private FileXML()
	{
		
	}
	/**
	 * 
	 * @param f fichier permettant de sauvegarder le graphe
	 * @param graphe g�n�rer par le programme
	 */
	public static void sauvegarderDocument(File f,Graph graphe)
	{
		WriterXML.getInstance().sauvegarderDocument(f, graphe);
	}
	/**
	 * 
	 * @param f fichier permettant de charger le graphe
	 * @return graphe charger � partir du fichier
	 */
	public static Graph chargerDocument(File f)
	{
		return ReaderXML.getInstance().chargerDocument(f);
	}
}
