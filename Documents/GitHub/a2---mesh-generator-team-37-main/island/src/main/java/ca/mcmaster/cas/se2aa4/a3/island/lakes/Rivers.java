package ca.mcmaster.cas.se2aa4.a3.island.lakes;

import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Properties.Properties;

public class Rivers {
    private Structs.Mesh aMesh;

    public Rivers(Structs.Mesh iMesh) {
        this.aMesh = iMesh;
        
    }

    public Structs.Mesh generateRivers(int numRivers) {
        Structs.Mesh.Builder iMesh = Structs.Mesh.newBuilder()
            .addAllVertices(aMesh.getVerticesList())
            .addAllSegments(aMesh.getSegmentsList())
            .addAllPolygons(aMesh.getPolygonsList());


        for(int i=0; i<numRivers; i++){
            Structs.Polygon p = findPoly();
            Structs.Segment.Builder s = Structs.Segment.newBuilder(iMesh.getSegments(p.getSegmentIdxsList().get(0)));
            s.addProperties(Properties.getLakeProps());
            iMesh.setSegments(p.getSegmentIdxsList().get(0), s);
        }

        return iMesh.build();
    }

    public Structs.Polygon findPoly(){
        Random random = new Random();

        Structs.Polygon p = aMesh.getPolygons(random.nextInt(aMesh.getPolygonsCount()));

        while(!p.getProperties(0).getValue().equals(Properties.landColors) )
        p = aMesh.getPolygons(random.nextInt(aMesh.getPolygonsCount()));

        return p;
    }
}