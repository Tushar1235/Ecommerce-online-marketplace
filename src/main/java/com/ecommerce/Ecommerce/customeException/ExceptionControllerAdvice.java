package com.ecommerce.Ecommerce.customeException;

import com.ecommerce.Ecommerce.view.SignUpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<SignUpResponse> handleCustomException(CustomException customException){
         return  new ResponseEntity<>(new SignUpResponse(false, customException.getMessage()), HttpStatus.BAD_REQUEST);
 }
}
