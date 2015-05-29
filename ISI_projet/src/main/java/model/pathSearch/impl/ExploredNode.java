package model.pathSearch.impl;

import model.graph.Node;

public class ExploredNode {

	private Node currentNode;
	
	private Node previousNode;
	
	private Double pathCost;
	
	public ExploredNode(Node _currentNode, Node _previousNode, Double _pathCost)
	{
		currentNode = _currentNode;
		previousNode = _previousNode;
		pathCost = _pathCost;
	}
	
	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public Node getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(Node previousNode) {
		this.previousNode = previousNode;
	}

	public double getPathCost() {
		return pathCost;
	}

	public void setPathCost(Double pathCost) {
		this.pathCost = pathCost;
	}

}
