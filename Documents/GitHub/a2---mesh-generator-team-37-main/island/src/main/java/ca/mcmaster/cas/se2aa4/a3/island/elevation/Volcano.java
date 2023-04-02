package ca.mcmaster.cas.se2aa4.a3.island.elevation;

import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Properties.Properties;

public class Volcano extends Mountain {

    public Volcano() {
        tier1 = Properties.getVolcanoTier1Props();
        tier2 = Properties.getVolcanoTier2Props();
        tier3 = Properties.getVolcanoTier3Props();
        tier4 = Properties.getVolcanoTier4Props();
    }

    @Override
    public int highestPeak(Structs.Mesh mesh) {
        Random rand = new Random();
        int p = rand.nextInt(mesh.getPolygonsCount());

        while (mesh.getPolygons(p).getProperties(0).getValue() != Properties.landColors)
            p = rand.nextInt(mesh.getPolygonsCount());

        return p;
    }

    public Structs.Mesh build(Structs.Mesh mesh) {
        return super.build(mesh);
    }

}
