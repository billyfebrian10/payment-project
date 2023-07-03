package com.billyfebrian.payment.project.assignment.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedEntityGraph(name = "paymentType", attributeNodes = @NamedAttributeNode("paymentType"))
public class Payment {

  @Id
  @GeneratedValue
  @Column(name = "payment_id")
  private Long id;

  @Column(name = "amount")
  private Double amount;

  @Column(name = "description")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "payment_type_id")
  @NotNull
  private PaymentType paymentType;

  @Column(name = "date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date paymentDate;

  @Column(name = "customer_id")
  private Long customerId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payment payment = (Payment) o;
    return Objects.equals(id, payment.id) && Objects.equals(amount,
        payment.amount) && Objects.equals(paymentType, payment.paymentType)
        && Objects.equals(paymentDate, payment.paymentDate) && Objects.equals(
        customerId, payment.customerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, amount, paymentType, paymentDate, customerId);
  }
}
