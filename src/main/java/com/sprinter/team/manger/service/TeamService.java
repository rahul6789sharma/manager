package com.sprinter.team.manger.service;

import com.sprinter.team.manger.common.mapper.TeamMapper;
import com.sprinter.team.manger.dto.TeamDto;
import com.sprinter.team.manger.dao.Team;

import com.sprinter.team.manger.exception.TeamsApiException;
import com.sprinter.team.manger.repository.TeamsRepository;

import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

  @Resource
  private TeamsRepository teamsRepository;
  @Resource
  private TeamMapper teamMapper;

  public Team saveTeam(final TeamDto teamDto) {
    return teamsRepository.save(teamMapper.teamDtoToDao(teamDto));
  }

  public Team getTeam(final Long id) {
    return teamsRepository.findById(id)
        .orElseThrow(() -> new TeamsApiException("Team not found with id " + id));
  }

  public Page<Team> findTeamWithPagination(final int offset, final int pageSize,
      final String sortBy, final Direction dir) {
    return teamsRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(dir, sortBy)));
  }

}
