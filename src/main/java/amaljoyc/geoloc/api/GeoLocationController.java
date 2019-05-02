package amaljoyc.geoloc.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static amaljoyc.geoloc.api.util.SwaggerConstants.*;

/**
 * Created by amaljoyc on 02.05.19.
 */
@RestController
@RequestMapping("/v1")
@Api("/v1")
@Slf4j
public class GeoLocationController {

    @GetMapping("/cars/{polygonId}")
    @ApiOperation(value = "Get all cars present inside a polygon with given polygonId",
            response = String.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_STATUS_200, message = "<List of VINs corresponding to each car>"),
            @ApiResponse(code = HTTP_STATUS_400, message = "Bad request received with invalid data"),
            @ApiResponse(code = HTTP_STATUS_404, message = "Polygon Not Found"),
            @ApiResponse(code = HTTP_STATUS_500, message = "Server Error")
    })
    public List<String> getCars(@PathVariable("polygonId") String polygonId) {
        log.debug("getCars for polygonId: {}", polygonId);
        return new ArrayList<>(); //@TODO
    }

    @GetMapping("/polygon/{vin}")
    @ApiOperation(value = "Get the polygon which contains the car with given VIN", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_STATUS_200, message = "<The polygonId corresponding to the polygon>"),
            @ApiResponse(code = HTTP_STATUS_400, message = "Bad request received with invalid data"),
            @ApiResponse(code = HTTP_STATUS_404, message = "Car Not Found"),
            @ApiResponse(code = HTTP_STATUS_500, message = "Server Error")
    })
    public String getPolygon(@PathVariable("vin") String vin) {
        log.debug("getPolygon for VIN: {}", vin);
        return "dummy_polygon_id"; //@TODO
    }
}
