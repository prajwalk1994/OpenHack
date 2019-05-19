package edu.sjsu.cmpe275.project.Controller;

import java.util.Date;
import java.util.List;
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
import edu.sjsu.cmpe275.project.Service.HackathonService;
import edu.sjsu.cmpe275.project.Service.HackathonTeamsService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class GradingController {

	@Autowired
	HackathonService hackathonService;

	@Autowired
	HackathonTeamsService hackathonTeamsService;

	@PostMapping("/gradeHackathon/{hackId}/{teamId}/{grade}")
	public ResponseEntity<Object> gradeHackathon(@PathVariable("hackId") int hackId, @PathVariable("teamId") int teamId,
			@PathVariable("grade") float grade) {
		try {
			Optional<HackathonTeams> team = this.hackathonTeamsService.getHackathonTeamsByHackIdAndTeamId(hackId,
					teamId);
			Optional<Hackathon> hackathon = this.hackathonService.getHackathon(hackId);
			if (!team.isPresent() || !hackathon.isPresent()) {
				return new ResponseEntity<Object>("Could not find Hackathon Team", HttpStatus.NOT_FOUND);
			}
			Hackathon currHack = hackathon.get();
			HackathonTeams currTeam = team.get();
			if(currHack.getStatus().equals("Closed")) {
				return new ResponseEntity<Object>("Cannot grade after hackathon is closed", HttpStatus.CONFLICT);
			}
			currTeam.setGrade(grade);
			return new ResponseEntity<Object>(this.hackathonTeamsService.addHackathonTeams(currTeam), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
