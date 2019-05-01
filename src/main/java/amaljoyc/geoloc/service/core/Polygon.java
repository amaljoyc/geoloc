package amaljoyc.geoloc.service.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by amaljoyc on 30.04.19.
 *
 *  Represents a Polygon with a list of LineSegments as its edges.
 */
@Data
public class Polygon {

    private List<LineSegment> edges;
    private List<Point> vertices;
    private BoundingBox boundingBox;

    private Polygon() {
        this.edges = new ArrayList<>();
        this.boundingBox = new BoundingBox();
    }

    /**
     *
     * @param point
     * @return whether the given Point is inside the Polygon or not based on,
     *              1) if either count of intersections is odd
     *              2) or point lies on an edge of polygon
     */
    public boolean contains(Point point) {
        LineSegment ray = castRay(point);
        int totalIntersections = 0;

        for (LineSegment edge : edges) {
            if (edge.contains(point)) {
                return true; // The point lies on an edge of polygon
            }

            if (hasIntersection(edge, ray)) {
                totalIntersections++;
            }
        }

        return totalIntersections % 2 != 0; // count of intersections is odd
    }

    /**
     *
     * @param vertices
     * @return a new and fully built Polygon
     */
    public static Polygon build(List<Point> vertices) {
        Polygon polygon = new Polygon();
        polygon.vertices = vertices;

        for (int i = 0; i < vertices.size() - 1; i++) {
            LineSegment lineSegment = new LineSegment(vertices.get(i), vertices.get(i + 1));
            polygon.edges.add(lineSegment);
            polygon.boundingBox.resizeAfterVertex(vertices.get(i));
        }

        return polygon;
    }

    private LineSegment castRay(Point startPoint) {
        Point endPoint = new Point(boundingBox.getMaxX(), startPoint.getYCoordinate());
        return new LineSegment(startPoint, endPoint);
    }

    private boolean hasIntersection(LineSegment edge, LineSegment ray) {
        // @TODO
        return true;
    }
}
