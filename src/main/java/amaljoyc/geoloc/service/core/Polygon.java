package amaljoyc.geoloc.service.core;

import amaljoyc.geoloc.exception.IllegalPolygonException;
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
     * @param vertices
     * @return a new and fully built Polygon
     */
    public static Polygon create(List<Point> vertices) {
        validateVertices(vertices);

        Polygon polygon = new Polygon();
        polygon.vertices = vertices;

        for (int i = 0; i < vertices.size() - 1; i++) {
            LineSegment lineSegment = LineSegment.create(vertices.get(i), vertices.get(i + 1));
            polygon.edges.add(lineSegment);
            polygon.boundingBox.resizeAfterVertex(vertices.get(i));
        }

        return polygon;
    }

    private static void validateVertices(List<Point> vertices) {
        int verticesCount = vertices.size();
        if (verticesCount < 4) {
            throw new IllegalPolygonException("Number of coordinates are less than 4 in count");
        } else if (!vertices.get(0).equals(vertices.get(verticesCount - 1))) {
            throw new IllegalPolygonException("First & Last vertices are not the same coordinates");
        }
    }

    /**
     *
     * @param point
     * @return whether the given Point is inside the Polygon or not based on,
     *              1) if either count of intersections is odd
     *              2) or point lies on an edge of polygon
     *
     *         True  --> Point is INSIDE / ON the Polygon
     *         False --> Point is OUTSIDE the Polygon
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

    private LineSegment castRay(Point startPoint) {
        Point endPoint = new Point(boundingBox.getMaxX(), startPoint.getYCoordinate());
        return LineSegment.create(startPoint, endPoint);
    }

    private boolean hasIntersection(LineSegment edge, LineSegment ray) {
        double xCoordinate;
        double yCoordinate;

        if (edge.isVertical() && ray.isNotVertical()) {
            xCoordinate = edge.getStartPoint().getXCoordinate();
            yCoordinate = calculateIntersectionYCoordinate(ray, xCoordinate);
        } else if (edge.isNotVertical() && ray.isVertical()) {
            xCoordinate = ray.getStartPoint().getXCoordinate();
            yCoordinate = calculateIntersectionYCoordinate(edge, xCoordinate);
        } else if (edge.isNotVertical() && ray.isNotVertical()) {
            if (isParallel(edge, ray)) { // no intersection for parallel lines
                return false;
            }
            xCoordinate = calculateIntersectionXCoordinate(edge, ray);
            yCoordinate = calculateIntersectionYCoordinate(edge, xCoordinate);
        } else { // both are vertical which results in parallel lines and hence no intersection
            return false;
        }

        Point intersectionPoint = new Point(xCoordinate, yCoordinate);

        // the above intersection is for the extended line (and not the provided line-segment)
        // now checking if it intersects within the provided segments
        return edge.contains(intersectionPoint) && ray.contains(intersectionPoint);
    }

    private double calculateIntersectionYCoordinate(LineSegment line, double intersectionXCoordinate) {
        return (line.getSlope() * intersectionXCoordinate) + line.getYIntercept(); // y = mx + b
    }

    private double calculateIntersectionXCoordinate(LineSegment edge, LineSegment ray) {
        return (ray.getYIntercept() - edge.getYIntercept()) / (edge.getSlope() - ray.getSlope()); // (b2-b1) / (m1-m2)
    }

    private boolean isParallel(LineSegment edge, LineSegment ray) {
        return edge.getSlope() == ray.getSlope();
    }
}
