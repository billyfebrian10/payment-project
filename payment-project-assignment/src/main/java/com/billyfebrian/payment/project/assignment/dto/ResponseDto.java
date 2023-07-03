package com.billyfebrian.payment.project.assignment.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDto<T> {
  private int status;
  private String errorCode;
  private String errorMessage;
  private T value;

  public ResponseDto (HttpStatus httpStatus, T value) {
    this(httpStatus);
    this.value = value;
  }

  public ResponseDto (HttpStatus httpStatus, String errorMessage) {
    this.status = httpStatus.value();
    this.errorCode = httpStatus.name();
    this.errorMessage = errorMessage;
  }

  public ResponseDto (HttpStatus httpStatus) {
    this.status = httpStatus.value();
    this.errorCode = httpStatus.name();
    this.errorMessage = httpStatus.name();
  }


}
