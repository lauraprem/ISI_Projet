package model.graph.label;

public interface Label {
    public Object getLabel();

    public void setLabel(Object label);

    public String toString();

    public Label clone();
}
