package amaljoyc.geoloc.api.util;

import amaljoyc.geoloc.api.ErrorResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by amaljoyc on 02.05.19.
 */
@UtilityClass
public class ControllerUtils {

    public static final <T> ResponseEntity<T> ok(T value) {
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    public static final ResponseEntity<ErrorResponse> notFound(String errorMessage) {
        return new ResponseEntity<>(new ErrorResponse(errorMessage), HttpStatus.NOT_FOUND);
    }

    public static final ResponseEntity<ErrorResponse> badRequest(String errorMessage) {
        return new ResponseEntity<>(new ErrorResponse(errorMessage), HttpStatus.BAD_REQUEST);
    }

    public static final ResponseEntity<ErrorResponse> internalServerError() {
        return new ResponseEntity<>(new ErrorResponse("Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
