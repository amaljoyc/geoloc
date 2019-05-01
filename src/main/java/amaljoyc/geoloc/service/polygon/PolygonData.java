package amaljoyc.geoloc.service.polygon;

import amaljoyc.geoloc.service.core.Point;
import amaljoyc.geoloc.service.core.Polygon;
import lombok.Data;

import java.util.List;

/**
 * Created by amaljoyc on 01.05.19.
 */
@Data
public class PolygonData {

    private String id;
    Polygon polygon;

    public PolygonData(String id, List<Point> vertices) {
        this.id = id;
        this.polygon = Polygon.create(vertices);
    }
}
