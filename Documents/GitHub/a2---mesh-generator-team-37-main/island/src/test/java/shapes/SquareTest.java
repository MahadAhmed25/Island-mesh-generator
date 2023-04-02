package shapes;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.shape.Square;

public class SquareTest {
    private Square square;

    @Before
    public void setUp() {
        double maxX = 100.0;
        double maxY = 200.0;
        square = new Square(maxX, maxY);
    }

    @Test
    public void testContainsInside() {
        Structs.Vertex v = Structs.Vertex.newBuilder().setX(50.0).setY(150.0).build();
        assertTrue(square.contains(v));
    }

    @Test
    public void testContainsOutside() {
        Structs.Vertex v = Structs.Vertex.newBuilder().setX(150.0).setY(125.0).build();
        assertFalse(square.contains(v));
    }
}