package dev.patika.schoolsystem.exceptions;

import dev.patika.schoolsystem.entity.ExceptionsAppErrorResponse;
import dev.patika.schoolsystem.repository.ExceptionsAppErrorResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ExceptionsAppErrorResponseRepository responseRepository;


    @ExceptionHandler({CourseIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionsAppErrorResponse> handleException(CourseIsAlreadyExistException exception){

        ExceptionsAppErrorResponse response = errorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({EmptyListException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionsAppErrorResponse> handleException(EmptyListException exception){

        ExceptionsAppErrorResponse response = errorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({IdNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionsAppErrorResponse> handleException(IdNotFoundException exception){

        ExceptionsAppErrorResponse response = errorResponse(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({InstructorIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionsAppErrorResponse> handleException(InstructorIsAlreadyExistException exception){

        ExceptionsAppErrorResponse response = errorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({StudentAgeNotValidException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ExceptionsAppErrorResponse> handleException(StudentAgeNotValidException exception){

        ExceptionsAppErrorResponse response = errorResponse(HttpStatus.FORBIDDEN, exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler({StudentNumberForOneCourseExceededException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ExceptionsAppErrorResponse> handleException(StudentNumberForOneCourseExceededException exception){

        ExceptionsAppErrorResponse response = errorResponse(HttpStatus.FORBIDDEN, exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

    }

    private ExceptionsAppErrorResponse errorResponse(HttpStatus httpStatus, String message){

        ExceptionsAppErrorResponse response = new ExceptionsAppErrorResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(message);
        response.setExceptionType(httpStatus.name());
        response.setExceptionThrowDate(LocalDate.now());
        responseRepository.save(response);
        return response;

    }

}
