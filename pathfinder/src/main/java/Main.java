import java.util.List;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.GraphShortestPath;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.PathFinder;

public class Main {
    

    public static void main(String[] args) {
        
        Graph graph = new Graph();

        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);
        graph.addNode(7);
        graph.addNode(8);
        graph.addNode(9);
        graph.addNode(10);
        graph.addNode(11);
        graph.addNode(12);
        graph.addNode(13);
        graph.addNode(14);
        graph.addNode(15);
        graph.addNode(16);
        
        graph.addEdge(graph.getNode(0), graph.getNode(1));
        graph.addEdge(graph.getNode(0), graph.getNode(7));

        graph.addEdge(graph.getNode(1), graph.getNode(0));
        graph.addEdge(graph.getNode(1), graph.getNode(7));
        graph.addEdge(graph.getNode(1), graph.getNode(2));
        graph.addEdge(graph.getNode(1), graph.getNode(8));

        graph.addEdge(graph.getNode(2), graph.getNode(1));
        graph.addEdge(graph.getNode(2), graph.getNode(8));
        graph.addEdge(graph.getNode(2), graph.getNode(9));
        graph.addEdge(graph.getNode(2), graph.getNode(3));

        graph.addEdge(graph.getNode(3), graph.getNode(2));
        graph.addEdge(graph.getNode(3), graph.getNode(9));
        graph.addEdge(graph.getNode(3), graph.getNode(4));
        graph.addEdge(graph.getNode(3), graph.getNode(10));

        graph.addEdge(graph.getNode(4), graph.getNode(3));
        graph.addEdge(graph.getNode(4), graph.getNode(10));
        graph.addEdge(graph.getNode(4), graph.getNode(11));
        graph.addEdge(graph.getNode(4), graph.getNode(5));

        graph.addEdge(graph.getNode(5), graph.getNode(4));
        graph.addEdge(graph.getNode(5), graph.getNode(11));
        graph.addEdge(graph.getNode(5), graph.getNode(12));
        graph.addEdge(graph.getNode(5), graph.getNode(6));

        graph.addEdge(graph.getNode(6), graph.getNode(5));
        graph.addEdge(graph.getNode(6), graph.getNode(12));

        graph.addEdge(graph.getNode(8), graph.getNode(1));
        graph.addEdge(graph.getNode(8), graph.getNode(2));
        graph.addEdge(graph.getNode(8), graph.getNode(9));
        graph.addEdge(graph.getNode(8), graph.getNode(16));
        graph.addEdge(graph.getNode(8), graph.getNode(15));
        graph.addEdge(graph.getNode(8), graph.getNode(14));
        graph.addEdge(graph.getNode(8), graph.getNode(7));

        PathFinder path = new GraphShortestPath(graph);
        List<Node> l = path.findPath(graph.getNode(0), graph.getNode(6));

        for(Node n: l){
            System.out.println(n.getId());
        }

    }

}
