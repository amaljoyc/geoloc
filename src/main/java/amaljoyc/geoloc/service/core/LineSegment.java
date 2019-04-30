package amaljoyc.geoloc.service.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by amaljoyc on 30.04.19.
 *
 *  Represents a LineSegment with fixed start and end Points
 */
@Data
@AllArgsConstructor
public class LineSegment {

    private Point startPoint;
    private Point endPoint;

    /**
     *
     * @param point
     * @return whether the given Point lies on the LineSegment or not
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
        if (isVertical()) {
            return point.getXCoordinate() == startPoint.getXCoordinate();
        } else if (isHorizontal()) {
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

    private boolean isVertical() {
        return startPoint.getXCoordinate() == endPoint.getXCoordinate();
    }

    private boolean isHorizontal() {
        return startPoint.getYCoordinate() == endPoint.getYCoordinate();
    }
}
