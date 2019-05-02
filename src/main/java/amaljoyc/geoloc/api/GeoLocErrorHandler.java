package amaljoyc.geoloc.api;

import amaljoyc.geoloc.api.dto.ErrorResponse;
import amaljoyc.geoloc.api.util.ControllerUtils;
import amaljoyc.geoloc.exception.PolygonNotFoundException;
import amaljoyc.geoloc.exception.VehicleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by amaljoyc on 02.05.19.
 */
@Slf4j
@RestControllerAdvice
public class GeoLocErrorHandler {

    @ExceptionHandler({PolygonNotFoundException.class, VehicleNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException exception) {
        log.warn("Not Found: {}", exception.getMessage());
        return ControllerUtils.notFound(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.warn("Bad Request: {}", exception.getMessage());
        return ControllerUtils.badRequest(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Unexpected exception: ", exception);
        return ControllerUtils.internalServerError();
    }
}
