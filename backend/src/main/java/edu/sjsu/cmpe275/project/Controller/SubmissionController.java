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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.Hackathon;
import edu.sjsu.cmpe275.project.Entity.Hackathon.Status;
import edu.sjsu.cmpe275.project.Entity.HackathonTeams;
import edu.sjsu.cmpe275.project.Service.HackathonService;
import edu.sjsu.cmpe275.project.Service.HackathonTeamsService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class SubmissionController {
	@Autowired
	HackathonTeamsService hackathonTeamsService;
	
	@Autowired
	HackathonService hackathonService;

	@PostMapping("submission/{teamId}/{hackId}")
	public ResponseEntity<Object> addSubmissionUrl(@PathVariable("teamId") int teamId,
			@PathVariable("hackId") int hackId, @RequestParam("url") String url) {
		try {
			Optional<HackathonTeams> hackathonTeams = this.hackathonTeamsService.getHackathonTeamsByHackIdAndTeamId(hackId, teamId);
			Optional<Hackathon> hackathon = this.hackathonService.getHackathon(hackId);
			if(!hackathonTeams.isPresent()) {
				return new ResponseEntity<Object>("No team with hackathon present", HttpStatus.NOT_FOUND);
			}
			Hackathon currHackathon = hackathon.get();
			HackathonTeams currentTeam = hackathonTeams.get();
			currentTeam.setSubmissionUrl(url);
			if(new Date().compareTo(currHackathon.getEndDate()) > 0 || currHackathon.getStatus().equals(Status.Closed)) {
				return new ResponseEntity<Object>("Cannot submit after End date", HttpStatus.UNAUTHORIZED);
			}
			if(currentTeam.getGrade()!=0) {
				return new ResponseEntity<Object>("Team has already been graded", HttpStatus.UNAUTHORIZED);
			}
			this.hackathonTeamsService.addHackathonTeams(currentTeam);
			return new ResponseEntity<Object>(currentTeam, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
