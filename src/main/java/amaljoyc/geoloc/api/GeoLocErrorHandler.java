package amaljoyc.geoloc.api;

import amaljoyc.geoloc.api.util.ControllerUtils;
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.warn("Bad Request: ", exception);
        return ControllerUtils.badRequest(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Unexpected exception: ", exception);
        return ControllerUtils.internalServerError();
    }
}
