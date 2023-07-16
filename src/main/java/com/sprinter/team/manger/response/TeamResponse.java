package com.sprinter.team.manger.response;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Holder for search response (used to pass data for editorials, products, etc.)
 *
 * @param <T> type of the data
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamResponse<T> {

  @Builder.Default
  private List<T> data = List.of();

  @JsonInclude(NON_NULL)
  private Pagination pagination;
}
