package com.billyfebrian.payment.project.assignment.controller;

import com.billyfebrian.payment.project.assignment.dto.ResponseDto;
import com.billyfebrian.payment.project.assignment.exception.PaymentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController  {

  @ExceptionHandler(value = PaymentException.class)
  public ResponseDto<Object> handlePaymentException(PaymentException paymentException) {
    return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, paymentException);
  }

}
