package ca.mcmaster.cas.se2aa4.a3.island.islandgen;

import java.io.IOException;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class SeedGen {
    public void saveMesh(long seed, Structs.Mesh mesh) throws IOException {
        String seedStr = "img/" + String.valueOf(seed) + ".mesh";
        new MeshFactory().write(mesh, seedStr);
    }

    public Structs.Mesh getMesh(long seed) throws IOException {
        String seedStr = "img/" + String.valueOf(seed) + ".mesh";
        return new MeshFactory().read(seedStr);
    }
}
