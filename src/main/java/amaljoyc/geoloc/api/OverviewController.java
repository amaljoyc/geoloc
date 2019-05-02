package amaljoyc.geoloc.api;

import amaljoyc.geoloc.api.dto.ErrorResponse;
import amaljoyc.geoloc.api.dto.overview.OverviewResponse;
import amaljoyc.geoloc.service.QueryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static amaljoyc.geoloc.api.util.SwaggerConstants.HTTP_STATUS_200;
import static amaljoyc.geoloc.api.util.SwaggerConstants.HTTP_STATUS_500;

/**
 * Created by amaljoyc on 02.05.19.
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class OverviewController {

    @Autowired
    private QueryService queryService;

    @GetMapping("/overview")
    @ApiOperation(value = "Get complete overview of every Polygons and Vehicles",
            response = OverviewResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_STATUS_200, message = "Success",
                    response = OverviewResponse.class),
            @ApiResponse(code = HTTP_STATUS_500, message = "Server Error",
                    response = ErrorResponse.class)
    })
    public OverviewResponse getOverview() {
        log.debug("get complete overview!");
        return queryService.getCurrentOverview();
    }
}
