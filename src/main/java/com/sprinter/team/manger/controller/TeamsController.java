package com.sprinter.team.manger.controller;

import com.sprinter.team.manger.common.utils.ControllerUtils;
import com.sprinter.team.manger.dto.TeamDto;
import com.sprinter.team.manger.dao.Team;
import com.sprinter.team.manger.response.TeamResponse;
import com.sprinter.team.manger.service.TeamService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TeamsController end Points for interaction with app
 */
@RestController
@RequestMapping(path = "/teams")
@Slf4j
public class TeamsController {

  @Resource
  private TeamService teamService;

  @GetMapping
  public ResponseEntity<TeamResponse<Team>> getAllTeams(
      @RequestParam(defaultValue = "0") int offset,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "id") String sortBy,
      @RequestParam(defaultValue = "ASC") Direction dir) {

    return ControllerUtils.buildOkResponse(
        teamService.findTeamWithPagination(offset, pageSize, sortBy, dir));
  }

  @PostMapping
  public ResponseEntity<Team> saveTeam(@Valid @RequestBody TeamDto teamDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(teamService.saveTeam(teamDto));
  }

}