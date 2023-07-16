package com.sprinter.team.manger.common.mapper;


import com.sprinter.team.manger.dto.TeamDto;
import com.sprinter.team.manger.dao.Team;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper class to convert from DTO to DAO and vice versa
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface TeamMapper {

  TeamDto teamDaoToDto(Team team);

  Team teamDtoToDao(TeamDto teamDto);
}
