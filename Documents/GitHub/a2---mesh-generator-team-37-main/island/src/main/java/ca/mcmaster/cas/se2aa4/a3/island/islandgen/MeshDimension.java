package ca.mcmaster.cas.se2aa4.a3.island.islandgen;

import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

public class MeshDimension {
    private Structs.Mesh aMesh;
    public double maxX;
    public double maxY;

    public MeshDimension(Structs.Mesh mesh) {
        this.aMesh = mesh;
        computeDimensions();
    }

    public void computeDimensions() {
        List<Vertex> vertices = aMesh.getVerticesList();
        double[] maxValues = computeMaxValues(vertices);
        maxX = maxValues[0];
        maxY = maxValues[1];
    }

    public double[] computeMaxValues(List<Vertex> vertices) {
        double maxX = vertices.get(0).getX();
        double maxY = vertices.get(0).getY();

        for (int i = 1; i < vertices.size(); i++) {
            if (vertices.get(i).getX() > maxX) {
                maxX = vertices.get(i).getX();
            }
            if (vertices.get(i).getY() > maxY) {
                maxY = vertices.get(i).getY();
            }
        }

        return new double[] { maxX, maxY };
    }
}
