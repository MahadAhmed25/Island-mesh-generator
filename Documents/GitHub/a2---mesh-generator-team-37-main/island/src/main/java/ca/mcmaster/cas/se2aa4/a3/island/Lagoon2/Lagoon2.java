
package ca.mcmaster.cas.se2aa4.a3.island.Lagoon2;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Properties.Properties;
import ca.mcmaster.cas.se2aa4.a3.island.islandgen.MeshDimension;

public class Lagoon2 {
    private Structs.Mesh aMesh;
    private double centerX;
    private double centerY;
    private double radius;

    public Lagoon2(Structs.Mesh iMesh) {
        this.aMesh = iMesh;
        MeshDimension dim = new MeshDimension(iMesh);
        this.centerX = dim.maxX / 2;
        this.centerY = dim.maxY / 2;
        this.radius = calculateRadius(dim.maxX, dim.maxY);
    }

    public double calculateRadius(double maxX, double maxY) {
        return (maxX > maxY) ? maxY * 0.2 : maxX * 0.2;
    }

    public Structs.Mesh build() {
        Structs.Mesh.Builder iMesh = Structs.Mesh.newBuilder();
        iMesh.addAllVertices(aMesh.getVerticesList());
        iMesh.addAllSegments(aMesh.getSegmentsList());

        Structs.Property lagoon = Properties.getLagoonProps();

        for (Structs.Polygon poly : aMesh.getPolygonsList()) {
            Structs.Polygon.Builder p = processPolygon(poly, lagoon);
            iMesh.addPolygons(p);
        }
        return iMesh.build();
    }

    public Structs.Polygon.Builder processPolygon(Structs.Polygon poly, Structs.Property lagoon) {
        Structs.Vertex centroid = aMesh.getVertices(poly.getCentroidIdx());
        Structs.Polygon.Builder p = Structs.Polygon.newBuilder(poly);
        if (isWithinLagoon(centroid.getX(), centroid.getY()))
            p.setProperties(0, lagoon);
        return p;
    }

    public boolean isWithinLagoon(double x, double y) {
        return Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) <= radius;
    }
    
}
