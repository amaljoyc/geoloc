package amaljoyc.geoloc.service.polygon;

import amaljoyc.geoloc.service.core.Point;
import lombok.Data;

import java.util.List;

/**
 * Created by amaljoyc on 01.05.19.
 */
@Data
public class PolygonData {

    private String id;
    List<Point> vertices;

    public PolygonData(String id, List<Point> vertices) {
        this.id = id;
        this.vertices = vertices;
    }
}
