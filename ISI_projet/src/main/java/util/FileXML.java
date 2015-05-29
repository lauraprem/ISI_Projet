package util;

import model.graph.Node;
import model.graph.Point;
import model.graph.edge.Edge;
import model.graph.graph.impl.Graph;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;

import java.io.File;

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
     * constructeur priv�e(toute les m�thodes de la classes sont static
     */
    public FileXML() {

    }

    public static void sauvegarderDocument(File f, Graph graphe) {
        WriterXML.getInstance().sauvegarderDocument(f, graphe);
    }

    public static Graph chargerDocument(File f) {
        return ReaderXML.getInstance().chargerDocument(f);
    }

    public static void main(String[] args) {
        String fileSeparator = System.getProperty("file.separator");
        Graph graphe = new Graph();
        Node noeud1 = new Node(new String("noeud1"), new Point(1, 0));
        Node noeud2 = new Node(new String("noeud2"), new Point(1, 1));
        Edge arc1 = new Edge(noeud1, noeud2, (double) 10, new Ground(GroundType.FLAT));
        graphe.addEdge(arc1);
        graphe.addNode(noeud1);
        graphe.addNode(noeud2);
        System.out.println("Source: " + arc1.getSource());
        System.out.println("Destination: " + arc1.getDestination());
        System.out.println("Type: " + arc1.getGround().getType());
        System.out.println("Valuation: " + arc1.getLength());
        File file = new File("data" + fileSeparator + "graphe.xml");
        FileXML.sauvegarderDocument(file, graphe);
        FileXML.chargerDocument(file);
    }
}
