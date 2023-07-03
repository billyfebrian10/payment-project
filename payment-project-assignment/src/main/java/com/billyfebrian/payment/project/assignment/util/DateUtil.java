package com.billyfebrian.payment.project.assignment.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.persistence.Id;

public class DateUtil {

  public static Date fromLocalDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  public static LocalDate convertFromEpoch(Long epoch) {
    if (epoch == null) {
      return null;
    }
    return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDate();
  }



}
