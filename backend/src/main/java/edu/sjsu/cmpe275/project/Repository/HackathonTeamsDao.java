package edu.sjsu.cmpe275.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.Entity.HackathonTeams;

@Repository
public interface HackathonTeamsDao extends JpaRepository<HackathonTeams, Integer>{
	List<HackathonTeams> findHackathonTeamsByHackIdAndTeamId(int hackId, int teamId);
}
