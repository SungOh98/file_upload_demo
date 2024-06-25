package com.example.demo.controller;


import com.example.demo.Exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex) {
        log.error("예상치 못한 에러 발생! {} \n {}", ex.getMessage(), ex.getStackTrace());
        return ResponseEntity.internalServerError().body(ErrorResponse.from("예상치못한 에러 발생"));


    }


}
