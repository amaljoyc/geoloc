package amaljoyc.geoloc.api;

import amaljoyc.geoloc.api.dto.ErrorResponse;
import amaljoyc.geoloc.api.dto.PolygonResponse;
import amaljoyc.geoloc.api.dto.VehiclesResponse;
import amaljoyc.geoloc.service.QueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private QueryService queryService;

    @GetMapping("/cars/{polygonId}")
    @ApiOperation(value = "Get all cars present inside a polygon with given polygonId",
            response = VehiclesResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_STATUS_200, message = "List of VINs corresponding to each car",
                    response = VehiclesResponse.class),
            @ApiResponse(code = HTTP_STATUS_404, message = "Polygon Not Found for id: <polygonId>",
                    response = ErrorResponse.class),
            @ApiResponse(code = HTTP_STATUS_500, message = "Server Error",
                    response = ErrorResponse.class)
    })
    public VehiclesResponse getCars(@PathVariable("polygonId") String polygonId) {
        log.debug("getCars for polygonId: {}", polygonId);
        List<String> vins = queryService.getCars(polygonId);
        return new VehiclesResponse(vins);
    }

    @GetMapping("/polygon/{vin}")
    @ApiOperation(value = "Get the polygon which contains the car with given VIN",
            response = PolygonResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_STATUS_200, message = "The polygonId corresponding to the polygon",
                    response = PolygonResponse.class),
            @ApiResponse(code = HTTP_STATUS_404, message = "Vehicle Not Found for VIN: <vin>",
                    response = ErrorResponse.class),
            @ApiResponse(code = HTTP_STATUS_500, message = "Server Error",
                    response = ErrorResponse.class)
    })
    public PolygonResponse getPolygon(@PathVariable("vin") String vin) {
        log.debug("getPolygon for VIN: {}", vin);
        String polygonId = queryService.getPolygon(vin);
        return new PolygonResponse(polygonId);
    }
}
