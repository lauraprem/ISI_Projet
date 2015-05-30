package util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.graph.Node;
import model.graph.Point;
import model.graph.edge.Edge;
import model.graph.graph.impl.Graph;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;

import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class WriterXMLTest extends XMLTestCase{
	private Graph graphe;
	private File f;
	private File f2;
	public void init()
	{
		String fileSeparator=System.getProperty("file.separator");
		f=new File("data"+fileSeparator+"grapheTest.xml");
		f2=new File("data"+fileSeparator+"grapheTest2.xml");
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
		try {
		init();//XMLUnit ne prenant pas le @Before je l'appel directement
		WriterXML.getInstance().sauvegarderDocument(f2, graphe);
		DocumentBuilderFactory domFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = domFact.newDocumentBuilder();
		Document doc = builder.parse(f);
		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		
		transformer.transform(domSource, result);
		
		Document doc1 = builder.parse(f2);
		domSource = new DOMSource(doc1);
		StringWriter writer1 = new StringWriter();
		result = new StreamResult(writer1);
		tf = TransformerFactory.newInstance();
		transformer = tf.newTransformer();
		
		transformer.transform(domSource, result);
		
		XMLUnit.setIgnoreWhitespace(true); // ignore les espace du document

	    //permet de tester les deux document en String
	    XMLTestCase.assertEquals(writer.toString(),writer1.toString());  // assertXMLEquals venant de XMLTestCase
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
