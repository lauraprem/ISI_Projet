package util;

import model.graph.graph.IGraph;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * classe permettant de sauvegarder
 * un graphe dans un fichier XML ou de charger un graphe
 * � partir d'un fichier XML
 *
 * @author gael, corinne, alexandre, laura
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
    private FileXML() {

    }

    /**
     * fonction d'appel pour sauvegarde du document
     *
     * @param f      fichier permettant de sauvegarder le graphe
     * @param graphe g�n�rer par le programme
     */
    public static void sauvegarderDocument(File f, IGraph graphe) {
        WriterXML.getInstance().sauvegarderDocument(f, graphe);
    }

    /**
     * fonction d'appel pour chargement du graphe
     *
     * @param f fichier permettant de charger le graphe
     * @return graphe charger � partir du fichier
     * @throws InvocationTargetException    exception de ciblage
     * @throws IllegalArgumentException     mauvais argument
     * @throws IllegalAccessException       probl�me d'acc�s de reflexivit�
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static IGraph chargerDocument(File f) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        return ReaderXML.getInstance().chargerDocument(f);
    }
}
