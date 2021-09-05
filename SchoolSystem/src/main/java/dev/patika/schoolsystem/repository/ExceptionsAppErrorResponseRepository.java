package dev.patika.schoolsystem.repository;

import dev.patika.schoolsystem.entity.ExceptionsAppErrorResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExceptionsAppErrorResponseRepository extends CrudRepository<ExceptionsAppErrorResponse,Long> {

    List<ExceptionsAppErrorResponse> getExceptionsAppErrorResponsesByExceptionType(String exceptionType);
    List<ExceptionsAppErrorResponse> getExceptionsAppErrorResponsesByExceptionThrowDate(LocalDate exceptionThrowDate);
    List<ExceptionsAppErrorResponse> findExceptionsAppErrorResponsesByStatus(int statusCode);

}
