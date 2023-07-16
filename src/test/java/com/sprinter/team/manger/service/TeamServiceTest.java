package com.sprinter.team.manger.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.sprinter.team.manger.TestDataFactory;
import com.sprinter.team.manger.common.mapper.TeamMapper;
import com.sprinter.team.manger.common.mapper.TeamMapperImpl;
import com.sprinter.team.manger.dao.Team;
import com.sprinter.team.manger.repository.TeamsRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

  @Mock
  private TeamsRepository teamsRepository;

  @Spy
  private TeamMapper teamMapper = new TeamMapperImpl();

  @InjectMocks
  private TeamService teamService;

  @Test
  void shouldSaveTeam() {
    when(this.teamsRepository.save(any())).thenReturn(TestDataFactory.buildTestTeamDao());
    final Team response = teamService.saveTeam(TestDataFactory.buildTestTeamDtoWithPlayersData());

    assertNotNull(response);
    assertEquals("Team1", response.getName());
    assertEquals("acronym", response.getAcronym());
    assertEquals(1, response.getPlayers().size());
    assertEquals("Player1", response.getPlayers().get(0).getName());
    assertEquals(30, response.getPlayers().get(0).getAge());
  }

  @Test
  void shouldGetAllTeams() {
    when(this.teamsRepository.findAll(any(PageRequest.class))).thenReturn(
        TestDataFactory.buildTestTeamDaoWithPaging());
    Page<Team> teamResponse = teamService.findTeamWithPagination(0, 1, "id", Direction.ASC);
    assertNotNull(teamResponse);
    assertNotNull(teamResponse.getContent());
    assertEquals("Team1", teamResponse.getContent().get(0).getName());
    assertEquals(1, teamResponse.getTotalElements());
    assertEquals(1, teamResponse.getTotalPages());
  }

}
