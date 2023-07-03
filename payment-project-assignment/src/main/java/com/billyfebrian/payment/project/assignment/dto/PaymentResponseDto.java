package com.billyfebrian.payment.project.assignment.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDto {
  private Long paymentId;
  private Double amount;
  private String paymentType;
  private Long customerId;
  private Date paymentDate;

}
