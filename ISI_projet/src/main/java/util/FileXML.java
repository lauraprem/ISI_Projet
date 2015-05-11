package util;

import java.awt.Point;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.ground.GroundType;
import model.graph.label.impl.StringLabel;

public class FileXML {
	public FileXML(File f,OSM graphe)
	{
		try {
			File file = new File("data\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(OSM.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 
			jaxbMarshaller.marshal(graphe, file);
			jaxbMarshaller.marshal(graphe, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ChargerDocument(File documentToRead)
	{
		
	}
	public static void main(String[] args)
	{
		OSM graphe=new OSM();
		Node noeud1=new Node(new StringLabel("noeud1"),new Point(1,0));
		Node noeud2=new Node(new StringLabel("noeud2"),new Point(1,1));
		Edge arc1=new Edge(noeud1, noeud2);
		arc1.getGround().setType(GroundType.FLAT);
		graphe.setArc(arc1);
		graphe.setNoeud(noeud1);
		graphe.setNoeud(noeud2);
		System.out.println("Destination: "+arc1.getDestination());
		System.out.println("Type: "+arc1.getGround().getType());
		System.out.println("Destination: "+arc1.getDestination());
		System.out.println("Destination: "+arc1.getDestination());
		File file = new File("data\\file.xml");
		new FileXML(file,graphe);
	}
}
