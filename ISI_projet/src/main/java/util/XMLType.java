package util;
/**
 * Sous-element accepter dans le XML(OSM rootelement)
 * @author gael,corinne,alexandre,laura
 *
 */
public enum XMLType {
    Node("node"), Edge("edge");
    private String label;
    
    XMLType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
