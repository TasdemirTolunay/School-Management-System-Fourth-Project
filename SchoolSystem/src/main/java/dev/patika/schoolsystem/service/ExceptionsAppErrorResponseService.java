package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.entity.ExceptionsAppErrorResponse;
import dev.patika.schoolsystem.exceptions.EmptyListException;
import dev.patika.schoolsystem.repository.ExceptionsAppErrorResponseRepository;
import dev.patika.schoolsystem.util.ErrorMessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods of transaction on the exceptions.
 */
@Service
@RequiredArgsConstructor
public class ExceptionsAppErrorResponseService {

    @Autowired
    private ExceptionsAppErrorResponseRepository responseRepository;

    /**
     * Gets all thrown exceptions logged in the database.
     * @return Exception List
     */
    public List<ExceptionsAppErrorResponse> findAllExceptions(){

        List<ExceptionsAppErrorResponse> errorResponseList = new ArrayList<>();
        Iterable<ExceptionsAppErrorResponse> errorResponseIterable = responseRepository.findAll();
        errorResponseIterable.iterator().forEachRemaining(errorResponseList :: add);
        if (errorResponseList.isEmpty()){

            throw new EmptyListException(ErrorMessageConstants.EMPTY_LIST);

        }
        return errorResponseList;

    }

    /**
     * Gets thrown exceptions logged in the database by throw date.
     * @param throwDate Date the error was thrown.
     * @return Exceptions by throw date.
     */
    public List<ExceptionsAppErrorResponse> findExceptionsByThrowDate(String throwDate){

        LocalDate date = LocalDate.parse(throwDate);
        return responseRepository.getExceptionsAppErrorResponsesByExceptionThrowDate(date);

    }

    /**
     * Gets thrown exceptions logged in the database by exception type.
     * @param typeName Type of exception.
     * @return Exceptions by Type.
     */
    public List<ExceptionsAppErrorResponse> findExceptionsByTypeName(String typeName){

        return responseRepository.getExceptionsAppErrorResponsesByExceptionType(typeName);

    }

    /**
     * Gets thrown exceptions logged in the database by exception status code.
     * @param statusCode exception status code
     * @return Exceptions by Status Code.
     */
    public List<ExceptionsAppErrorResponse> findExceptionsByStatusCode(int statusCode){

        return responseRepository.findExceptionsAppErrorResponsesByStatus(statusCode);

    }

}
