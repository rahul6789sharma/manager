package com.sprinter.team.manger.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PlayerDto {

  private Long id;
  @NotNull(message = "Player name cant be null")
  private String name;
  private int age;
  private String position;

}
