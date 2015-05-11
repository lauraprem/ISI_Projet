package model.graph.label.impl;

import model.graph.label.Label;

public class DoubleLabel implements Label {
    double label;

    public DoubleLabel(double label) {
        super();
        this.label = label;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = (Double) label;
    }

    public String toString() {
        return Double.toString(label);
    }

    @Override
    public DoubleLabel clone() {
        return new DoubleLabel(label);
    }
}
