package amaljoyc.geoloc.service.polygon.dump;

import lombok.Data;

/**
 * Created by amaljoyc on 01.05.19.
 */
@Data
public class Geometry {

    private String type;
    private double[][][] coordinates;
}
