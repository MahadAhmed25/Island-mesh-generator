package ca.mcmaster.cas.se2aa4.a3.island.urbanization;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;

public class meshToGraph {
    
    private Structs.Mesh aMesh;

    public meshToGraph(Structs.Mesh mesh){
        this.aMesh = mesh;
    }

    public void convert(){

        Graph graph = new Graph();

        //Add all centroids to graph to represent the nodes
        for(Structs.Polygon p: aMesh.getPolygonsList()){
            graph.addNode(p.getCentroidIdx());
        }

        //Connect all neighboring centroids with graph edges
        for(Structs.Polygon p: aMesh.getPolygonsList()){
            for(int idx: p.getNeighborIdxsList()){
                Structs.Polygon pNeighbor = aMesh.getPolygons(idx);
                graph.addEdge(graph.getNode(p.getCentroidIdx()), graph.getNode(pNeighbor.getCentroidIdx()));
            }
        }
 
    }


}
