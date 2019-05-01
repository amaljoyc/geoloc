package amaljoyc.geoloc.service.core;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by amaljoyc on 30.04.19.
 */
public class LineSegmentTest {

    @Test
    public void testPointOnHorizontalLineSegment() {
        Point testPoint = new Point(2, 1);
        Point startPoint = new Point(1, 1);
        Point endPoint = new Point(4, 1);
        LineSegment lineSegment = LineSegment.create(startPoint, endPoint);

        assertTrue(lineSegment.contains(testPoint));
    }

    @Test
    public void testPointOutsideHorizontalLineSegment() {
        Point testPoint = new Point(2, 3);
        Point startPoint = new Point(1, 1);
        Point endPoint = new Point(4, 1);
        LineSegment lineSegment = LineSegment.create(startPoint, endPoint);

        assertFalse(lineSegment.contains(testPoint));
    }

    @Test
    public void testPointOnSlantingLineSegment() {
        Point testPoint = new Point(3, 3);
        Point startPoint = new Point(1, 1);
        Point endPoint = new Point(4, 4);
        LineSegment lineSegment = LineSegment.create(startPoint, endPoint);

        assertTrue(lineSegment.contains(testPoint));
    }

    @Test
    public void testPointOutsideSlantingLineSegment() {
        Point testPoint = new Point(3.5123123d, 1.51234234d);
        Point startPoint = new Point(1.124242d, 1.124242d);
        Point endPoint = new Point(4.9365232d, 4.9365232d);
        LineSegment lineSegment = LineSegment.create(startPoint, endPoint);

        assertFalse(lineSegment.contains(testPoint));
    }

    @Test
    public void testPointOnPositiveNegativeLineSegment() {
        Point testPoint = new Point(2, -1);
        Point startPoint = new Point(1, -1);
        Point endPoint = new Point(3, -1);
        LineSegment lineSegment = LineSegment.create(startPoint, endPoint);

        assertTrue(lineSegment.contains(testPoint));
    }

    @Test
    public void testPointOutsidePositiveNegativeLineSegment() {
        Point testPoint = new Point(2, -2.5);
        Point startPoint = new Point(1, -1);
        Point endPoint = new Point(3, -1);
        LineSegment lineSegment = LineSegment.create(startPoint, endPoint);

        assertFalse(lineSegment.contains(testPoint));
    }

    @Test
    public void testPointOnNegativeOnlyLineSegment() {
        Point testPoint = new Point(-1.5, -3.5);
        Point startPoint = new Point(-1.5, -1.5);
        Point endPoint = new Point(-1.5, -4.5);
        LineSegment lineSegment = LineSegment.create(startPoint, endPoint);

        assertTrue(lineSegment.contains(testPoint));
    }

    @Test
    public void testPointOutsideNegativeOnlyLineSegment() {
        Point testPoint = new Point(-3, -4);
        Point startPoint = new Point(-1.5, -1.5);
        Point endPoint = new Point(-1.5, -4.5);
        LineSegment lineSegment = LineSegment.create(startPoint, endPoint);

        assertFalse(lineSegment.contains(testPoint));
    }
}
