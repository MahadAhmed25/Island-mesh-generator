package ca.mcmaster.cas.se2aa4.a4.pathfinder.path;

import java.util.List;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Node;

public interface PathFinder {
    List<Node> findPath(Node start, Node end);
}
