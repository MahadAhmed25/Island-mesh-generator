package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.GraphShortestPath;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.PathFinder;

public class graphTest {
    
    Graph graph;

    @Before
    public void setUpGraph() {
        this.graph = new Graph();
        this.graph.addNode(1);
        this.graph.addNode(2);
        this.graph.addNode(3);
        this.graph.addNode(4);
        this.graph.addEdge(this.graph.getNode(1), this.graph.getNode(2));
        this.graph.addEdge(this.graph.getNode(2), this.graph.getNode(3));
        this.graph.addEdge(this.graph.getNode(3), this.graph.getNode(4));
    }

    @Test
    public void addNodetoGraph(){
        int size = this.graph.getNodesList().size();
        this.graph.addNode(5);
        assertEquals(this.graph.getNodesList().size(), size+1); 
    }

    @Test
    public void addNodeInstancetoGraph(){
        int size = this.graph.getNodesList().size();
        Node node = new Node(10);
        this.graph.addNode(node);
        assertEquals(this.graph.getNodesList().size(), size+1);
    }

    @Test
    public void addEdgetoGraph(){
        int size = this.graph.getEdgesList().size();
        this.graph.addEdge(this.graph.getNode(1), this.graph.getNode(3));
        assertEquals(this.graph.getEdgesList().size(), size+1);
    }

    @Test
    public void addAttritbutetoNode(){
        this.graph.getNode(1).addAttribute("elevation", "100");
        assertEquals(this.graph.getNode(1).getAttribute("elevation"), "100");
    }

    @Test
    public void addAttritbutetoEdge(){
        this.graph.getEdge(0).addAttribute("type", "road");
        assertEquals(this.graph.getEdge(0).getAttribute("type"), "road");
    }

    @Test
    public void makeSureDuplicateNodeDoesntGetAdded(){
        int size = this.graph.getNodesList().size();
        this.graph.addNode(1);
        assertEquals(this.graph.getNodesList().size(), size);
    }

    @Test
    public void makeSureDuplicateEdgeDoesntGetAdded(){
        int size = this.graph.getEdgesList().size();
        this.graph.addEdge(this.graph.getNode(1), this.graph.getNode(2));
        assertEquals(this.graph.getEdgesList().size(), size);
    }

    @Test
    public void addEdgebetweenNonExistentNodeShouldGiveError(){
        assertThrows(NullPointerException.class, () -> {
            this.graph.addEdge(this.graph.getNode(1), this.graph.getNode(10));
        }); 
    }

    @Test
    public void findThePathFromNode1toNode4(){
        PathFinder path = new GraphShortestPath(this.graph);
        List<Node> expectedPath = Arrays.asList(this.graph.getNode(1), this.graph.getNode(2), this.graph.getNode(3), this.graph.getNode(4));
        assertEquals(expectedPath, path.findPath(this.graph.getNode(1), this.graph.getNode(4)));
    }

    @Test
    public void findThePathFromNode1toNode4Weighted(){
        this.graph.addEdge(this.graph.getNode(1), this.graph.getNode(3));
        this.graph.getEdge(3).setWeight(10);
        PathFinder path = new GraphShortestPath(this.graph);
        List<Node> expectedPath = Arrays.asList(this.graph.getNode(1), this.graph.getNode(2), this.graph.getNode(3), this.graph.getNode(4));
        assertEquals(expectedPath, path.findPath(this.graph.getNode(1), this.graph.getNode(4)));
    }

    @Test
    public void findThePathFromNode1toNode4Weighted2(){
        this.graph.addEdge(this.graph.getNode(1), this.graph.getNode(3));
        this.graph.getEdge(3).setWeight(10);
        this.graph.getEdge(0).setWeight(10000);
        PathFinder path = new GraphShortestPath(this.graph);
        List<Node> expectedPath = Arrays.asList(this.graph.getNode(1), this.graph.getNode(3), this.graph.getNode(4));
        assertEquals(expectedPath, path.findPath(this.graph.getNode(1), this.graph.getNode(4)));
    }

}
