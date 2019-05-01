package amaljoyc.geoloc.service.core;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by amaljoyc on 01.05.19.
 */
public class PolygonTest {

    @Test
    public void testTriangle() {
        List<Point> vertices = Arrays.asList(
                new Point(1.5, 1.5),
                new Point(9.5, 1.5),
                new Point(4, 6),
                new Point(1.5, 1.5)
        );
        Polygon triangle = Polygon.create(vertices);

        Point testPointInside = new Point(4, 3);
        Point testPointOutsideLeft = new Point(2, 5);
        Point testPointOutsideRight = new Point(8, 5);

        assertTrue(triangle.contains(testPointInside));

        assertFalse(triangle.contains(testPointOutsideLeft));
        assertFalse(triangle.contains(testPointOutsideRight));
    }

    @Test
    public void testSquare() {
        List<Point> vertices = Arrays.asList(
                new Point(2.5, 1),
                new Point(10, 1),
                new Point(10, 8),
                new Point(2.5, 8),
                new Point(2.5, 1)
        );
        Polygon square = Polygon.create(vertices);

        Point testPointInside = new Point(5, 5);
        Point testPointOnEdge = new Point(2.5, 2.5);
        Point testPointOutsideLeft = new Point(1, 7);
        Point testPointOutsideTop = new Point(6.5, 9.5);

        assertTrue(square.contains(testPointInside));
        assertTrue(square.contains(testPointOnEdge));

        assertFalse(square.contains(testPointOutsideLeft));
        assertFalse(square.contains(testPointOutsideTop));
    }

    @Test
    public void testPentagon() {
        List<Point> vertices = Arrays.asList(
                new Point(1.5, 5),
                new Point(3.5, 2),
                new Point(6, 2),
                new Point(7.5, 5.5),
                new Point(3.5, 8),
                new Point(1.5, 5)
        );
        Polygon pentagon = Polygon.create(vertices);

        Point testPointInside = new Point(3.5, 6.5);
        Point testPointOnEdge = new Point(4.5, 2);
        Point testPointOutsideLeft = new Point(1, 2.5);
        Point testPointOutsideDown = new Point(4.5, 0.5);

        assertTrue(pentagon.contains(testPointInside));
        assertTrue(pentagon.contains(testPointOnEdge));

        assertFalse(pentagon.contains(testPointOutsideLeft));
        assertFalse(pentagon.contains(testPointOutsideDown));
    }

    @Test
    public void testNonConvexPolygon() {
        List<Point> vertices = Arrays.asList(
                new Point(2.5, 2),
                new Point(9.5, 2),
                new Point(8, 6.5),
                new Point(6, 5),
                new Point(4.5, 7.5),
                new Point(2.5, 2)
        );
        Polygon nonConvexPolygon = Polygon.create(vertices);

        Point testPointInsideLeft = new Point(4.5, 6);
        Point testPointInsideRight = new Point(8, 4.5);
        Point testPointOnVertex = new Point(6, 5);
        Point testPointOutsideLeft = new Point(1.5, 5.5);

        assertTrue(nonConvexPolygon.contains(testPointInsideLeft));
        assertTrue(nonConvexPolygon.contains(testPointInsideRight));
        assertTrue(nonConvexPolygon.contains(testPointOnVertex));

        assertFalse(nonConvexPolygon.contains(testPointOutsideLeft));
    }
}
