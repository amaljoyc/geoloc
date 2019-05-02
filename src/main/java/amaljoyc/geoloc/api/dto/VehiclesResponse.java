package amaljoyc.geoloc.api.dto;

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
public class VehiclesResponse {

    private List<String> vins;
}
