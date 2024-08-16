package kadriozcan.marketplaceapp.exception;

import kadriozcan.marketplaceapp.common.Constants;
import kadriozcan.marketplaceapp.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleException(Exception e) {
        return new ResponseEntity<>(new BaseResponse(Constants.ResponseCodes.UNKNOWN_ERROR, e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponse> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(new BaseResponse(Constants.ResponseCodes.USER_NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseResponse> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(new BaseResponse(Constants.ResponseCodes.ACCESS_DENIED, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
