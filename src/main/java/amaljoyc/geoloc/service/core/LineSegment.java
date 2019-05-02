package amaljoyc.geoloc.service.core;

import amaljoyc.geoloc.exception.IllegalLineSegmentException;
import lombok.Data;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by amaljoyc on 30.04.19.
 *
 *  Represents a LineSegment with fixed start and end Points
 */
@Data
public class LineSegment implements GeometryFigure {

    private Point startPoint;
    private Point endPoint;

    private double slope; // `m = (y2 - y1) / (x2 - x1)` where m is the slope.
    private double yIntercept; // `y = mx + b` where b is the y-intercept.

    private boolean isVertical;
    private boolean isHorizontal;

    private LineSegment() {
    }

    private LineSegment(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    /**
     *
     * @param startPoint
     * @param endPoint
     * @return a new and fully built LineSegment
     */
    public static LineSegment create(Point startPoint, Point endPoint) {
        validateEndPoints(startPoint, endPoint);

        LineSegment lineSegment = new LineSegment(startPoint, endPoint);

        lineSegment.isVertical = startPoint.getXCoordinate() == endPoint.getXCoordinate();
        lineSegment.isHorizontal = startPoint.getYCoordinate() == endPoint.getYCoordinate();

        if (!lineSegment.isVertical) { // for a vertical line, slope would be infinity, hence skipping.
            lineSegment.slope = (endPoint.getYCoordinate() - startPoint.getYCoordinate()) /
                    (endPoint.getXCoordinate() - startPoint.getXCoordinate());
            lineSegment.yIntercept = startPoint.getYCoordinate() - (lineSegment.slope * startPoint.getXCoordinate());
        }

        return lineSegment;
    }

    private static void validateEndPoints(Point startPoint, Point endPoint) {
        if (startPoint.equals(endPoint)) {
            throw new IllegalLineSegmentException();
        }
    }

    /**
     *
     * @param point
     * @return whether the given Point lies on the LineSegment or not
     *              True  --> Point is ON LineSegment
     *              False --> Point is NOT ON LineSegment
     */
    public boolean contains(Point point) {
        return isBetweenSegmentEndPoints(point) && isOnLine(point);
    }

    /**
     *
     * @param point
     * @return whether the given Point is between the startPoint & endPoint or not
     */
    private boolean isBetweenSegmentEndPoints(Point point) {
        double minX = min(startPoint.getXCoordinate(), endPoint.getXCoordinate());
        double minY = min(startPoint.getYCoordinate(), endPoint.getYCoordinate());

        double maxX = max(startPoint.getXCoordinate(), endPoint.getXCoordinate());
        double maxY = max(startPoint.getYCoordinate(), endPoint.getYCoordinate());

        return (point.getXCoordinate() >= minX && point.getXCoordinate() <= maxX)
                && (point.getYCoordinate() >= minY && point.getYCoordinate() <= maxY);
    }

    /**
     *
     * @param point
     * @return whether the Point is on line or not
     *
     * The equation of a non-vertical line passing through two points A(x1,y1) and B(x2,y2) is given by
     *         (x−x1) / (x2−x1) = (y−y1) / (y2−y1)
     *      where a point 𝑃=(𝑥,𝑦) falls on this line.
     *
     * Read more: https://www.emathzone.com/tutorials/geometry/two-points-form-equation-of-a-line.html#ixzz5mb4SvOFc
     *
     * ie, value = (𝑥−𝑥1)(𝑦2−𝑦1) − (𝑦−𝑦1)(𝑥2−𝑥1) and if value = 0 then the point lies exactly on the line.
     */
    private boolean isOnLine(Point point) {
        if (isVertical) {
            return point.getXCoordinate() == startPoint.getXCoordinate();
        } else if (isHorizontal) {
            return point.getYCoordinate() == startPoint.getYCoordinate();
        } else {
            double leftOperand = (point.getXCoordinate() - startPoint.getXCoordinate()) *
                    (endPoint.getYCoordinate() - startPoint.getYCoordinate());
            double rightOperand = (point.getYCoordinate() - startPoint.getYCoordinate()) *
                    (endPoint.getXCoordinate() - startPoint.getXCoordinate());
            int value = (int) (leftOperand - rightOperand);
            return value == 0;
        }
    }

    public boolean isNotVertical() {
        return !isVertical;
    }
}
