package edu.sjsu.cmpe275.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.Entity.HackathonTeams;

@Repository
public interface HackathonTeamsDao extends JpaRepository<HackathonTeams, Integer>{
	
	List<HackathonTeams> findHackathonTeamsByTeamIdId(int teamId);
	Optional<HackathonTeams> findHackathonTeamsByHackIdAndTeamId(int hackId, int teamId);
}
