package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Node, List<Node>> adjacencyList;
    private List<Node> nodes;
    private List<Edge> edges;

    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addNode(int id) {
        Node node = getNode(id);
        if (node == null) {
            node = new Node(id);
            adjacencyList.putIfAbsent(node, new ArrayList<>());
            nodes.add(node);
        }
    }

    public void addNode(Node node){
        if(getNode(node.getId()) == null){
            adjacencyList.putIfAbsent(node, new ArrayList<>());
            nodes.add(node);
        }
    }

    public void addEdge(Node node1, Node node2) {
        // Check if the edge already exists
        if (adjacencyList.get(node1).contains(node2) || adjacencyList.get(node2).contains(node1)) 
            return;
    
        // Add the new edge
        Edge edge = new Edge(node1, node2);
        adjacencyList.get(node1).add(node2);
        adjacencyList.get(node2).add(node1);
        edges.add(edge);
    }

    public Node getNode(int id){
        for (Node node : adjacencyList.keySet()) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null; // node with id not found
    }

    public List<Node> getNodesList(){
        return nodes;
    }

    public List<Edge> getEdgesList(){
        return edges;
    }

    public Edge getEdge(int index){
        return edges.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, List<Node>> entry : adjacencyList.entrySet()) {
            Node node = entry.getKey();
            List<Node> neighbors = entry.getValue();
            sb.append(node.getId()).append(": ");
            for (Node neighbor : neighbors) {
                sb.append(neighbor.getId()).append(", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    
}
