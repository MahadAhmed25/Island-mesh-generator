import java.io.IOException;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a3.island.islandgen.islandGenerator;

public class Main {
    public static void main(String[] args) throws IOException {
        Configuration config = new Configuration(args);
        Structs.Mesh iMesh = new islandGenerator(config).generate();
        new MeshFactory().write(iMesh, config.output());
    }
}