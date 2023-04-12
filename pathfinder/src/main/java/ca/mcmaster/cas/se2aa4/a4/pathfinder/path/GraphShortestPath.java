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
    Map<Node, Double> distances = new HashMap<>(); // tentative distances from start node to each node
    Map<Node, Node> predecessors = new HashMap<>(); // predecessors of each node in the shortest path
    
    public GraphShortestPath(Graph graph) {
        this.graph = graph;
    }
    
    @Override
    public List<Node> findPath(Node start, Node end) {
        

        PriorityQueue<Node> unvisitedNodes = new PriorityQueue<>(Comparator.comparingDouble(distances::get)); // nodes not visited yet
        Set<Node> visitedNodes = new HashSet<>(); // visited nodes
        
        // initialize tentative distances and add all nodes to unvisited nodes
        for (Node node : graph.getNodesList()) {
            if (node.equals(start)) {
                distances.put(node, 0.0);
            } else {
                distances.put(node, Double.POSITIVE_INFINITY);
            }
            unvisitedNodes.offer(node);
        }
        
        // loop until we reach the end node or there are no more nodes to visit
        System.out.println("*");
        while (!unvisitedNodes.isEmpty()) {
            Node current = unvisitedNodes.poll();
                    
            if (current.equals(end)) {

                // we have found the shortest path, reconstruct it using predecessors and return it
                List<Node> shortestPath = new ArrayList<>();
                Node node = end;
                while (node != null) {
                    shortestPath.add(node);
                    node = predecessors.get(node);
                }
                Collections.reverse(shortestPath); // reverse the list to get the path from start to end
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
        System.out.println("no path was found");
        return null;



    }
}