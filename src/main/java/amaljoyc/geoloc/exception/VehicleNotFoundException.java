package amaljoyc.geoloc.exception;

/**
 * Created by amaljoyc on 02.05.19.
 */
public class VehicleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5973004189622165557L;

    public VehicleNotFoundException(String vin) {
        super("Vehicle Not Found for VIN: " + vin);
    }
}
