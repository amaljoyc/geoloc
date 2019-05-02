package amaljoyc.geoloc.exception;

/**
 * Created by amaljoyc on 02.05.19.
 */
public class PolygonNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5836292092758713652L;

    public PolygonNotFoundException(String polygonId) {
        super("Polygon Not Found for id: " + polygonId);
    }
}
