package util;

import java.awt.Point;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

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
	public FileXML(File f,OSM graphe)
	{
		sauvegarderDocument(f, graphe);
	}
	public FileXML(File f)
	{
		chargerDocument(f);
	}
	public void chargerDocument(File documentToRead)
	{
		try {
			 
			File fXmlFile =documentToRead;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
		 
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		 
			NodeList nList = doc.getElementsByTagName("node");
		 
			System.out.println("----------------------------");
			listerElement(nList);
			nList = doc.getElementsByTagName("edge");
			 
			System.out.println("----------------------------");
			listerElement(nList);
			
		    } catch (Exception e) {
			e.printStackTrace();
		    }
	}
	public void listerElement(NodeList nList)
	{
		for (int temp = 0; temp < nList.getLength(); temp++) {
			 
			org.w3c.dom.Node nNode = nList.item(temp);
	 
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
	 
			if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
				NamedNodeMap noms=nList.item(temp).getAttributes();
				System.out.println("id : " + eElement.getAttribute(noms.item(0).getNodeName()));
				System.out.println("x  : " + eElement.getAttribute(noms.item(1).getNodeName()));
				System.out.println("y  : " + eElement.getAttribute(noms.item(2).getNodeName()));
				System.out.println("type : " + eElement.getAttribute(noms.item(3).getNodeName()));
	 
			}
		}
	}
	public void sauvegarderDocument(File f,OSM graphe)
	{
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("osm");
			doc.appendChild(rootElement);
			// node elements
			Element node = doc.createElement("node");
			rootElement.appendChild(node);
			// set attribute to node element
			Attr attr = doc.createAttribute("id");
			attr.setValue("1");
			node.setAttributeNode(attr);
			// set attribute to node element
			attr = doc.createAttribute("x");
			attr.setValue("1");
			node.setAttributeNode(attr);
			// set attribute to node element
			attr = doc.createAttribute("y");
			attr.setValue("1");
			node.setAttributeNode(attr);
			// set attribute to node element
			attr = doc.createAttribute("type");
			attr.setValue("NORMAL");
			node.setAttributeNode(attr);
			// firstname elements
			Element edge = doc.createElement("edge");
			rootElement.appendChild(edge);
			//set attribute to edge element
			attr = doc.createAttribute("nd1");
			attr.setValue("1");
			edge.setAttributeNode(attr);
			//set attribute to edge element
			attr = doc.createAttribute("nd2");
			attr.setValue("2");
			edge.setAttributeNode(attr);
			// set attribute to node element
			attr = doc.createAttribute("valuation");
			attr.setValue("31");
			edge.setAttributeNode(attr);
			// set attribute to node element
			attr = doc.createAttribute("type");
			attr.setValue("PLAT");
			edge.setAttributeNode(attr);
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f);
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
	 
			System.out.println("File saved!");
				 
			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			  } catch (TransformerException tfe) {
				tfe.printStackTrace();
			  }
		}
	public static void main(String[] args)
	{
		String fileSeparator=System.getProperty("file.separator");
		OSM graphe=new OSM();
//		Node noeud1=new Node(new StringLabel("noeud1"),new Point(1,0));
//		Node noeud2=new Node(new StringLabel("noeud2"),new Point(1,1));
//		Edge arc1=new Edge(noeud1, noeud2,new StringLabel("arc1"),new Ground(GroundType.FLAT));
//		graphe.setArc(arc1);
//		graphe.setNoeud(noeud1);
//		graphe.setNoeud(noeud2);
//		System.out.println("Source: "+arc1.getSource());
//		System.out.println("Destination: "+arc1.getDestination());
//		System.out.println("Type: "+arc1.getGround().getType());
		//System.out.println("Valuation: "+arc1.getValuation());
		File file = new File("data"+fileSeparator+"graphe.xml");
		new FileXML(file,graphe);
		new FileXML(file);
	}
}
