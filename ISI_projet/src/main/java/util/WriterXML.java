package util;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.graph.impl.Graph;
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

public class WriterXML {
    private static WriterXML writer;

    public static WriterXML getInstance() {
        if (writer == null) {
            writer = new WriterXML();
        }
        return writer;
    }

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

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public void creerElementNode(Node noeud, Document doc, Element rootElement) {
        // node elements
        Element node = doc.createElement("node");
        rootElement.appendChild(node);
        // set attribute to node element
        Attr attr = doc.createAttribute("id");
        attr.setValue(String.valueOf(noeud.getId()));
        node.setAttributeNode(attr);
        // set attribute to node element
//        attr = doc.createAttribute("label");
//        attr.setValue(String.valueOf(noeud.getLabel()));
//        node.setAttributeNode(attr);
        // set attribute to node element
        attr = doc.createAttribute("x");
        attr.setValue(String.valueOf(noeud.getX()));
        node.setAttributeNode(attr);
        // set attribute to node element
        attr = doc.createAttribute("y");
        attr.setValue(String.valueOf(noeud.getY()));
        node.setAttributeNode(attr);
        // set attribute to node element
        attr = doc.createAttribute("type");
        String type = "NORMAL";
        if(noeud.getFireLevel()>0){
        	type = "INCENDIE";
        }
        attr.setValue(String.valueOf(type));
        node.setAttributeNode(attr);
    }

    public void creerElementEdge(Edge arc, Document doc, Element rootElement) {
        // edge elements
        Element edge = doc.createElement("edge");
        rootElement.appendChild(edge);
        //set attribute to edge element
        Attr attr = doc.createAttribute("nd1");
        attr.setValue(String.valueOf(arc.getSource().getId()));
        edge.setAttributeNode(attr);
        //set attribute to edge element
        attr = doc.createAttribute("nd2");
        attr.setValue(String.valueOf(arc.getDestination().getId()));
        edge.setAttributeNode(attr);
        // set attribute to node element
//        attr = doc.createAttribute("length");
//        attr.setValue(String.valueOf(arc.getLength()));
//        edge.setAttributeNode(attr);
        // set attribute to node element
        attr = doc.createAttribute("type");
        attr.setValue(arc.getGround().getType().getLabel().toUpperCase());
        edge.setAttributeNode(attr);
    }
}
