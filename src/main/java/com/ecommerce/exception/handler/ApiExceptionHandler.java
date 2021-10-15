package com.ecommerce.exception.handler;

import com.ecommerce.exception.InternalServerErrorException;
import com.ecommerce.exception.NonUniqueCustomerException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.util.ResponseWrapper;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseWrapper handleInternalServerSErrorException(InternalServerErrorException ex){
        return ResponseWrapper.internalServerError()
                .rootMessage(ResponseWrapper.ResponseMessage.of("500", "internal.error", ex.getMessage()))
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseWrapper handleResourceNotFoundException(ResourceNotFoundException ex){
        return ResponseWrapper.notFound()
                .rootMessage(ResponseWrapper.ResponseMessage.of("404", "not.found", ex.getMessage()))
                .build();
    }

    @ExceptionHandler(NonUniqueCustomerException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseWrapper handleNonUniqueCustomerException(NonUniqueCustomerException ex){
        return ResponseWrapper.badRequest()
                .rootMessage(ResponseWrapper.ResponseMessage.of("400", "bad.request", ex.getMessage()))
                .build();
    }
}
