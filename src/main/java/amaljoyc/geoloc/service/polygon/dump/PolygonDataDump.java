package amaljoyc.geoloc.service.polygon.dump;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by amaljoyc on 01.05.19.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolygonDataDump {

    @JsonProperty("_id")
    private String id;

    private Geometry geometry;
}
