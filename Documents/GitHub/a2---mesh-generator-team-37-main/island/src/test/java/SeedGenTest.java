import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.islandgen.SeedGen;

public class SeedGenTest {
    private Structs.Mesh mockMesh; // Store Mesh object
    private Structs.Mesh mockMesh2; // Store Mesh object
    private SeedGen seedGen; // Stores SeedGen object

    @Before
    public void setUp() throws IOException {
        seedGen = new SeedGen();
        mockMesh = Mesh.newBuilder()
                .addVertices(Vertex.newBuilder().setX(1).setY(2).build())
                .addVertices(Vertex.newBuilder().setX(4).setY(3).build())
                .addVertices(Vertex.newBuilder().setX(2).setY(5).build())
                .addVertices(Vertex.newBuilder().setX(6).setY(1).build())
                .build();
        mockMesh2 = Mesh.newBuilder()
                .addVertices(Vertex.newBuilder().setX(2).setY(1).build())
                .addVertices(Vertex.newBuilder().setX(3).setY(4).build())
                .addVertices(Vertex.newBuilder().setX(5).setY(2).build())
                .addVertices(Vertex.newBuilder().setX(1).setY(6).build())
                .build();
    }

    // Test to see if mesh is saved
    @Test
    public void testSaveMesh() throws IOException {
        long seed = System.currentTimeMillis();
        // save mockMesh with corresponding seed (Should create write the file in the saveMesh but for some reason it keeps saying it doesnt exist)
        seedGen.saveMesh(seed, mockMesh);

        // Make sure fil was created and isnt size 0
        File meshFile = new File("img/" + String.valueOf(seed) + ".mesh");
        assertNotNull(meshFile);
        assertEquals(true, meshFile.exists());
        assertEquals(true, meshFile.length() > 0);
    }

    @Test
    public void testSaveAndGetMesh() throws IOException {
        long seed = System.currentTimeMillis();
        // save mockMesh with corresponding seed (Should create write the file in the saveMesh but for some reason it keeps saying it doesnt exist)
        seedGen.saveMesh(seed, mockMesh);

        // Make sure fil was created and isnt size 0
        File meshFile = new File("img/" + seed + ".mesh");
        assertNotNull(meshFile);
        assertEquals(true, meshFile.exists());
        assertEquals(true, meshFile.length() > 0);

        // Load the Mesh object from the file
        Structs.Mesh loadedMesh = seedGen.getMesh(seed);

        // Ensure that the loaded Mesh object is not null and has the same number of vertices as the original Mesh object
        assertNotNull(loadedMesh);
        assertEquals(mockMesh.getVerticesCount(), loadedMesh.getVerticesCount());
    }

    @After
    public void tearDown() {
        // Delete mesh files created during testing
        File[] meshFiles = new File("img").listFiles((dir, name) -> name.endsWith(".mesh"));
        if (meshFiles != null) {
            for (File meshFile : meshFiles) {
                meshFile.delete();
            }
        }
    }
}