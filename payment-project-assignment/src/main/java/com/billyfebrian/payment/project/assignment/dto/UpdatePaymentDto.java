package com.billyfebrian.payment.project.assignment.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePaymentDto {
  @NotNull
  private Double amount;
  @NotNull
  private Long paymentTypeId;
  @NotNull
  private Long customerId;
}
