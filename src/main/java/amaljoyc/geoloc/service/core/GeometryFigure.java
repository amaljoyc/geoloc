package amaljoyc.geoloc.service.core;

/**
 * Created by amaljoyc on 02.05.19.
 */
public interface GeometryFigure {

    /**
     * checks and confirms (True / False) if the GeometryFigure contains the given Point.
     * @param point
     * @return
     */
    boolean contains(Point point);
}
