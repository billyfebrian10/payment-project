package com.billyfebrian.payment.project.assignment.service.impl;

import com.billyfebrian.payment.project.assignment.dto.InsertPaymentDto;
import com.billyfebrian.payment.project.assignment.dto.PagingResponseDto;
import com.billyfebrian.payment.project.assignment.dto.PaymentFilterRequest;
import com.billyfebrian.payment.project.assignment.dto.PaymentResponseDto;
import com.billyfebrian.payment.project.assignment.dto.UpdatePaymentDto;
import com.billyfebrian.payment.project.assignment.entity.Payment;
import com.billyfebrian.payment.project.assignment.entity.PaymentType;
import com.billyfebrian.payment.project.assignment.exception.PaymentException;
import com.billyfebrian.payment.project.assignment.repository.PaymentRepository;
import com.billyfebrian.payment.project.assignment.repository.PaymentTypeRepository;
import com.billyfebrian.payment.project.assignment.repository.specification.PaymentFilterSpecification;
import com.billyfebrian.payment.project.assignment.service.PaymentService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  public static final String PAYMENT_NOT_EXIST = "Payment not exist";
  private PaymentRepository paymentRepository;
  private PaymentTypeRepository paymentTypeRepository;

  @Override
  public PaymentResponseDto insertPayment(InsertPaymentDto insertPaymentDto) {
    PaymentType paymentType = findPaymentTypeByPaymentTypeId(
        insertPaymentDto.getPaymentTypeId());
    Payment savedPayment = paymentRepository.save(toPaymentEntity(insertPaymentDto, paymentType));
    return toPaymentDto(savedPayment);
  }

  private PaymentType findPaymentTypeByPaymentTypeId(Long paymentTypeId) {
    return paymentTypeRepository.findById(paymentTypeId)
        .orElseThrow(() -> new RuntimeException("payment type not found"));
  }

  @Override
  public PaymentResponseDto findOne(Long id) {
    Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentException(
        PAYMENT_NOT_EXIST));
    return toPaymentDto(payment);
  }

  @Override
  public PaymentResponseDto updatePayment(Long paymentId, UpdatePaymentDto updatePaymentDto) {
    Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentException(
        PAYMENT_NOT_EXIST));
    payment.setAmount(updatePaymentDto.getAmount());
    payment.setPaymentType(findPaymentTypeByPaymentTypeId(updatePaymentDto.getPaymentTypeId()));
    payment.setCustomerId(updatePaymentDto.getCustomerId());
    Payment updatedPayment = paymentRepository.save(payment);
    return toPaymentDto(updatedPayment);
  }

  @Override
  public void deletePayment(Long paymentId) {
    Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentException(
        PAYMENT_NOT_EXIST));
    paymentRepository.delete(payment);
  }

  @Override
  public PagingResponseDto<PaymentResponseDto> filterByPagination(
      PaymentFilterRequest paymentFilterRequest) {
    PageRequest pageRequest = PageRequest.of(paymentFilterRequest.getPage(),
        paymentFilterRequest.getLimit(), Sort.by(Direction.DESC, "paymentDate"));
    Specification<Payment> paymentSpecification =
        new PaymentFilterSpecification(paymentFilterRequest);
    Page<Payment> pageResult = paymentRepository.findAll(paymentSpecification, pageRequest);
    return
        new PagingResponseDto<>(pageResult.getTotalElements(), pageResult.getTotalPages(),
            pageResult.getSize(), toListOfPaymentDto(pageResult.getContent()));
  }

  private static Payment toPaymentEntity(InsertPaymentDto insertPaymentDto,
      PaymentType paymentType) {
    return Payment.builder()
        .amount(insertPaymentDto.getAmount())
        .customerId(insertPaymentDto.getCustomerId())
        .paymentType(paymentType)
        .paymentDate(new Date()).build();
  }

  private static PaymentResponseDto toPaymentDto(Payment paymentEntity) {
    return PaymentResponseDto.builder()
        .paymentId(paymentEntity.getId())
        .amount(paymentEntity.getAmount())
        .paymentType(paymentEntity.getPaymentType().getPaymentTypeName())
        .paymentDate(paymentEntity.getPaymentDate())
        .customerId(paymentEntity.getCustomerId())
        .build();
  }

  private static List<PaymentResponseDto> toListOfPaymentDto(List<Payment> paymentPage) {
    return paymentPage.stream().map(PaymentServiceImpl::toPaymentDto).collect(Collectors.toList());
  }
}
