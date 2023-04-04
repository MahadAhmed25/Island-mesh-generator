package ca.mcmaster.cas.se2aa4.a3.island.lakes;

import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Properties.Properties;

public class Lakes {
    private Structs.Mesh aMesh;
    private Random random;

    public Lakes(Structs.Mesh iMesh) {
        this.aMesh = iMesh;
        random = new Random();
    }

    public Structs.Mesh generateLakes(int numLakes) {
        Structs.Mesh.Builder iMesh = Structs.Mesh.newBuilder();
        iMesh.addAllVertices(aMesh.getVerticesList());
        iMesh.addAllSegments(aMesh.getSegmentsList());
        iMesh.addAllPolygons(aMesh.getPolygonsList());

        Structs.Property lakes = Properties.getLakeProps();
        Structs.Property humid_lake = Properties.getLakeHumidityProps();
        Structs.Property no_humid = Properties.get_normHumidityProps();

        if (numLakes > aMesh.getPolygonsCount()) {
            throw new IllegalArgumentException("Not possible to have more lakes than polygons");
        }

        int randomPolyIdx = random.nextInt(aMesh.getPolygonsCount());
        while (numLakes > 0) {
            if (aMesh.getPolygons(randomPolyIdx).getProperties(0).getValue() == Properties.landColors) {
                Structs.Polygon randomPoly = aMesh.getPolygons(randomPolyIdx); // Get polygon at the random index
                Structs.Polygon.Builder p = Structs.Polygon.newBuilder(randomPoly);
                p.setProperties(0, lakes);
                // p.addProperties(Properties.getLakeHumidityProps());
                numLakes--;
                iMesh.setPolygons(randomPolyIdx, p);
            }
            randomPolyIdx = random.nextInt(aMesh.getPolygonsCount());
        }

        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {
            Structs.Polygon.Builder p = Structs.Polygon.newBuilder(iMesh.getPolygons(i));
            for (int j = 0; j < p.getNeighborIdxsCount(); j++) {
                int neighborIndex = p.getNeighborIdxs(j);
                Structs.Polygon.Builder neighbor = Structs.Polygon.newBuilder(iMesh.getPolygons(neighborIndex));

                if (neighbor.getProperties(0).getValue() == Properties.lakeColors) {
                    p.addProperties(humid_lake);
                    break;
                }

                else if (j == p.getNeighborIdxsCount() - 1) {
                    p.addProperties(no_humid);
                }
            }
            iMesh.setPolygons(i, p);
        }
        return iMesh.build();
    }
}