package util;

import model.graph.Node;
import model.graph.Point;
import model.graph.edge.Edge;
import model.graph.graph.IGraph;
import model.graph.graph.impl.Graph;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import util.utilXML.ReaderXML;

import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

public class ReaderXMLTest {
    private Graph graphe;
    private File f;
    private File grosFichier;
    private File fMauvais;
    private File fMauvais2;
    private File fMauvais3;
    private File fMauvais4;
    private File fMauvais5;

    @Before
    public void init() {
        Node.resetIds();
        String fileSeparator = System.getProperty("file.separator");
        f = new File("data" + fileSeparator + "grapheTest.xml");
        grosFichier = new File("data" + fileSeparator + "grosGrapheTest.xml");
        fMauvais = new File("data" + fileSeparator + "grapheMauvaisTest.xml");
        fMauvais3 = new File("data" + fileSeparator + "grapheMauvaisTest3.xml");
        fMauvais4 = new File("data" + fileSeparator + "grapheMauvaisTest2.xml");
        fMauvais5 = new File("data" + fileSeparator + "grapheMauvaisTest4.json");
        graphe = new Graph();
        Node noeud1 = new Node(new Point(1, 0));
        Node noeud2 = new Node(new Point(1, 1));
        Edge arc1 = new Edge(noeud1, noeud2, (double) 10, new Ground(GroundType.FLAT));
        graphe.addEdge(arc1);
        graphe.addNode(noeud1);
        graphe.addNode(noeud2);
    }

    @Test
    public void testChargement() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        Node.resetIds();
        IGraph graphe1 = ReaderXML.getInstance().chargerDocument(f);
        assertEquals(graphe1, graphe);
    }

    @Test
    public void testChargementGrosfichier() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        IGraph graphe2 = ReaderXML.getInstance().chargerDocument(grosFichier);
        assertEquals(graphe2.getAllNodes().size(), 26);
        assertEquals(graphe2.getAllEdges().size(), 39);
    }

    //mauvais typage dans le XML
    @Test(expected = java.lang.reflect.InvocationTargetException.class)
    public void testMauvaisChargement() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        Node.resetIds();
        IGraph graphe3;
        graphe3 = ReaderXML.getInstance().chargerDocument(fMauvais);
        assertEquals(graphe3, graphe);
    }

    //pas d'instannciation de file
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testMauvaisChargement2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        Node.resetIds();
        IGraph graphe4;
        graphe4 = ReaderXML.getInstance().chargerDocument(fMauvais2);
        assertEquals(graphe4, graphe);
    }

    //fichier sans extension
    @Test(expected = java.io.FileNotFoundException.class)
    public void testMauvaisChargement3() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        Node.resetIds();
        IGraph graphe5;
        graphe5 = ReaderXML.getInstance().chargerDocument(fMauvais3);
        assertEquals(graphe5, graphe);
    }

    //fichier sans les elements
    @Test(expected = org.xml.sax.SAXParseException.class)
    public void testMauvaisChargement4() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        Node.resetIds();
        IGraph graphe6;
        graphe6 = ReaderXML.getInstance().chargerDocument(fMauvais4);
        assertEquals(graphe6, graphe);
    }

    //fichier avec la mauvaise extension
    @Test(expected = org.xml.sax.SAXParseException.class)
    public void testMauvaisChargement5() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        Node.resetIds();
        IGraph graphe7;
        graphe7 = ReaderXML.getInstance().chargerDocument(fMauvais5);
        assertEquals(graphe7, graphe);
    }
}
