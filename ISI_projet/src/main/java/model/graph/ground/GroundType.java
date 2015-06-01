package model.graph.ground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

/**
 * @author Alexandre
 *         06/05/2015
 */
public enum GroundType {


    /**
     * differents types de sol
     */
    FLOODED("Inondé", Color.BLUE), FLAT("Plat", Color.GRAY), STEEP("Escarpé", Color.ORANGE);


    /**
     * logger de la classe
     */
    private final static Logger logger = LogManager.getLogger();

    /**
     * nom du type de sol
     */
    private String label;
    private Color color;

    GroundType(String label) {
        this(label, Color.BLACK);
    }

    GroundType(String label, Color color) {
        this.label = label;
        this.color = color;
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

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GroundType{");
        sb.append("label='").append(label).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
