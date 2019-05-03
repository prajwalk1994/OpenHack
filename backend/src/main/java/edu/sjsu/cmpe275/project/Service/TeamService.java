package edu.sjsu.cmpe275.project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import edu.sjsu.cmpe275.project.Entity.Team;

import edu.sjsu.cmpe275.project.Repository.TeamDao;

public class TeamService {
	@Autowired
	TeamDao teamDao;
	
	public List<Team> getAllTeams() {
        return this.teamDao.findAll();
    }
	
	public Team addTeam(Team team) {
        return this.teamDao.save(team);
    }
	
	public void deleteTeam(int ID) {
        this.teamDao.deleteById(ID);
    }
	
	public Optional<Team> getTeam(int ID) {
        return this.teamDao.findById(ID);
    }
}