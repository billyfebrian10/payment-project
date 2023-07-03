package com.billyfebrian.payment.project.assignment.repository;

import com.billyfebrian.payment.project.assignment.entity.Payment;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long>,
    JpaSpecificationExecutor<Payment> {
  @EntityGraph(value = "paymentType")
  Optional<Payment> findById(Long id);

}
