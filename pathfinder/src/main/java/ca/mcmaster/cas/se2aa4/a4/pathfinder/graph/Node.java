package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    private int id;
    private List<Edge> outEdges; // outgoing edges
    private Map<String, String> attributes;

    public Node(int id) {
        this.id = id;
        this.outEdges = new ArrayList<>();
        this.attributes = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void addOutgoingEdges(Edge edge){
        outEdges.add(edge);
    }

    public void addAttribute(String key, String value){
        attributes.put(key, value);
    }

    public String getAttribute(String key){
        return attributes.get(key);
    }
}
