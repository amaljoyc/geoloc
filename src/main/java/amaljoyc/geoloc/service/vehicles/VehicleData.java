package amaljoyc.geoloc.service.vehicles;

import amaljoyc.geoloc.service.core.Point;
import lombok.Data;

/**
 * Created by amaljoyc on 01.05.19.
 */
@Data
public class VehicleData {

    private String vin;
    private Point location;

    public VehicleData(String vin, double longitude, double latitude) {
        this.vin = vin;
        this.location = new Point(longitude, latitude);
    }
}