package amaljoyc.geoloc.service.core;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by amaljoyc on 30.04.19.
 *
 * Represents a Point with x & y coordinates.
 */
@Data
@AllArgsConstructor
public class Point {

    private double xCoordinate;
    private double yCoordinate;
}
