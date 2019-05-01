package amaljoyc.geoloc.exception;

/**
 * Created by amaljoyc on 01.05.19.
 */
public class IllegalLineSegmentException extends RuntimeException {

    private static final long serialVersionUID = -724555079653484534L;

    public IllegalLineSegmentException() {
        super("Provided Points cannot make a valid LineSegment as they are referring to same coordinates");
    }
}
