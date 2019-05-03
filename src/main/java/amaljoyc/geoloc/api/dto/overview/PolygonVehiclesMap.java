package amaljoyc.geoloc.api.dto.overview;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by amaljoyc on 02.05.19.
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class PolygonVehiclesMap {

    private String polygonId;

    @JsonProperty("VINs")
    private List<String> vins;
}
