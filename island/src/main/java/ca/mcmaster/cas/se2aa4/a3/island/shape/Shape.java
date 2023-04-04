package ca.mcmaster.cas.se2aa4.a3.island.shape;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public abstract class Shape {
    protected Structs.Property land;
    protected Structs.Property ocean;

    public abstract boolean contains(Structs.Vertex vertex);

    public Structs.Mesh build(Structs.Mesh aMesh) {
        Structs.Mesh.Builder iMesh = Structs.Mesh.newBuilder();
        iMesh.addAllVertices(aMesh.getVerticesList()); // add al vertices of old mesh to new mesh
        iMesh.addAllSegments(aMesh.getSegmentsList()); // add al segments of old mesh to new mesh

        for (Structs.Polygon poly : aMesh.getPolygonsList()) { // Iterate through all polygons in old mesh
            Structs.Vertex centroid = aMesh.getVertices(poly.getCentroidIdx()); // Get centroid of current polygon
            Structs.Polygon.Builder p = Structs.Polygon.newBuilder(poly); // Create a polygon builder for current polygon

            // Check if centroid of current polygon contained within shape using contains
            // if centroid is contained then add land property to the polygon builder else add ocean
            if (contains(centroid))
                p.addProperties(land);
            else
                p.addProperties(ocean);

            iMesh.addPolygons(p); // Add the polygon builder to the new mesh
        }
        return iMesh.build(); // Returns new mesh
    }
}
