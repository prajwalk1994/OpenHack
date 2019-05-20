package edu.sjsu.cmpe275.project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.project.Entity.Hackathon;

import edu.sjsu.cmpe275.project.Repository.HackathonDao;

@Service
@Transactional
public class HackathonService {
	@Autowired
	HackathonDao hackathonDao;
	
	public List<Hackathon> getAllHackathons() {
        return this.hackathonDao.findAll();
    }
	
	public Hackathon addHackathon(Hackathon hackathon) {
        return this.hackathonDao.save(hackathon);
    }
	
	public void deleteHackathon(int ID) {
        this.hackathonDao.deleteById(ID);
    }
	
	public List<Hackathon> getFinalHackathons(){
		return this.hackathonDao.findAllFinalHackathons();
	}
	
	public Optional<Hackathon> getHackathon(int ID) {
        return this.hackathonDao.findById(ID);
    }
}
