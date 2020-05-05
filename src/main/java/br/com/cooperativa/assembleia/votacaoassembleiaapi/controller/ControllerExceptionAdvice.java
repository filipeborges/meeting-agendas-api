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

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ControllerExceptionAdviceDto resourceNotFoundHandler(HttpServletRequest req, ResourceNotFoundException ex) {
        logger.info("Resource Not Found", ex);
        return buildReturnDto("Resource Not Found", ex.getMessage(), req);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ControllerExceptionAdviceDto invalidParamHandler(HttpServletRequest req, Exception ex) {
        logger.info("Invalid Param On Endpoint Calls", ex);
        return buildReturnDto("Invalid Param On Endpoint Calls", ex.getMessage(), req);
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    ControllerExceptionAdviceDto feignErrorHandler(HttpServletRequest req, FeignException ex) {
        logger.error("Error on user-info service", ex);
        return buildReturnDto("Failed to communicate with cpf service validation", ex.getMessage(), req);
    }

    @ExceptionHandler({AssociateUnableToVoteException.class, VotingSessionExpiredException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ControllerExceptionAdviceDto unableToVoteHandler(HttpServletRequest req, Exception ex) {
        logger.error("Associate unable to vote", ex);
        return buildReturnDto("Associate unable to vote", ex.getMessage(), req);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ControllerExceptionAdviceDto internalServerErrorHandler(HttpServletRequest req, RuntimeException ex) {
        logger.error("Exception thrown", ex);
        return buildReturnDto("Unexpected error encountered", ex.getMessage(), req);
    }

    private ControllerExceptionAdviceDto buildReturnDto(
            String message,
            String detail,
            HttpServletRequest req
    ) {
        return new ControllerExceptionAdviceDto(new Date(), message, detail, req.getServletPath());
    }

}
