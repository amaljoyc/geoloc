package amaljoyc.geoloc.service.core;

import lombok.Data;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by amaljoyc on 30.04.19.
 *
 *  Represents the smallest enclosing box for a Polygon
 */
@Data
public class BoundingBox {

    private double minX = Double.MAX_VALUE;
    private double maxX = -Double.MAX_VALUE;
    private double minY = Double.MAX_VALUE;
    private double maxY = -Double.MAX_VALUE;

    public void resizeAfterVertex(Point vertex) {
        minX = min(minX, vertex.getXCoordinate());
        maxX = max(maxX, vertex.getXCoordinate());
        minY = min(minY, vertex.getYCoordinate());
        maxY = max(maxY, vertex.getYCoordinate());
    }
}
