package ca.mcmaster.cas.se2aa4.a3.island.elevation;


import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public abstract class Mountain {
    
    protected Structs.Property tier1;
    protected Structs.Property tier2;
    protected Structs.Property tier3;
    protected Structs.Property tier4;

    public abstract int highestPeak(Structs.Mesh mesh);


    public Structs.Mesh build(Structs.Mesh aMesh){

        Structs.Mesh.Builder iMesh = Structs.Mesh.newBuilder();
        iMesh.addAllVertices(aMesh.getVerticesList());
        iMesh.addAllSegments(aMesh.getSegmentsList());
        int index = highestPeak(aMesh);

        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {
            Structs.Polygon.Builder p = Structs.Polygon.newBuilder(aMesh.getPolygons(i));
    
            if (i == index) {
                p.setProperties(0, tier1);
            } else if (aMesh.getPolygons(index).getNeighborIdxsList().contains(i)) {
                p.setProperties(0, tier2);
            }
    
            iMesh.addPolygons(p.build());
        }

        neighborRing(tier3, tier2, tier1, aMesh, iMesh);
        neighborRing(tier4, tier3, tier2, aMesh, iMesh);
        return iMesh.build();
    }

    public void neighborRing(Structs.Property tier, Structs.Property tierSub1, Structs.Property tierSub2, Structs.Mesh aMesh, Structs.Mesh.Builder iMesh ){
        // Loop through all polygons in the mesh again
        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {
            Structs.Polygon.Builder p = Structs.Polygon.newBuilder(iMesh.getPolygons(i));
    
            // If the current polygon is a tier -1 polygon, loop through its neighbors
            if (p.getProperties(0).getValue().equals(tierSub1.getValue())) {
                for (int j = 0; j < p.getNeighborIdxsCount(); j++) {
                    int neighborIndex = p.getNeighborIdxs(j);
                    Structs.Polygon.Builder neighbor = Structs.Polygon.newBuilder(iMesh.getPolygons(neighborIndex));
    
                    // If the neighbor is not already a tier -2  or tier -1 polygon, set its property to tier
                    if (!neighbor.getProperties(0).getValue().equals(tierSub1.getValue()) && 
                        !neighbor.getProperties(0).getValue().equals(tierSub2.getValue())) {
                        neighbor.setProperties(0, tier);
                        iMesh.setPolygons(neighborIndex, neighbor.build());
                    }
                }
            }

        }
    }

}
