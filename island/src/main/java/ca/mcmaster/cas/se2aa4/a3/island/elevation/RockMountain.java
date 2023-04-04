package ca.mcmaster.cas.se2aa4.a3.island.elevation;

import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Properties.Properties;

public class RockMountain extends Mountain {
    
    public RockMountain() {
        tier1 = Properties.getRockMountTier1Props();
        tier2 = Properties.getRockMountTier2Props();
        tier3 = Properties.getRockMountTier3Props();
        tier4 = Properties.getRockMountTier4Props();
    }

    @Override
    public int highestPeak(Structs.Mesh mesh) {
        Random rand = new Random();
        int p = rand.nextInt(mesh.getPolygonsCount());

        // Keep generating new index until on land tile && Not on lake tile
        while (mesh.getPolygons(p).getProperties(0).getValue() != Properties.landColors)
            p = rand.nextInt(mesh.getPolygonsCount());

        return p;
    }

    public Structs.Mesh build(Structs.Mesh mesh) {
        return super.build(mesh);
    }

}
