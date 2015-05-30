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

public class FileXMLTest {
	private Graph graphe;
	private Node noeud1;
	private Node noeud2;
	private Edge arc1;
	private String fileSeparator=System.getProperty("file.separator");
	@Before
	public void init()
	{
		graphe=new Graph();
		noeud1=new Node(new String("noeud1"),new Point(1,0));
		noeud2=new Node(new String("noeud2"),new Point(1,1));
		arc1=new Edge(noeud1, noeud2,(double)10,new Ground(GroundType.FLAT));
		graphe.addEdge(arc1);
		graphe.addNode(noeud1);
		graphe.addNode(noeud2);
		System.out.println("Source: "+arc1.getSource());
		System.out.println("Destination: "+arc1.getDestination());
		System.out.println("Type: "+arc1.getGround().getType());
		System.out.println("Valuation: "+arc1.getLength());
	}
	@Test
	public void test1BoucleReadWrite()
	{
		File file = new File("data"+fileSeparator+"graphe.xml");
		FileXML.sauvegarderDocument(file,graphe);
		Graph graphe1=FileXML.chargerDocument(file);
		assertEquals(graphe,graphe1);
	}
	@Test
	public void test2BoucleReadWrite()
	{
		File file = new File("data"+fileSeparator+"graphe.xml");
		FileXML.sauvegarderDocument(file,graphe);
		Graph graphe1=FileXML.chargerDocument(file);
		File file2 = new File("data"+fileSeparator+"graphe2.xml");
		FileXML.sauvegarderDocument(file2,graphe);
		Graph graphe2=FileXML.chargerDocument(file2);
		assertEquals(graphe1,graphe2);
	}
}
