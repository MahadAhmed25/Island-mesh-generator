package ca.mcmaster.cas.se2aa4.a3.island.shape;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Properties.Properties;

public class TiltedOval extends Shape {
    private double centerX;
    private double centerY;
    private double majorAxis;
    private double minorAxis;
    private double angle;

    public TiltedOval(double maxX, double maxY) {
        this.centerX = maxX / 2;
        this.centerY = maxY / 2;
        this.majorAxis = maxX * 0.4;
        this.minorAxis = maxY * 0.3;
        this.angle = Math.toRadians(35);

        land = Properties.getLandProps();
        ocean = Properties.getOceanProps();
    }

    public boolean contains(Structs.Vertex v) {
        double xRot = (v.getX() - centerX) * Math.cos(angle) - (v.getY() - centerY) * Math.sin(angle) + centerX;
        double yRot = (v.getX() - centerX) * Math.sin(angle) + (v.getY() - centerY) * Math.cos(angle) + centerY;
        return Math.pow((xRot - centerX) / majorAxis, 2) + Math.pow((yRot - centerY) / minorAxis, 2) <= 1;
    }

    public Structs.Mesh build(Structs.Mesh aMesh) {
        return super.build(aMesh);
    }
}
