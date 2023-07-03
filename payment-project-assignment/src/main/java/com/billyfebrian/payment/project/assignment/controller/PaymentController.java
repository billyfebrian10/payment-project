package com.billyfebrian.payment.project.assignment.controller;

import com.billyfebrian.payment.project.assignment.dto.InsertPaymentDto;
import com.billyfebrian.payment.project.assignment.dto.PagingResponseDto;
import com.billyfebrian.payment.project.assignment.dto.PaymentFilterRequest;
import com.billyfebrian.payment.project.assignment.dto.PaymentResponseDto;
import com.billyfebrian.payment.project.assignment.dto.ResponseDto;
import com.billyfebrian.payment.project.assignment.dto.UpdatePaymentDto;
import com.billyfebrian.payment.project.assignment.service.PaymentService;
import com.billyfebrian.payment.project.assignment.util.DateUtil;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments")
@AllArgsConstructor
// in this example we use @CrossOrigin to ease the development. in the real scenario we can
// use nginx to proxy request from frontend to backend, so the backend no need to configure
// cross origin.
@CrossOrigin
public class PaymentController {

  private PaymentService paymentService;

  @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseDto<PaymentResponseDto> insertPayment(@RequestBody @Valid InsertPaymentDto request) {
    return new ResponseDto<>(HttpStatus.OK, paymentService.insertPayment(request));
  }

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseDto<PaymentResponseDto> findById(@PathVariable Long id) {
    return new ResponseDto<>(HttpStatus.OK, paymentService.findOne(id));
  }

  @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseDto<PaymentResponseDto> updatePayment(@PathVariable Long id, @RequestBody @Valid
      UpdatePaymentDto updatePaymentDto) {
    return new ResponseDto<>(HttpStatus.OK, paymentService.updatePayment(id, updatePaymentDto));
  }

  @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseDto<PaymentResponseDto> deletePayment(@PathVariable Long id) {
    paymentService.deletePayment(id);
    return new ResponseDto<>(HttpStatus.OK);
  }

  @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseDto<PagingResponseDto<PaymentResponseDto>> filterPayment(@RequestParam(
      "paymentType") String paymentType,
      @RequestParam("customerId") Long customerId,
      @RequestParam(value = "dateFrom", required = false) Long dateFrom,
      @RequestParam(value = "dateTo", required = false) Long dateTo,
      @RequestParam("limit") int limit, @RequestParam("page") int page) {

    PaymentFilterRequest request = PaymentFilterRequest.builder()
        .customerId(customerId)
        .paymentType(paymentType)
        .dateFrom(DateUtil.convertFromEpoch(dateFrom))
        .dateTo(DateUtil.convertFromEpoch(dateTo))
        .limit(limit)
        .page(page-1).build();
    return new ResponseDto<>(HttpStatus.OK, paymentService.filterByPagination(request));
  }

}
