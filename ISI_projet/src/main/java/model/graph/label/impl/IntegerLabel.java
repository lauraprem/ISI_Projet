package model.graph.label.impl;

import model.graph.label.Label;

public class IntegerLabel implements Label {
    int label;

    public IntegerLabel(int label) {
        super();
        this.label = label;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = (Integer) label;
    }

    public String toString() {
        return String.valueOf(label);
    }

    @Override
    public IntegerLabel clone() {
        return new IntegerLabel(label);
    }
}
