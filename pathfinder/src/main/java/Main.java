import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Node;

public class Main {
    
    public static void main(String[] args){


        Graph graph = new Graph();
        graph.addNode(0);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addEdge(graph.getNode(0), graph.getNode(2));
        graph.addEdge(graph.getNode(0), graph.getNode(3));
        graph.addEdge(graph.getNode(0), graph.getNode(4));
        graph.addEdge(graph.getNode(3), graph.getNode(4));
       
    

        System.out.println(graph.toString());

    }

}
