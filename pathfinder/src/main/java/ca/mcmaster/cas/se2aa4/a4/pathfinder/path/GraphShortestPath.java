package ca.mcmaster.cas.se2aa4.a4.pathfinder.path;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Node;

public class GraphShortestPath implements PathFinder {
    private Graph graph;
    Map<Node, Double> distances = new HashMap<>(); 
    Map<Node, Node> predecessors = new HashMap<>(); 
    
    public GraphShortestPath(Graph graph) {
        this.graph = graph;
    }
    
    @Override
    public List<Node> findPath(Node start, Node end) {
        

        PriorityQueue<Node> unvisitedNodes = new PriorityQueue<>(Comparator.comparingDouble(distances::get)); 
        Set<Node> visitedNodes = new HashSet<>(); 
        
        // initialize tentative distances and add all nodes to unvisited nodes
        for (Node node : graph.getNodesList()) {
            if (node.equals(start)) {
                distances.put(node, 0.0);
            } else {
                distances.put(node, Double.POSITIVE_INFINITY);
            }
            unvisitedNodes.offer(node);
        }
        
        
        while (!unvisitedNodes.isEmpty()) {
            Node current = unvisitedNodes.poll();
            //System.out.println(current.getId()); //TEST: check to see if nodes are being visited
                    
            if (current.equals(end)) {

                
                List<Node> shortestPath = new ArrayList<>();
                Node node = end;
                while (node != null) {
                    shortestPath.add(node);
                    node = predecessors.get(node);
                }
                Collections.reverse(shortestPath); 
                return shortestPath;
            }
            visitedNodes.add(current);
            for (Edge edge : current.getOutEdges()) {
                Node neighbor = edge.getDestination();
                if (visitedNodes.contains(neighbor)) {
                    continue;
                }
                double tentativeDistance = distances.get(current) + edge.getWeight();
                if (tentativeDistance < distances.get(neighbor)) {
                    distances.put(neighbor, tentativeDistance);
                    predecessors.put(neighbor, current);
                    unvisitedNodes.remove(neighbor);
                    unvisitedNodes.offer(neighbor);
                }
            }
        }
        
        // end node is not reachable from start node
        return null;



    }
}