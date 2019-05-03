package edu.sjsu.cmpe275.project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import edu.sjsu.cmpe275.project.Entity.HackathonTeams;

import edu.sjsu.cmpe275.project.Repository.HackathonTeamsDao;
@Service
@Transactional
public class HackathonTeamsService {
	@Autowired
	HackathonTeamsDao hackathonTeamsDao;
	
	public List<HackathonTeams> getAllHackathonTeams() {
        return this.hackathonTeamsDao.findAll();
    }
	
	public HackathonTeams addHackathonTeams(HackathonTeams hackathonTeams) {
        return this.hackathonTeamsDao.save(hackathonTeams);
    }
	
	public void deleteHackathonTeams(int ID) {
        this.hackathonTeamsDao.deleteById(ID);
    }
	
	public Optional<HackathonTeams> getHackathonTeams(int ID) {
        return this.hackathonTeamsDao.findById(ID);
    }
}