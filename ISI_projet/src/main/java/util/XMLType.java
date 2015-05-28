package util;

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
