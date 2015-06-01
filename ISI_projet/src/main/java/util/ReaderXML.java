package util;

import model.graph.Node;
import model.graph.edge.Edge;
import model.graph.graph.IGraph;
import model.graph.graph.impl.Graph;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * singleton comportant le chargement d'un fichier XML vers un graphe
 *
 * @author gael, corinne, alexandre, laura
 */
public class ReaderXML {
    private final static Logger logger = LogManager.getLogger();
    private static ReaderXML reader;

    /**
     * methode pour recuperer le singleton
     *
     * @return une instance une unique
     */
    public static ReaderXML getInstance() {
        if (reader == null) {
            reader = new ReaderXML();
        }
        return reader;
    }

    public IGraph chargerDocument(File documentToRead) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        return this.chargerDocument(documentToRead, null);
    }

    /**
     * @param graphe         instance de IGraph a retourner
     * @param documentToRead document � lire
     * @return un graphe charger � partir du XML
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public IGraph chargerDocument(File documentToRead, IGraph graphe) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
        if (graphe == null) {
            graphe = new Graph();
        }
        File fXmlFile = documentToRead;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        //optionnel mais recommand�
        doc.getDocumentElement().normalize();
        logger.trace("Root element : " + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName(XMLType.Node.getLabel());
        listerElement(nList, graphe, XMLType.Node);
        nList = doc.getElementsByTagName(XMLType.Edge.getLabel());
        listerElement(nList, graphe, XMLType.Edge);
        if (graphe.getAllNodes().size() > 0) {
            return graphe;
        }
        return null;
    }

    /**
     * permet de récupérer l'ensemble des attributs d'un noeud du XML visité
     *
     * @param nList       noeud du XML récupéré
     * @param graphe      a charger
     * @param typeDeNoeud permet de connaitre quel est la classe à utilisé par
     *                    rapport à nList
     * @throws InvocationTargetException ciblage
     * @throws IllegalArgumentException  mauvais argument
     * @throws IllegalAccessException    probléme d'accés de reflexivité
     */
    public void listerElement(NodeList nList, IGraph graphe, XMLType typeDeNoeud) throws IllegalArgumentException, InvocationTargetException {
        for (int temp = 0; temp < nList.getLength(); temp++) {

            org.w3c.dom.Node nNode = nList.item(temp);

            logger.trace("Current Element : " + nNode.getNodeName());

            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                NamedNodeMap noms = nList.item(temp).getAttributes();
                switch (typeDeNoeud) {
                    case Node:
                        Node noeud = new Node();
                        ArrayList<Field> fieldList = new ArrayList<Field>();
                        Field[] fields = Node.class.getDeclaredFields();
                        for (int i = 0; i < fields.length; i++) {
                            fieldList.add(fields[i]);
                        }
                        fields = Node.class.getSuperclass().getDeclaredFields();
                        for (int i = 0; i < fields.length; i++) {
                            fieldList.add(fields[i]);
                        }
                        for (int i = 0; i < noms.getLength(); i++) {
                            String nomParam = noms.item(i).getNodeName();
                            for (Field parametre : fieldList) {
                                if (parametre.getName().contains(nomParam)) {
                                    for (Method method : Node.class.getDeclaredMethods()) {
                                        String nomMethod = method.getName();
                                        if (parametre.getType().getName().contains("String") && nomMethod.equalsIgnoreCase("set" + parametre.getName())
                                                || !(parametre.getType().getName().contains("String")) && nomMethod.equalsIgnoreCase("set" + parametre.getName() + "String")) {
                                            method.setAccessible(true);
                                            try {
                                                method.invoke(noeud, (eElement.getAttribute(nomParam)));
                                            } catch (IllegalAccessException e) {
                                                logger.error("Attention problème d'accès au niveau de la classe " + Node.class.getName() + " et de la méthode:" + method.getName());
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                            logger.trace(nomParam + " : " + eElement.getAttribute(nomParam));
                        }
                        graphe.addNode(noeud);
                        break;
                    case Edge:
                        Edge arc = new Edge();
                        for (int i = 0; i < noms.getLength(); i++) {
                            for (Field parametre : Edge.class.getDeclaredFields()) {
                                String nomParam = noms.item(i).getNodeName();
                                if (parametre.getName().contains(noms.item(i).getNodeName())) {
                                    for (Method method : Edge.class.getDeclaredMethods()) {
                                        String nomMethod = method.getName();
                                        if (parametre.getType().getName().contains("Node") && method.getName().equalsIgnoreCase("set" + parametre.getName())) {
                                            method.setAccessible(true);
                                            for (Node noeudGraphe : graphe.getAllNodes()) {
                                                if (String.valueOf(noeudGraphe.getId()).equals(eElement.getAttribute(noms.item(i).getNodeName()))) {
                                                    try {
                                                        method.invoke(arc, noeudGraphe);
                                                    } catch (IllegalAccessException e) {
                                                        logger.error("Attention problème d'accès au niveau de la classe " + Edge.class.getName() + " et de la méthode:" + method.getName());
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        } else {
                                            if (parametre.getType().getName().contains("Ground") && method.getName().equalsIgnoreCase("set" + parametre.getName())) {
                                                Ground ground = new Ground(GroundType.getGroundType(eElement.getAttribute(noms.item(i).getNodeName())));
                                                method.setAccessible(true);
                                                try {
                                                    method.invoke(arc, ground);
                                                } catch (IllegalAccessException e) {
                                                    logger.error("Attention problème d'accès au niveau de la classe " + Edge.class.getName() + " et de la méthode:" + method.getName());
                                                    e.printStackTrace();
                                                }
                                            } else {
                                                if (parametre.getType().getName().contains("String") && method.getName().equalsIgnoreCase("set" + parametre.getName())
                                                        || !(parametre.getType().getName().contains("String")) && method.getName().equalsIgnoreCase("set" + parametre.getName() + "String")) {
                                                    method.setAccessible(true);
                                                    try {
                                                        method.invoke(arc, (eElement.getAttribute(noms.item(i).getNodeName())));
                                                    } catch (IllegalAccessException e) {
                                                        logger.error("Attention problème d'accès au niveau de la classe " + Edge.class.getName() + " et de la méthode:" + method.getName());
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            logger.trace(noms.item(i).getNodeName() + " : " + eElement.getAttribute(noms.item(i).getNodeName()));
                        }
                        graphe.addEdge(arc);
                        break;
                }

            }
        }
    }
}
