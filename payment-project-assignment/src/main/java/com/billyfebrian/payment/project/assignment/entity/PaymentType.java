package com.billyfebrian.payment.project.assignment.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentType {

  @Id
  @GeneratedValue
  @Column(name = "payment_type_id")
  private Long id;

  @Column(name = "type_name")
  private String paymentTypeName;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentType that = (PaymentType) o;
    return Objects.equals(id, that.id) && Objects.equals(paymentTypeName,
        that.paymentTypeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, paymentTypeName);
  }
}
