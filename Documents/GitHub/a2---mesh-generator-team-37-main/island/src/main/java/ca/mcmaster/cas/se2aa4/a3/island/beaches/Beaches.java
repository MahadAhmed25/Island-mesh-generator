package ca.mcmaster.cas.se2aa4.a3.island.beaches;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Properties.Properties;

public class Beaches {
    private Structs.Mesh aMesh;

    public Beaches(Structs.Mesh iMesh) {
        this.aMesh = iMesh;
    }

    public Structs.Mesh enrichBeaches() {
        Structs.Mesh.Builder iMesh = Structs.Mesh.newBuilder();
        iMesh.addAllVertices(aMesh.getVerticesList());
        iMesh.addAllSegments(aMesh.getSegmentsList());

        Structs.Property sand = Properties.getBeachProps();

        for (Structs.Polygon poly : aMesh.getPolygonsList()) {
            Structs.Polygon.Builder x = Structs.Polygon.newBuilder(poly);
            for (int idx : poly.getNeighborIdxsList()) {
                Structs.Polygon neighborPoly = aMesh.getPolygons(idx);
                if (shouldAddBeachProperties(poly, neighborPoly)) {
                    x.setProperties(0, sand);
                }
            }
            iMesh.addPolygons(x);
        }
        return iMesh.build();
    }

    private boolean shouldAddBeachProperties(Structs.Polygon poly, Structs.Polygon neighborPoly) {
        return poly.getProperties(0).getValue().equals("122,171,135") &&
                (neighborPoly.getProperties(0).getValue().equals(Properties.lagoonColors) || neighborPoly.getProperties(0).getValue().equals(Properties.oceanColors));
    }
}