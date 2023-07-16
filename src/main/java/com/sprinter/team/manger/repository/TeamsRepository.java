package com.sprinter.team.manger.repository;

import com.sprinter.team.manger.dao.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Data Repository class for Teams Entity
 */
@Repository
public interface TeamsRepository extends JpaRepository<Team, Long> {

  /**
   * Retrieve Teams Entities for  Pageable object
   *
   * @param pageable identifies unique contact
   * @return Page<Team>
   */
  Page<Team> findAll(Pageable pageable);
}
