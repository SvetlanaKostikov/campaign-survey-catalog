package com.crowdsourcing.campaign.survey.api.web;


import com.crowdsourcing.campaign.survey.api.web.jwt.InvalidTokenException;
import com.crowdsourcing.campaign.survey.catalog.domain.OperationUnauthorizedException;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyPassportOperationException;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyPassportNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity defaultExceptionHandler(Exception e) {
        return new ResponseEntity<>(getResponseBody(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            OperationUnauthorizedException.class,
            InvalidTokenException.class
    })
    public ResponseEntity<Object> handleJwtException(Exception e) {
        return new ResponseEntity<>(getResponseBody(e), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({
            SurveyPassportOperationException.class,
    })
    public ResponseEntity<Object> operationExceptionHandler(Exception e) {
        return new ResponseEntity<>(getResponseBody(e), HttpStatus.OK);
    }

    @ExceptionHandler({
            SurveyPassportNotFoundException.class
    })
    public ResponseEntity<Object> notFoundExceptionHandler(Exception e) {
        return new ResponseEntity<>(getResponseBody(e), HttpStatus.NOT_FOUND);
    }


    private Map<String, Object> getResponseBody(Exception e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("success", "false");
        body.put("message", e.getMessage());

        return body;
    }

}
