package com.sprinter.team.manger;

import com.sprinter.team.manger.dao.Player;
import com.sprinter.team.manger.dao.Team;
import com.sprinter.team.manger.dto.PlayerDto;
import com.sprinter.team.manger.dto.TeamDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class TestDataFactory {

  public static TeamDto buildTestTeamDtoWithoutPlayersData() {
    return TeamDto.builder().name("Team1").acronym("Kansas").build();
  }

  public static TeamDto buildTestTeamDtoWithPlayersData() {
    List<PlayerDto> players = new ArrayList<>();
    players.add(PlayerDto.builder().name("Player1").age(20).position("Player1Position").build());
    players.add(PlayerDto.builder().name("Player2").age(30).position("Player2Position").build());
    return TeamDto.builder().name("Team1").acronym("Kansas").players(players).build();
  }

  public static TeamDto buildTestTeamDtoWithPlayersData(final String name) {
    List<PlayerDto> players = new ArrayList<>();
    players.add(PlayerDto.builder().name("Player1").age(20).position("Player1Position").build());
    players.add(PlayerDto.builder().name("Player2").age(30).position("Player2Position").build());
    return TeamDto.builder().name(name).acronym("Kansas").players(players).build();
  }


  public static Team buildTestTeamDao() {
    List<Player> players = new ArrayList<>();
    players.add(Player.builder().name("Player1").age(30).id(1l).build());
    return Team.builder().id(1l).name("Team1").acronym("acronym").players(players).build();
  }


  public static Page<Team> buildTestTeamDaoWithPaging() {
    List<Player> players = new ArrayList<>();
    players.add(Player.builder().name("Player1").age(30).id(1l).build());

    Team team = Team.builder().id(1l).name("Team1").acronym("acronym").players(players).build();
    List<Team> teams = new ArrayList<>();
    teams.add(team);

    final PageRequest pageable = PageRequest.of(0, 1);
    return new PageImpl<>(teams, pageable, teams.size());
  }


}
