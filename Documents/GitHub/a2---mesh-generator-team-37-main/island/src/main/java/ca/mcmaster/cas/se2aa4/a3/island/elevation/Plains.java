package ca.mcmaster.cas.se2aa4.a3.island.elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Properties.Properties;

public class Plains extends Elevation {
    
    public Plains() {
        lowColor = Properties.getLandLowProps();
        mediumColor = Properties.getLandMediumProps();
        highColor = Properties.getLandHighProps();
       
    }

    public Structs.Mesh addElevation(Structs.Mesh mesh) {
        return super.addElevation(mesh);
    }


}
