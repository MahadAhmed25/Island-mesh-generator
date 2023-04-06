import java.util.List;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.GraphShortestPath;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.PathFinder;

public class Main {
    
    public static void main(String[] args){


        Graph graph = new Graph();
        graph.addNode(0);
        //graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addEdge(graph.getNode(0), graph.getNode(3));
        graph.addEdge(graph.getNode(3), graph.getNode(4));
        graph.addEdge(graph.getNode(4), graph.getNode(0));
        //graph.addEdge(graph.getNode(2), graph.getNode(1));
        graph.addEdge(graph.getNode(0), graph.getNode(2));
        //graph.getEdge(4).setWeight(100);
       
    

        System.out.println(graph.toString());

        PathFinder path = new GraphShortestPath(graph);
        List<Node> shortestPath = path.findPath(graph.getNode(4), graph.getNode(2));

        for (Node node : shortestPath) {
            System.out.print(node.getId() + " -> ");
        }


    }


}
