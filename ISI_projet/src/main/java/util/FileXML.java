package util;

import java.awt.Point;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;
import model.graph.label.impl.StringLabel;

public class FileXML {
	public FileXML(File f,OSM graphe)
	{
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(OSM.class.getPackage().getName(),OSM.class.getClassLoader());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 
			jaxbMarshaller.marshal(graphe, f);
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
		String fileSeparator=System.getProperty("file.separator");
		OSM graphe=new OSM();
		Node noeud1=new Node(new StringLabel("noeud1"),new Point(1,0));
		Node noeud2=new Node(new StringLabel("noeud2"),new Point(1,1));
		Edge arc1=new Edge(noeud1, noeud2,new StringLabel("arc1"),new Ground(GroundType.FLAT));
		graphe.setArc(arc1);
		graphe.setNoeud(noeud1);
		graphe.setNoeud(noeud2);
		System.out.println("Source: "+arc1.getSource());
		System.out.println("Destination: "+arc1.getDestination());
		System.out.println("Type: "+arc1.getGround().getType());
		System.out.println("Valuation: "+arc1.getValuation());
		File file = new File("data"+fileSeparator+"file.xml");
		new FileXML(file,graphe);
	}
}
