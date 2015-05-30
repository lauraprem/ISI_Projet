package util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.graph.IdAlreadyUsedException;
import model.graph.Node;
import model.graph.Point;
import model.graph.PointUtil;
import model.graph.edge.Edge;
import model.graph.graph.impl.Graph;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class ReaderXML {
	private static ReaderXML reader;
	public static ReaderXML getInstance()
	{
		if(reader==null)
		{
			reader=new ReaderXML();
		}
		return reader;
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
			return graphe;
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		return null;
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
					ArrayList<Field> fieldList=new ArrayList<Field>();
					Field[] fields=Node.class.getDeclaredFields();
					for(int i=0;i<fields.length;i++)
					{
						fieldList.add(fields[i]);
					}
					fields=Node.class.getSuperclass().getDeclaredFields();
					for(int i=0;i<fields.length;i++)
					{
						fieldList.add(fields[i]);
					}
					
					Node node = new Node();
					for(int i=0;i<noms.getLength();i++)
					{
//						for(Field parametre:fieldList)
//						{
//							String nomParam=noms.item(i).getNodeName();
//							if(parametre.getName().contains(noms.item(i).getNodeName()))
//							{
//								for(Method method:Node.class.getDeclaredMethods())
//								{
//									String nomMethod=method.getName();
//									try {
//									if(parametre.getType().getName().contains("String") && method.getName().equalsIgnoreCase("set"+parametre.getName()))
//									{
//										method.setAccessible(true);
//										method.invoke(noeud,(eElement.getAttribute(noms.item(i).getNodeName())));
//									}
//									else
//										if(!(parametre.getType().getName().contains("String")) && method.getName().equalsIgnoreCase("set"+parametre.getName()+"String"))
//									{
//										method.setAccessible(true);
//										method.invoke(noeud,(eElement.getAttribute(noms.item(i).getNodeName())));
//									}
//									} catch (IllegalAccessException e) {
//										e.printStackTrace();
//									} catch (IllegalArgumentException e) {
//										e.printStackTrace();
//									} catch (InvocationTargetException e) {
//										e.printStackTrace();
//									} catch (SecurityException e) {
//										e.printStackTrace();
//									}
//								}
//							}
//						}
						String attrValue = eElement.getAttribute(noms.item(i).getNodeName());
						switch (noms.item(i).getNodeName()) {
						case "id":
							try {
								node.setIfUniqueId(Long.parseLong(attrValue));
							} catch (IdAlreadyUsedException e) {
								e.printStackTrace();
							}
							case "x":
							node.setX((int)Double.parseDouble(attrValue));
						case "y":
							node.setY((int)Double.parseDouble(attrValue));
						case "type":
					        if( eElement.getAttribute(noms.item(i).getNodeName()).equals("INCENDIE")){
					        	node.increaseFireLevel(10);
					        }
							break;
						default:
							break;
						}
//						System.out.println(noms.item(i).getNodeName()+" : " + eElement.getAttribute(noms.item(i).getNodeName()));
					}
					
					graphe.addNode(node);
//					graphe.addNode(noeud);
					break;
				case Edge:
					Edge arc=new Edge();
					for(int i=0;i<noms.getLength();i++)
					{
						for(Field parametre:Edge.class.getDeclaredFields())
						{
							String nomParam=noms.item(i).getNodeName();
							if(parametre.getName().contains(noms.item(i).getNodeName()))
							{
								for(Method method:Edge.class.getDeclaredMethods())
								{
									String nomMethod=method.getName();
									try {
									if(parametre.getType().getName().contains("Node")&& method.getName().equalsIgnoreCase("set"+parametre.getName()))
									{
										method.setAccessible(true);
										for(Node noeudGraphe:graphe.getAllNodes())
										{
											if(String.valueOf(noeudGraphe.getId()).equals(eElement.getAttribute(noms.item(i).getNodeName())))
											{
												method.invoke(arc,noeudGraphe);
											}
										}
									}
									else
									{
										if(parametre.getType().getName().contains("Ground") && method.getName().equalsIgnoreCase("set"+parametre.getName()))
										{
											Ground ground=new Ground(GroundType.getGroundType(eElement.getAttribute(noms.item(i).getNodeName())));
											method.setAccessible(true);
											method.invoke(arc, ground);
										}
										else
										{
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
										}
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

						/*String attrValue = eElement.getAttribute(noms.item(i).getNodeName());
						switch (noms.item(i).getNodeName()) {
						case "nd1":
							for (int j = 0; j < graphe.getAllNodes().size(); j++) {
								String StringIdNode = graphe.getAllNodes().get(j).getId().toString();
								if(attrValue.equals(StringIdNode)){
									arc.setSource(graphe.getAllNodes().get(j));
								break;
								}
							}
							break;
						case "nd2":
							for (int j = 0; j < graphe.getAllNodes().size(); j++) {
								String StringIdNode = graphe.getAllNodes().get(j).getId().toString();
								if(attrValue.equals(StringIdNode)){
									arc.setDestination(graphe.getAllNodes().get(j));
								break;
								}
							}
							break;
						case "type":
							Ground ground=new Ground(GroundType.getGroundType(eElement.getAttribute(noms.item(i).getNodeName())));
							arc.setGround(ground);
							break;
						default:
							break;
						}*/
					}
					
					// Calcul de la distance entre les points
					Point point1 = new Point (arc.getSource().getX(),arc.getSource().getY());
					Point point2 = new Point (arc.getDestination().getX(),arc.getDestination().getY());
					arc.setLength(PointUtil.getDistance(point1, point2));
					
					graphe.addEdge(arc);
					break;
				}
			}
		}
	}
}
