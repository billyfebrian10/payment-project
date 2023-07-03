package com.billyfebrian.payment.project.assignment.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponseDto<T>  {

  private long totalRecords;
  private long totalPage;
  private long pageSize;
  private List<T> data;

}
