package amaljoyc.geoloc.service.vehicles.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by amaljoyc on 01.05.19.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalVehicleData {

    private String vin;
    private VehiclePosition position;
}
