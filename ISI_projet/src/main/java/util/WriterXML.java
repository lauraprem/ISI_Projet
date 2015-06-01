package util;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.graph.impl.Graph;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
/**
 * singleton comportant la sauvegarde un graphe dans un fichier XML
 * @author gael,corinne,alexandre,laura
 *
 */
public class WriterXML {
	private final static Logger logger = LogManager.getLogger();
    private static WriterXML writer;
    /**
	 * methode pour recuperer le singleton
	 * @return une instance une unique
	 */
    public static WriterXML getInstance() {
        if (writer == null) {
            writer = new WriterXML();
        }
        return writer;
    }
    /**
     * 
     * @param f fichier dans lequel on sauvegarde le graphe
     * @param graphe à fournir pour le sauvegarder
     */
    public void sauvegarderDocument(File f, Graph graphe) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("osm");
            doc.appendChild(rootElement);
            //creation des element node
            for (Node noeud : graphe.getAllNodes()) {
                creerElementNode(noeud, doc, rootElement);
            }
            for (Edge arc : graphe.getAllEdges()) {
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

            logger.info("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
    /**
     * methode permettant de créer les élément Node du XML
     * @param noeud instance permettant de récupérer les futures attribut de l'élément Node
     * @param doc document dans lequel on doit sauvegarder les valuers
     * @param rootElement element de référence du document
     */
    public void creerElementNode(Node noeud, Document doc, Element rootElement) {
        // node elements
        Element node = doc.createElement("node");
        rootElement.appendChild(node);
        // set attribute to node element
        Attr attr = doc.createAttribute(Attribute.id);
        attr.setValue(String.valueOf(noeud.getId()));
        node.setAttributeNode(attr);
        // set attribute to node element
        attr = doc.createAttribute(Attribute.label);
        attr.setValue(String.valueOf(noeud.getLabel()));
        node.setAttributeNode(attr);
        // set attribute to node element
        attr = doc.createAttribute(Attribute.x);
        attr.setValue(String.valueOf(noeud.getX()));
        node.setAttributeNode(attr);
        // set attribute to node element
        attr = doc.createAttribute(Attribute.y);
        attr.setValue(String.valueOf(noeud.getY()));
        node.setAttributeNode(attr);
        // set attribute to node element
        attr = doc.createAttribute(Attribute.fireLevel);
        attr.setValue(String.valueOf(noeud.getFireLevel()));
        node.setAttributeNode(attr);
    }
    /**
     * methode permettant de créer les élément Edge du XML
     * @param arc instance permettant de récupérer les futures attribut de l'élément Edge
     * @param doc document dans lequel on doit sauvegarder les valuers
     * @param rootElement element de référence du document
     */
    public void creerElementEdge(Edge arc, Document doc, Element rootElement) {
        // edge elements
        Element edge = doc.createElement("edge");
        rootElement.appendChild(edge);
        //set attribute to edge element
        Attr attr = doc.createAttribute(Attribute.source);
        attr.setValue(String.valueOf(arc.getSource().getId()));
        edge.setAttributeNode(attr);
        //set attribute to edge element
        attr = doc.createAttribute(Attribute.destination);
        attr.setValue(String.valueOf(arc.getDestination().getId()));
        edge.setAttributeNode(attr);
        // set attribute to node element
        attr = doc.createAttribute(Attribute.length);
        attr.setValue(String.valueOf(arc.getLength()));
        edge.setAttributeNode(attr);
        // set attribute to node element
        attr = doc.createAttribute(Attribute.ground);
        attr.setValue(arc.getGround().getType().getLabel());
        edge.setAttributeNode(attr);
    }
}
