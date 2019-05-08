package edu.sjsu.cmpe275.project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.project.Entity.HackathonTeams;
import edu.sjsu.cmpe275.project.Entity.TeamMember;
import edu.sjsu.cmpe275.project.Repository.TeamMemberDao;

@Service
@Transactional
public class TeamMemberService {
	@Autowired
	TeamMemberDao teamMemberDao;
	
	public Optional<TeamMember> getTeamMember(int id) {
		return this.teamMemberDao.findById(id);
	}
	
	public TeamMember addTeamMember(TeamMember teamMember) {
		return this.teamMemberDao.save(teamMember);
	}
	
	public List<TeamMember> getTeamMemberByUser(int userId){
		return this.teamMemberDao.findTeamMemberByUserId(userId);
	}
	public List<TeamMember> getTeamMemberByTeamIdAndUserId(int teamid, int userid){
		return this.teamMemberDao.findTeamMemberByTeamIdAndUserId(teamid, userid);
	}
}
