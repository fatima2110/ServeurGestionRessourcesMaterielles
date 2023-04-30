package ma.fstf.ServeurGestionRessourcesMaterielles.Utils;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtExceptionHandler {

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException ex) {
        String error = "Your session has expired. Please log in again.";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
