package br.com.cooperativa.assembleia.votacaoassembleiaapi.controller;

import br.com.cooperativa.assembleia.votacaoassembleiaapi.dto.ControllerExceptionAdviceDto;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.AssociateUnableToVoteException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.ResourceNotFoundException;
import br.com.cooperativa.assembleia.votacaoassembleiaapi.exception.VotingSessionExpiredException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ControllerExceptionAdviceDto resourceNotFoundHandler(ResourceNotFoundException ex) {
        logger.info("Resource Not Found", ex);
        return buildReturnDto(ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ControllerExceptionAdviceDto invalidParamHandler(Exception ex) {
        logger.info("Invalid Param On Endpoint Calls", ex);
        return buildReturnDto(ex.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ControllerExceptionAdviceDto feignErrorHandler(FeignException ex) {
        logger.error("Error on user-info service", ex);
        return buildReturnDto(
                String.format("Failed to communicate with cpf service validation - %s", ex.getMessage())
        );
    }

    @ExceptionHandler({AssociateUnableToVoteException.class, VotingSessionExpiredException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ControllerExceptionAdviceDto unableToVoteHandler(Exception ex) {
        logger.error("Associate unable to vote", ex);
        return buildReturnDto(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ControllerExceptionAdviceDto internalServerErrorHandler(Exception ex) {
        logger.error("Exception thrown", ex);
        return buildReturnDto(
                String.format("Internal Server Error - %s", ex.getMessage())
        );
    }

    private ControllerExceptionAdviceDto buildReturnDto(String message) {
        return new ControllerExceptionAdviceDto(new Date(), message);
    }

}
