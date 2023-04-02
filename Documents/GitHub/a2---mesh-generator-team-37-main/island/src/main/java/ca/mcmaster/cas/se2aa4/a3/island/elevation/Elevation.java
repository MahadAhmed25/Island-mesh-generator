package ca.mcmaster.cas.se2aa4.a3.island.elevation;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Properties.Properties;
import ca.mcmaster.cas.se2aa4.a3.island.islandgen.islandGenerator;

public abstract class Elevation {

    private final Random random = new Random();
    protected Structs.Property lowColor;
    protected Structs.Property mediumColor;
    protected Structs.Property highColor;
    private final Map<String, Structs.Property> elevationToPropertyMap = new HashMap<>();

    public Elevation() {

    }

    public Structs.Mesh addElevation(Structs.Mesh aMesh) {
        elevationToPropertyMap.put("LOW", lowColor);
        elevationToPropertyMap.put("MEDIUM", mediumColor);
        elevationToPropertyMap.put("HIGH", highColor);

        Structs.Mesh.Builder iMesh = Structs.Mesh.newBuilder();
        iMesh.addAllVertices(aMesh.getVerticesList());
        iMesh.addAllSegments(aMesh.getSegmentsList());

        for (Structs.Polygon poly : aMesh.getPolygonsList()) {
            Structs.Polygon.Builder p = Structs.Polygon.newBuilder(poly);
            if (p.getProperties(0).getValue().equals(Properties.landColors)) {
                p.addProperties(randElevation());

                if (islandGenerator.biomeArg.equals("grassland"))
                    p.setProperties(0, elevationToPropertyMap.get(randElevation().getValue()));
            } else
                p.addProperties(Structs.Property.newBuilder().setKey("elevation").setValue("NONE").build());

            iMesh.addPolygons(p);

        }

        return iMesh.build();

    }

    public Structs.Property randElevation() {
        int elevationidx = random.nextInt(Elevate.values().length);
        Elevate elevate = Elevate.values()[elevationidx];
        Structs.Property elevation = Structs.Property.newBuilder()
                .setKey("elevation")
                .setValue(elevate.toString())
                .build();

        return elevation;
    }

}
