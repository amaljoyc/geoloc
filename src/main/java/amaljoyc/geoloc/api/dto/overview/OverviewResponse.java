package amaljoyc.geoloc.api.dto.overview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by amaljoyc on 02.05.19.
 */
@Getter
@Setter
@AllArgsConstructor
public class OverviewResponse {

    private List<PolygonVehiclesMap> carsInsidePolygon;
    private List<String> carsOutsidePolygon;
    private List<String> emptyPolygons;
}
