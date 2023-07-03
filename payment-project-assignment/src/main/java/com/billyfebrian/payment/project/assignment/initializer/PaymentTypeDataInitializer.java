package com.billyfebrian.payment.project.assignment.initializer;

import com.billyfebrian.payment.project.assignment.entity.PaymentType;
import com.billyfebrian.payment.project.assignment.repository.PaymentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentTypeDataInitializer implements ApplicationRunner {
  private final PaymentTypeRepository paymentTypeRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    paymentTypeRepository.save(PaymentType.builder().id(1L).paymentTypeName("CASH").build());
    paymentTypeRepository.save(PaymentType.builder().id(2L).paymentTypeName("CREDIT").build());
  }
}
