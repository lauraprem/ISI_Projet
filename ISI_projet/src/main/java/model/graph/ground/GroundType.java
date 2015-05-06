package model.graph.ground;

/**
 * @author Alexandre
 *         06/05/2015
 */
public enum GroundType {
    FLOODED("Inondé"), FLAT("Plat"), STEEP("Escarpé");

    private String label;

    GroundType(String label) {
        this.label = label;
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
