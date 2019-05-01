package amaljoyc.geoloc.exception;

/**
 * Created by amaljoyc on 01.05.19.
 */
public class IllegalPolygonException extends RuntimeException {

    private static final long serialVersionUID = 2777387610567370493L;

    public IllegalPolygonException(String reason) {
        super("Provided vertices cannot make a valid Polygon: " + reason);
    }
}
