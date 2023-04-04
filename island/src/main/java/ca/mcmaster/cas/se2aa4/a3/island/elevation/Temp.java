package ca.mcmaster.cas.se2aa4.a3.island.elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Properties.Properties;

public class Temp {
    private Structs.Mesh aMesh;

    public Temp(Structs.Mesh iMesh) {
        this.aMesh = iMesh;
    }

    public Structs.Mesh enrichTemp() {
        Structs.Mesh.Builder iMesh = Structs.Mesh.newBuilder();
        iMesh.addAllVertices(aMesh.getVerticesList());
        iMesh.addAllSegments(aMesh.getSegmentsList());
        iMesh.addAllPolygons(aMesh.getPolygonsList());

        Structs.Property mid = Properties.get_temp_temperature();
        Structs.Property warm = Properties.get_hot_temperature();
        Structs.Property new_cold = Properties.get_cold_temperature();
        Structs.Property other = Properties.get_other_temperature();

        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {
            Structs.Polygon.Builder p = Structs.Polygon.newBuilder(iMesh.getPolygons(i));

            if (p.getProperties(3).getValue().equals("LOW")) {
                p.addProperties(warm);
            }
            else if (p.getProperties(3).getValue().equals("MEDIUM")){
                p.addProperties(mid);
            }
            else if (p.getProperties(3).getValue().equals("HIGH")){
                p.addProperties(new_cold);
            }

            else{
                p.addProperties(other);
            }
             
            
            iMesh.setPolygons(i, p);
        }


        return iMesh.build();
    }

    
}
