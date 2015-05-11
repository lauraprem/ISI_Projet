package util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.graph.Node;
import model.graph.edge.Edge;

@XmlRootElement
public class OSM {
	private Edge arc;
	private Node noeud;
	public Edge getArc() {
		return arc;
	}
	
	public Node getNoeud() {
		return noeud;
	}
	@XmlElement
	public void setArc(Edge arc) {
		this.arc = arc;
	}
	@XmlElement
	public void setNoeud(Node noeud) {
		this.noeud = noeud;
	}
	
}
