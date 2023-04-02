import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.islandgen.MeshDimension;

public class MeshDimensionTest{

    @Test
    public void testComputeDimensionsWithGeneralCoordinates(){
        Set<Vertex> vertices = new HashSet<>();
        vertices.add(Vertex.newBuilder().setX(1).setY(2).build());
        vertices.add(Vertex.newBuilder().setX(4).setY(3).build());
        vertices.add(Vertex.newBuilder().setX(2).setY(5).build());
        vertices.add(Vertex.newBuilder().setX(6).setY(1).build());
        Structs.Mesh Mockmesh = Mesh.newBuilder().addAllVertices(vertices).build();

        MeshDimension dim = new MeshDimension(Mockmesh);
        assertEquals(6, dim.maxX);
        assertEquals(5, dim.maxY);
    }

    @Test
    public void testComputeDimensionsWithSingleVertexMesh() {
        Structs.Mesh singleVertexMesh = Mesh.newBuilder()
                .addVertices(Vertex.newBuilder().setX(1).setY(2).build())
                .build();
        MeshDimension dim = new MeshDimension(singleVertexMesh);
        assertEquals(1.0, dim.maxX, 0.0);
        assertEquals(2.0, dim.maxY, 0.0);
    }

    @Test
    public void testComputeDimensionsWithMeshContainingDuplicateVertices() {
        Structs.Mesh meshWithDuplicates = Mesh.newBuilder()
                .addVertices(Vertex.newBuilder().setX(1).setY(2).build())
                .addVertices(Vertex.newBuilder().setX(4).setY(3).build())
                .addVertices(Vertex.newBuilder().setX(2).setY(5).build())
                .addVertices(Vertex.newBuilder().setX(6).setY(1).build())
                .addVertices(Vertex.newBuilder().setX(4).setY(3).build())
                .build();
        MeshDimension dim = new MeshDimension(meshWithDuplicates);
        assertEquals(6, dim.maxX);
        assertEquals(5, dim.maxY);
    }

    @Test
    public void testComputeDimensionsWithMeshContainingNegativeCoordinates() {
        Structs.Mesh meshWithNegativeCoordinates = Mesh.newBuilder()
                .addVertices(Vertex.newBuilder().setX(-1).setY(-2).build())
                .addVertices(Vertex.newBuilder().setX(4).setY(3).build())
                .addVertices(Vertex.newBuilder().setX(2).setY(5).build())
                .addVertices(Vertex.newBuilder().setX(6).setY(1).build())
                .build();
        MeshDimension dim = new MeshDimension(meshWithNegativeCoordinates);
        assertEquals(6, dim.maxX);
        assertEquals(5, dim.maxY);
    }

}