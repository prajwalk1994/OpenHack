package edu.sjsu.cmpe275.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.Entity.TeamMember;

@Repository
public interface TeamMemberDao extends JpaRepository<TeamMember, Integer>{
	List<TeamMember> findTeamMemberByUserId(int userId);

	List<TeamMember> findTeamMemberByTeamIdAndUserId(int teamid, int userid);
}
