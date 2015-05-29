package model.graph.ground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Alexandre
 *         06/05/2015
 */
public enum GroundType {
    FLOODED("Inondé"), FLAT("Plat"), STEEP("Escarpé");

    private final static Logger logger = LogManager.getLogger();

    private String label;

    GroundType(String label) {
        this.label = label;
    }

    public static GroundType getGroundType(String label) {
        GroundType[] types = GroundType.values();
        for (GroundType type : types) if (type.getLabel().equals(label)) return type;
        logger.warn(String.format("No GroundType is matching : \"%s\"", label));
        return null;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GroundType{");
        sb.append("label='").append(label).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
