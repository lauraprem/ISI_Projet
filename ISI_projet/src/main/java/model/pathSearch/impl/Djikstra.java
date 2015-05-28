package model.pathSearch.impl;

import java.util.ArrayList;
import java.util.List;

import model.graph.Node;
import model.graph.graph.GraphUtil;
import model.graph.graph.IGraph;
import model.graph.ground.GroundType;
import model.pathSearch.IShorterPathSearch;
import model.robot.NodePath;

public class Djikstra implements IShorterPathSearch {

	private ArrayList<Node> closedNodes;
	private ArrayList<ExploredNode> path;
	
	public Djikstra() {
		closedNodes = new ArrayList<Node>();
		path = new ArrayList<ExploredNode>();
		
	}

	@Override
	public Double findShorterPath(IGraph _graph, Node _start, Node goal, List<GroundType> capacity, NodePath _path) {
		IGraph graph = GraphUtil.getFilteredGraph(_graph, capacity);
		if(!graph.getAllNodes().contains(goal)){
			return -1.0;
		}
		closedNodes.add(_start.clone());
		path.add(new ExploredNode(_start, null, 0.0));
	
		Node currentNode = _start.clone();
		//tant qu'on n'a pas trouvé la fin
		while(!currentNode.equals(goal)){
			ExploredNode betterNode = new ExploredNode(graph.getAdjNodes(path.get(0).getCurrentNode()).get(0), path.get(0).getCurrentNode(), path.get(0).getPathCost()+graph.getEdgeFromNodes(path.get(0).getCurrentNode(), graph.getAdjNodes(path.get(0).getCurrentNode()).get(0)).getLength());
			//pour chaque noeud qu'on a déjà visité
			for(ExploredNode exploredNode : path){
				//pour chacun de ses voisins
				for(Node node : graph.getAdjNodes(exploredNode.getCurrentNode())){
					//s'il n'est pas déjà visité et si son cout est inferieur au précédent toruvé
					if (!closedNodes.contains(node) && graph.getEdgeFromNodes(node, exploredNode.getCurrentNode()).getLength()+exploredNode.getPathCost() < betterNode.getPathCost()){
						//on conserve ce noeud comme étant le prochain
						betterNode = new ExploredNode(node, exploredNode.getCurrentNode(), graph.getEdgeFromNodes(node, exploredNode.getCurrentNode()).getLength()+exploredNode.getPathCost());
						
					}
				}
			}
			//TODO si betternode.currentnode() est déjà dans la liste, il faut modifier et pas ajouter !!!
			path.add(betterNode);
			currentNode = betterNode.getCurrentNode();
			closedNodes.add(betterNode.getCurrentNode());
			
		}

		Node node = goal;
		_path.addFirst(node);
		while(!node.equals(_start)){
			for(ExploredNode exploredNode : path){
				if(exploredNode.getCurrentNode().equals(node)){
					node=exploredNode.getPreviousNode();
					_path.addFirst(node);
					break;
				}
				
			}
		}
		
		
		return path.get(path.size()-1).getPathCost();
	}
	



}
