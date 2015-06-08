package util;

import model.graph.Node;
import model.graph.Point;
<<<<<<< Updated upstream
import model.graph.edge.Edge;
import model.graph.graph.IGraph;
import model.graph.graph.impl.Graph;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;

=======
import model.graph.Edge;
import model.graph.IGraph;
import model.graph.impl.Graph;
import model.graph.Ground;
import model.graph.GroundType;
>>>>>>> Stashed changes
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import util.xml.FileXML;

import util.utilXML.FileXML;

import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

public class FileXMLTest {
    private final static Logger logger = LogManager.getLogger();
    private Graph graphe;
    private Node noeud1;
    private Node noeud2;
    private Edge arc1;
    private String fileSeparator = System.getProperty("file.separator");

    @Before
    public void init() {
        graphe = new Graph();
        noeud1 = new Node(new Point(1, 0));
        noeud2 = new Node(new Point(1, 1));
        arc1 = new Edge(noeud1, noeud2, (double) 10, new Ground(GroundType.FLAT));
        graphe.addEdge(arc1);
        graphe.addNode(noeud1);
        graphe.addNode(noeud2);
        logger.info("Source: " + arc1.getSource());
        logger.info("Destination: " + arc1.getDestination());
        logger.info("Type: " + arc1.getGround().getType());
        logger.info("Valuation: " + arc1.getLength());
    }

    @Test
    public void test1BoucleReadWrite() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        File file = new File("data" + fileSeparator + "graphe.xml");
        FileXML.sauvegarderDocument(file, graphe);
        IGraph graphe1 = FileXML.chargerDocument(file);
        assertEquals(graphe, graphe1);
    }

    @Test
    public void test2BoucleReadWrite() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        File file = new File("data" + fileSeparator + "graphe.xml");
        FileXML.sauvegarderDocument(file, graphe);
        IGraph graphe1 = FileXML.chargerDocument(file);
        File file2 = new File("data" + fileSeparator + "graphe2.xml");
        FileXML.sauvegarderDocument(file2, graphe);
        IGraph graphe2 = FileXML.chargerDocument(file2);
        assertEquals(graphe1, graphe2);
    }
}
