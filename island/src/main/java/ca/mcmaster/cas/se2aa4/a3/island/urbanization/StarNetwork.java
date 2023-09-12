package ca.mcmaster.cas.se2aa4.a3.island.urbanization;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.GraphShortestPath;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.PathFinder;


public class StarNetwork {
    
    Graph graph;
    Structs.Mesh aMesh;
    List<Integer> cityNodes;
    

    public StarNetwork(Structs.Mesh mesh){
        this.aMesh = mesh;
        this.graph = new Graphable(aMesh).convert();
        this.cityNodes = new ArrayList<>();
        findCities();
    }
        
   

    public void findCities(){

        for(Structs.Polygon p: aMesh.getPolygonsList()){
            if(aMesh.getVertices(p.getCentroidIdx()).getPropertiesCount() == 1){
                cityNodes.add(p.getCentroidIdx());
            }
        }
        System.out.println(cityNodes.toString());
    }

    public Structs.Mesh connectRoads(){

        Structs.Property roadd = Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue("0,255,255")
                .build();

        PathFinder path = new GraphShortestPath(graph);

        Structs.Mesh.Builder cloneMesh = Structs.Mesh.newBuilder();
        cloneMesh.addAllVertices(aMesh.getVerticesList());
        cloneMesh.addAllSegments(aMesh.getSegmentsList());
        cloneMesh.addAllPolygons(aMesh.getPolygonsList());

        //FIXME
        List<Node> l = path.findPath(graph.getNode(cityNodes.get(0)), graph.getNode(cityNodes.get(1)));
          
        System.out.println("TEST: SHORTEST PATH");
        for(Node n: l){ 
            System.out.println(n.getId());
            Structs.Vertex.Builder c = Structs.Vertex.newBuilder(aMesh.getVertices(n.getId()));
            c.addProperties(roadd);
            cloneMesh.setVertices(n.getId(), c);
        }
        System.out.println("TEST: SHORTEST PATH");

        return cloneMesh.build();

    }

}
