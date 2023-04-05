package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph;

import java.util.HashMap;
import java.util.Map;

public class Edge {
    private Node source;
    private Node destination;
    private int weight;
    private Map<String, String> attributes;

    public Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
        this.weight = 0;
        this.attributes = new HashMap<>();
        source.addOutgoingEdges(this);
        destination.addOutgoingEdges(this);
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public void addAttribute(String key, String value){
        attributes.put(key, value);
    }

    public String getAttribute(String key){
        return attributes.get(key);
    }
}
