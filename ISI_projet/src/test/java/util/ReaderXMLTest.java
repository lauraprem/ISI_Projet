package util;

import static org.junit.Assert.assertEquals;

import java.io.File;

import model.graph.Node;
import model.graph.Point;
import model.graph.edge.Edge;
import model.graph.graph.impl.Graph;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;

import org.junit.Before;
import org.junit.Test;

public class ReaderXMLTest {
	private Graph graphe;
	private File f;
	private File grosFichier;
	@Before
	public void init()
	{
		Node.resetIds();
		String fileSeparator=System.getProperty("file.separator");
		f=new File("data"+fileSeparator+"grapheTest.xml");
		grosFichier=new File("data"+fileSeparator+"grosGrapheTest.xml");
		graphe=new Graph();
		Node noeud1=new Node(new String("noeud1"),new Point(1,0));
		Node noeud2=new Node(new String("noeud2"),new Point(1,1));
		Edge arc1=new Edge(noeud1, noeud2,(double)10,new Ground(GroundType.FLAT));
		graphe.addEdge(arc1);
		graphe.addNode(noeud1);
		graphe.addNode(noeud2);
	}
	@Test
	public void testChargement()
	{
		Node.resetIds();
		Graph graphe1=ReaderXML.getInstance().chargerDocument(f);
		assertEquals(graphe1,graphe);
	}
	@Test
	public void testChargementGrosfichier()
	{
		Graph graphe2=ReaderXML.getInstance().chargerDocument(grosFichier);
		assertEquals(graphe2.getAllNodes().size(),26);
		assertEquals(graphe2.getAllEdges().size(),39);
	}
}
