package shapes;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.shape.Circle;

public class CircleTest {
    private Circle circle;

    @Before
    public void setUp() {
        double maxX = 100.0;
        double maxY = 200.0;
        circle = new Circle(maxX, maxY);
    }

    @Test
    public void testContainsInside() {
        Structs.Vertex v = Structs.Vertex.newBuilder().setX(50.0).setY(100.0).build();
        assertTrue(circle.contains(v));
    }

    @Test
    public void testContainsOutside() {
        Structs.Vertex v = Structs.Vertex.newBuilder().setX(150.0).setY(300.0).build();
        assertFalse(circle.contains(v));
    }
}