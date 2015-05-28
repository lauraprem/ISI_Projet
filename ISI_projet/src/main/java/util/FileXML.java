package util;

import java.awt.Point;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
import model.graph.graph.impl.Graph;
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
	public FileXML(File f,Graph graphe)
	{
		sauvegarderDocument(f, graphe);
	}
	public FileXML(File f)
	{
		chargerDocument(f);
	}
	public FileXML()
	{
		
	}
	public Graph chargerDocument(File documentToRead)
	{
		Graph graphe=new Graph();
		try {
			 
			File fXmlFile =documentToRead;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		 
			NodeList nList = doc.getElementsByTagName(XMLType.Node.getLabel());
		 
			System.out.println("----------------------------");
			listerElement(nList,graphe,XMLType.Node);
			nList = doc.getElementsByTagName(XMLType.Edge.getLabel());
			 
			System.out.println("----------------------------");
			listerElement(nList,graphe,XMLType.Edge);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		return graphe;
	}
	public void listerElement(NodeList nList,Graph graphe,XMLType typeDeNoeud)
	{
		for (int temp = 0; temp < nList.getLength(); temp++) {
			 
			org.w3c.dom.Node nNode = nList.item(temp);
	 
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			
			if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
				NamedNodeMap noms=nList.item(temp).getAttributes();
				switch(typeDeNoeud)
				{
				case Node:
					Node noeud=new Node();
					for(int i=0;i<noms.getLength();i++)
					{
						for(Field parametre:Node.class.getDeclaredFields())
						{
							String nomParam=noms.item(i).getNodeName();
							if(parametre.getName().contains(noms.item(i).getNodeName()))
							{
								for(Method method:Node.class.getDeclaredMethods())
								{
									String nomMethod=method.getName();
									try {
									if(parametre.getType().getName().contains("String") && method.getName().equalsIgnoreCase("set"+parametre.getName()))
									{
										method.setAccessible(true);
										method.invoke(noeud,(eElement.getAttribute(noms.item(i).getNodeName())));
									}
									else
										if(!(parametre.getType().getName().contains("String")) && method.getName().equalsIgnoreCase("set"+parametre.getName()+"String"))
									{
										method.setAccessible(true);
										method.invoke(noeud,(eElement.getAttribute(noms.item(i).getNodeName())));
									}
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
						System.out.println(noms.item(i).getNodeName()+" : " + eElement.getAttribute(noms.item(i).getNodeName()));
					}
					graphe.addNode(noeud);
					break;
				case Edge:
					Edge arc=new Edge();
					for(int i=0;i<noms.getLength();i++)
					{
						for(Field parametre:Edge.class.getDeclaredFields())
						{
							if(parametre.equals(noms.item(i).getNodeName()))
							{
								for(Method method:Edge.class.getDeclaredMethods())
								{
									String nomMethod=method.getName();
									try {
									if(parametre.getType().getName().contains("String") && method.getName().equalsIgnoreCase("set"+parametre.getName()))
									{
										method.setAccessible(true);
										method.invoke(arc,(eElement.getAttribute(noms.item(i).getNodeName())));
									}
									else
										if(!(parametre.getType().getName().contains("String")) && method.getName().equalsIgnoreCase("set"+parametre.getName()+"String"))
									{
										method.setAccessible(true);
										method.invoke(arc,(eElement.getAttribute(noms.item(i).getNodeName())));
									}
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
						System.out.println(noms.item(i).getNodeName()+" : " + eElement.getAttribute(noms.item(i).getNodeName()));
					}
					graphe.addEdge(arc);
					break;
				}
				
			}
		}
	}
	public void sauvegarderDocument(File f,Graph graphe)
	{
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("osm");
			doc.appendChild(rootElement);
			//creation des element node
			for(Node noeud:graphe.getAllNodes())
			{
				creerElementNode(noeud,doc,rootElement);
			}
			for(Edge arc:graphe.getAllEdges())
			{
				creerElementEdge(arc, doc, rootElement);
			}
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
	public void creerElementNode(Node noeud,Document doc,Element rootElement)
	{
		// node elements
		Element node = doc.createElement("node");
		rootElement.appendChild(node);
		// set attribute to node element
		Attr attr = doc.createAttribute("id");
		attr.setValue(String.valueOf(noeud.getId()));
		node.setAttributeNode(attr);
		// set attribute to node element
		attr = doc.createAttribute("label");
		attr.setValue(String.valueOf(noeud.getLabel()));
		node.setAttributeNode(attr);
		// set attribute to node element
		attr = doc.createAttribute("x");
		attr.setValue(String.valueOf(noeud.getX()));
		node.setAttributeNode(attr);
		// set attribute to node element
		attr = doc.createAttribute("y");
		attr.setValue(String.valueOf(noeud.getY()));
		node.setAttributeNode(attr);
		// set attribute to node element
		attr = doc.createAttribute("fireLevel");
		attr.setValue(String.valueOf(noeud.getFireLevel()));
		node.setAttributeNode(attr);
	}
	public void creerElementEdge(Edge arc,Document doc,Element rootElement)
	{
		// edge elements
		Element edge = doc.createElement("edge");
		rootElement.appendChild(edge);
		//set attribute to edge element
		Attr attr = doc.createAttribute("source");
		attr.setValue(arc.getSource().getLabel());
		edge.setAttributeNode(attr);
		//set attribute to edge element
		attr = doc.createAttribute("destination");
		attr.setValue(arc.getDestination().getLabel());
		edge.setAttributeNode(attr);
		// set attribute to node element
		attr = doc.createAttribute("length");
		attr.setValue(String.valueOf(arc.getLength()));
		edge.setAttributeNode(attr);
		// set attribute to node element
		attr = doc.createAttribute("type");
		attr.setValue(arc.getGround().getType().getLabel());
		edge.setAttributeNode(attr);
	}
	public static void main(String[] args)
	{
		String fileSeparator=System.getProperty("file.separator");
		Graph graphe=new Graph();
		Node noeud1=new Node(new String("noeud1"),new Point(1,0));
		Node noeud2=new Node(new String("noeud2"),new Point(1,1));
		Edge arc1=new Edge(noeud1, noeud2,(double)10,new Ground(GroundType.FLAT));
		graphe.addEdge(arc1);
		graphe.addNode(noeud1);
		graphe.addNode(noeud2);
		System.out.println("Source: "+arc1.getSource());
		System.out.println("Destination: "+arc1.getDestination());
		System.out.println("Type: "+arc1.getGround().getType());
		System.out.println("Valuation: "+arc1.getLength());
		File file = new File("data"+fileSeparator+"graphe.xml");
		new FileXML(file,graphe);
		new FileXML(file);
	}
}
