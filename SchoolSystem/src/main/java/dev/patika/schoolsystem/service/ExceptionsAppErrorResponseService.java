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

@Service
@RequiredArgsConstructor
public class ExceptionsAppErrorResponseService {

    @Autowired
    private ExceptionsAppErrorResponseRepository responseRepository;

    public List<ExceptionsAppErrorResponse> findAllExceptions(){

        List<ExceptionsAppErrorResponse> errorResponseList = new ArrayList<>();
        Iterable<ExceptionsAppErrorResponse> errorResponseIterable = responseRepository.findAll();
        errorResponseIterable.iterator().forEachRemaining(errorResponseList :: add);
        if (errorResponseList.isEmpty()){

            throw new EmptyListException(ErrorMessageConstants.EMPTY_LIST);

        }
        return errorResponseList;

    }

    public List<ExceptionsAppErrorResponse> findExceptionsByThrowDate(String throwDate){

        LocalDate date = LocalDate.parse(throwDate);
        return responseRepository.getExceptionsAppErrorResponsesByExceptionThrowDate(date);

    }

    public List<ExceptionsAppErrorResponse> findExceptionsByTypeName(String typeName){

        return responseRepository.getExceptionsAppErrorResponsesByExceptionType(typeName);

    }

    public List<ExceptionsAppErrorResponse> findExceptionsByStatusCode(int statusCode){

        return responseRepository.findExceptionsAppErrorResponsesByStatus(statusCode);

    }

}
