package com.sprinter.team.manger.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {

  private Long id;

  @NotNull(message = "Teams name can't be null")
  private String name;

  @NotNull(message = "Teams acronym can't be null")
  private String acronym;

  @NotNull(message = "Teams budget can't be null")
  private int budget;

  private List<PlayerDto> players;
}
