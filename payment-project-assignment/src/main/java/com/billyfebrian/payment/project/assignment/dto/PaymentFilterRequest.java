package com.billyfebrian.payment.project.assignment.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentFilterRequest {

  @NotBlank
  private String paymentType;
  @NotNull
  private Long customerId;
  private LocalDate dateFrom;
  private LocalDate dateTo;
  private int limit;
  private int page;

}
