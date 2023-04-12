package ca.mcmaster.cas.se2aa4.a3.island.urbanization;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;

public class MeshToGraph{
    
    private Structs.Mesh aMesh;

    public MeshToGraph(Structs.Mesh mesh){
        this.aMesh = mesh;
    }

    public Graph convert(){

        Graph graph = new Graph();

        //Add all centroids to graph to represent the nodes
        for(Structs.Polygon p: aMesh.getPolygonsList()){
            graph.addNode(p.getCentroidIdx());
        }

        //Connect all neighboring centroids with graph edges
        for(Structs.Polygon p: aMesh.getPolygonsList()){
            for(int idx: p.getNeighborIdxsList()){
                graph.addEdge(graph.getNode(p.getCentroidIdx()), graph.getNode(aMesh.getPolygons(idx).getCentroidIdx()));
            }
        }


        return graph;
 
    }
}

