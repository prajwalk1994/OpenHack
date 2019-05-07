package edu.sjsu.cmpe275.project.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.Hackathon;
import edu.sjsu.cmpe275.project.Entity.HackathonTeams;
import edu.sjsu.cmpe275.project.Entity.Team;
import edu.sjsu.cmpe275.project.Service.HackathonService;
import edu.sjsu.cmpe275.project.Service.HackathonTeamsService;
import edu.sjsu.cmpe275.project.Service.TeamService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class HackathonTeamController {
	@Autowired
	HackathonTeamsService hackathonTeamsService;
	
	@Autowired
	HackathonService hackathonService;
	
	@Autowired
	TeamService teamService;
	
	@PostMapping("/hackathonteam/{hackId}/{teamId}")
	public ResponseEntity<Object> addTeamToHackathon(@PathVariable("hackId") int hackId, @PathVariable("teamId") int teamId){
		try {
			Optional<Hackathon> hackathon = this.hackathonService.getHackathon(hackId);
			Optional<Team> team = this.teamService.getTeam(teamId);
			if(!hackathon.isPresent() || !team.isPresent()) {
				return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
			}
			HackathonTeams hackTeam = new HackathonTeams();
			hackTeam.setHackId(hackathon.get());
			hackTeam.setTeamId(team.get());
			return new ResponseEntity<Object>(this.hackathonTeamsService.addHackathonTeams(hackTeam), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
}