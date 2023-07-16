package com.sprinter.team.manger.integrationtest.controller;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprinter.team.manger.TestDataFactory;
import com.sprinter.team.manger.dao.Team;
import com.sprinter.team.manger.dto.TeamDto;
import com.sprinter.team.manger.repository.TeamsRepository;
import com.sprinter.team.manger.response.TeamResponse;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class TeamsControllerTest {

  private static ObjectMapper objectMapper;

  @Autowired
  private TeamsRepository teamsRepository;

  @Autowired
  private MockMvc mockMvc;

  @BeforeAll
  public static void init() {
    objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
  }

  @BeforeEach
  public void setup() {
    teamsRepository.deleteAll();
  }

  @Test
  void shouldReturnInputParmValidationError() throws Exception {

    this.mockMvc.perform(post("/teams")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(TeamDto.builder().build())))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString(
            "{\"acronym\":\"Teams acronym can't be null\",\"name\":\"Teams name can't be null\"}")));
  }

  @Test
  void shouldCreateTeams() throws Exception {

    this.mockMvc.perform(post("/teams")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(TestDataFactory.buildTestTeamDtoWithoutPlayersData())))
        .andDo(print())
        .andExpect(status().isCreated());

    final MvcResult mvcResult = this.mockMvc.perform(get("/teams")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andReturn();

    final TeamResponse<Team> response =
        objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
            new TypeReference<>() {
            });

    assertNotNull(response);
    assertNotNull(response.getData());

    // validate no of search results
    assertEquals(1, response.getData().size());

    // validate pagination
    assertEquals(0, response.getPagination().getPage());
    assertEquals(1, response.getPagination().getTotalPages());
    assertEquals(1, response.getPagination().getTotalResults());
  }

  @Test
  void shouldCreateTeamsWithPlayer() throws Exception {

    this.mockMvc.perform(post("/teams")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(TestDataFactory.buildTestTeamDtoWithPlayersData())))
        .andDo(print())
        .andExpect(status().isCreated());

    final MvcResult mvcResult = this.mockMvc.perform(get("/teams")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andReturn();

    final TeamResponse<Team> response =
        objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
            new TypeReference<>() {
            });

    assertNotNull(response);
    assertNotNull(response.getData());
    assertEquals(2, response.getData().stream().findFirst().get().getPlayers().size());
    // validate no of search results
    assertEquals(1, response.getData().size());

    // validate pagination
    assertEquals(0, response.getPagination().getPage());
    assertEquals(1, response.getPagination().getTotalPages());
    assertEquals(1, response.getPagination().getTotalResults());
  }

  @Test
  void shouldCreateTeamsWithPlayerPageResponse() throws Exception {

    this.mockMvc.perform(post("/teams")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(TestDataFactory.buildTestTeamDtoWithPlayersData("Ateam"))))
        .andDo(print())
        .andExpect(status().isCreated());

    this.mockMvc.perform(post("/teams")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(TestDataFactory.buildTestTeamDtoWithPlayersData("Bteam"))))
        .andDo(print())
        .andExpect(status().isCreated());

    final MvcResult mvcResult = this.mockMvc.perform(
            get("/teams?offset=0&pageSize=5&sortBy=name&dir=DESC")
                .contentType(APPLICATION_JSON))
        .andDo(print())
        .andReturn();

    final TeamResponse<Team> response =
        objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
            new TypeReference<>() {
            });

    assertNotNull(response);
    assertNotNull(response.getData());
    assertEquals("Bteam", response.getData().get(0).getName());
    assertEquals("Ateam", response.getData().get(1).getName());

    // validate no of search results
    assertEquals(2, response.getData().size());

    // validate pagination
    assertEquals(0, response.getPagination().getPage());
    assertEquals(1, response.getPagination().getTotalPages());
    assertEquals(2, response.getPagination().getTotalResults());
  }

  private static String asJsonString(final Object obj) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
