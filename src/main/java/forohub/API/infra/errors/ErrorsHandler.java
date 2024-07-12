package forohub.API.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandle404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandle400(MethodArgumentNotValidException e) {
        List<ErrorValidationDTO> errors = e.getFieldErrors().stream()
                .map(ErrorValidationDTO::new)
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacionsDeNegocio(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
    }

    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity errorHandlerValidacionesDeIntegridad(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
    }

    private record ErrorValidationDTO(String field, String error) {
        public ErrorValidationDTO(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    private record ErrorMessageDTO(String errorMessage) { }
}
