package com.billyfebrian.payment.project.assignment.repository.specification;

import com.billyfebrian.payment.project.assignment.dto.PaymentFilterRequest;
import com.billyfebrian.payment.project.assignment.entity.Payment;
import com.billyfebrian.payment.project.assignment.entity.PaymentType;
import com.billyfebrian.payment.project.assignment.util.DateUtil;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class PaymentFilterSpecification implements Specification<Payment> {

  private PaymentFilterRequest filterRequest;

  @Override
  public Predicate toPredicate(Root<Payment> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    Join<Payment, PaymentType> paymentType = root.join("paymentType");
    Predicate paymentTypeCriteria = criteriaBuilder.equal(paymentType.get("paymentTypeName"),
        filterRequest.getPaymentType());
    Predicate customerIdCriteria = criteriaBuilder.equal(root.get("customerId"),
        filterRequest.getCustomerId());
    Predicate getDateCriteria = getDateCriteria(criteriaBuilder, root, filterRequest);
    // avoid fetch paymentType if the query used for count because we don't need fetch for count
    // query
    if (query.getResultType() != Long.class) {
      root.fetch("paymentType", JoinType.INNER);
    }
    return criteriaBuilder.and(paymentTypeCriteria, customerIdCriteria, getDateCriteria);
  }

  private static Predicate getDateCriteria(CriteriaBuilder criteriaBuilder,
      Root<Payment> root, PaymentFilterRequest filter) {
    Date startDate;
    Date endDate;
    Path<Date> datePath = root.get("paymentDate");
    if (filter.getDateFrom() != null && filter.getDateTo() != null) {
      startDate = DateUtil.fromLocalDate(filter.getDateFrom());
      endDate = DateUtil.fromLocalDate(filter.getDateTo().plusDays(1));
      Predicate greaterThanOrEqualTo = criteriaBuilder.greaterThanOrEqualTo(datePath, startDate);
      Predicate lessThan = criteriaBuilder.lessThan(datePath, endDate);
      return criteriaBuilder.and(greaterThanOrEqualTo, lessThan);
    }else if (filter.getDateFrom() != null) {
      startDate = DateUtil.fromLocalDate(filter.getDateFrom());
      return criteriaBuilder.greaterThanOrEqualTo(datePath, startDate);
    }else if (filter.getDateTo() != null) {
      endDate = DateUtil.fromLocalDate(filter.getDateTo().plusDays(1));
      return criteriaBuilder.lessThan(datePath, endDate);
    }
    return criteriaBuilder.conjunction();
  }
}
