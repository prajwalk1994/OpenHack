package edu.sjsu.cmpe275.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.Entity.TeamMember;

@Repository
public interface TeamMemberDao extends JpaRepository<TeamMember, Integer>{
	List<TeamMember> findTeamMemberByUserId(int userId);

	List<TeamMember> findTeamMemberByTeamIdAndUserId(int teamid, int userid);
	List<TeamMember> findTeamMemberByTeamId(int teamid);
	
	@Query(value = "select tm.* from hackathon_teams t, teammember tm, hackathon h where tm.teamid = t.team_id_id and t.hack_id_id = h.id and h.id = ?1", nativeQuery = true)
	List<TeamMember> findTeamMemberByHackathon(int hackId);
}
