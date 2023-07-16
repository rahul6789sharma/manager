package com.sprinter.team.manger.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pagination {

  int page;
  int totalPages;
  long totalResults;
}