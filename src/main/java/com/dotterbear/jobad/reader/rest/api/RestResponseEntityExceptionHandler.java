package com.dotterbear.jobad.reader.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.dotterbear.jobad.reader.rest.api.utils.ApiUtils;
import com.dotterbear.jobad.rest.model.BaseResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private static Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

  @ExceptionHandler(value = IllegalArgumentException.class)
  protected ResponseEntity<BaseResponse> handleConflict(IllegalArgumentException e,
      WebRequest request) {
    log.warn("handleConflic", e);
    return new ResponseEntity<BaseResponse>(ApiUtils.buildBaseResponse(e), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<BaseResponse> handleInternalException(Exception e, WebRequest request) {
    log.error("handleInternalException", e);
    return new ResponseEntity<BaseResponse>(ApiUtils.buildBaseResponse(e),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
