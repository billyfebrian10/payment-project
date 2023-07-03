package com.billyfebrian.payment.project.assignment.service;

import com.billyfebrian.payment.project.assignment.dto.PaymentFilterRequest;
import com.billyfebrian.payment.project.assignment.dto.InsertPaymentDto;
import com.billyfebrian.payment.project.assignment.dto.PagingResponseDto;
import com.billyfebrian.payment.project.assignment.dto.PaymentResponseDto;
import com.billyfebrian.payment.project.assignment.dto.UpdatePaymentDto;

public interface PaymentService {

  PaymentResponseDto insertPayment(InsertPaymentDto insertPaymentDto);

  PaymentResponseDto findOne(Long id);

  PaymentResponseDto updatePayment(Long paymentId, UpdatePaymentDto updatePaymentDto);

  void deletePayment(Long paymentId);

  PagingResponseDto<PaymentResponseDto> filterByPagination(
      PaymentFilterRequest paymentFilterRequest);
}
