package ca.mcmaster.cas.se2aa4.a3.island.urbanization;

import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Properties.Properties;

public class Cities {

    int numCities;
    Structs.Mesh aMesh;
    Random random = new Random();
    Structs.Property capital = Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue("0,0,0")
                .build();

    Structs.Property town = Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue("128,128,128")
                .build();
            
    
    public Cities(int numCities, Structs.Mesh mesh){ 
        this.numCities = numCities;
        this.aMesh = mesh;
    }   

    public Structs.Mesh addCapital(){

        Structs.Mesh.Builder cloneMesh = cloneMesh();

        Structs.Polygon pickPolygon = aMesh.getPolygons(random.nextInt(aMesh.getPolygonsCount()));
        while(!pickPolygon.getProperties(0).getValue().equals(Properties.landColors)){
            pickPolygon = aMesh.getPolygons(random.nextInt(aMesh.getPolygonsCount()));
        }

        Structs.Vertex.Builder c = Structs.Vertex.newBuilder(aMesh.getVertices(pickPolygon.getCentroidIdx()));
        c.addProperties(capital);
        cloneMesh.setVertices(pickPolygon.getCentroidIdx(), c);
        this.aMesh = cloneMesh.build();

        addCities();

        return aMesh;

    }


    public void addCities(){
    
        Structs.Mesh.Builder cloneMesh = cloneMesh();

        for(int i=0; i<numCities; i++){

            Structs.Polygon pickPolygon = aMesh.getPolygons(random.nextInt(aMesh.getPolygonsCount()));
            while(!pickPolygon.getProperties(0).getValue().equals(Properties.landColors)){
                pickPolygon = aMesh.getPolygons(random.nextInt(aMesh.getPolygonsCount()));
            }

            Structs.Vertex.Builder c = Structs.Vertex.newBuilder(aMesh.getVertices(pickPolygon.getCentroidIdx()));
            c.addProperties(town);
            cloneMesh.setVertices(pickPolygon.getCentroidIdx(), c);
        }

        this.aMesh = cloneMesh.build();

    }


    public Structs.Mesh.Builder cloneMesh(){
        Structs.Mesh.Builder cloneMesh = Structs.Mesh.newBuilder();
        cloneMesh.addAllVertices(aMesh.getVerticesList());
        cloneMesh.addAllSegments(aMesh.getSegmentsList());
        cloneMesh.addAllPolygons(aMesh.getPolygonsList());

        return cloneMesh;
    }

}
