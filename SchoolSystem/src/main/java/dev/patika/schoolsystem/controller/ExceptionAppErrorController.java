package dev.patika.schoolsystem.controller;

import dev.patika.schoolsystem.entity.ExceptionsAppErrorResponse;
import dev.patika.schoolsystem.service.ExceptionsAppErrorResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * It is a control class that enables operations within the service class to be performed using get mapping on the web base.
 */
@RestController
@RequestMapping("/exception")
@RequiredArgsConstructor
public class ExceptionAppErrorController {

    private final ExceptionsAppErrorResponseService errorResponseService;

    @GetMapping("/list")
    public ResponseEntity<List<ExceptionsAppErrorResponse>> findAllException(){

        return new ResponseEntity<>(errorResponseService.findAllExceptions(), HttpStatus.OK);

    }

    @GetMapping("/exceptionDate/{throwDate}")
    public ResponseEntity<List<ExceptionsAppErrorResponse>> getExceptionsByThrowDate(@PathVariable String  throwDate){

        return new ResponseEntity<>(errorResponseService.findExceptionsByThrowDate(throwDate),HttpStatus.OK);

    }

    @GetMapping("/exceptionType/{type}")
    public ResponseEntity<List<ExceptionsAppErrorResponse>> getExceptionsByType(@PathVariable String type){

        return new ResponseEntity<>(errorResponseService.findExceptionsByTypeName(type),HttpStatus.OK);

    }

    @GetMapping("/exceptionStatusCode/{statusCode}")
    public ResponseEntity<List<ExceptionsAppErrorResponse>> getExceptionByStatusCode(@PathVariable int statusCode){

        return new ResponseEntity<>(errorResponseService.findExceptionsByStatusCode(statusCode),HttpStatus.OK);

    }
}
