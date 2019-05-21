package edu.sjsu.cmpe275.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.Entity.HackathonTeams;

@Repository
public interface HackathonTeamsDao extends JpaRepository<HackathonTeams, Integer>{
	
	List<HackathonTeams> findHackathonTeamsByTeamIdId(int teamId);
	
	Optional<HackathonTeams> findHackathonTeamsByHackIdAndTeamId(int hackId, int teamId);
	Optional<HackathonTeams> findHackathonTeamsByHackIdIdAndTeamIdId(int hackId, int teamId);
	@Query(value = "select h.* from hackathon_teams h, team t where h.team_id_id = t.id order by t.score desc", nativeQuery = true)
	List<HackathonTeams> findHackathonTeamsByHackIdId(int hackId);
}
