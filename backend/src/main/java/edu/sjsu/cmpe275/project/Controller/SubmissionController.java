package edu.sjsu.cmpe275.project.Controller;

import java.util.List;

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

import edu.sjsu.cmpe275.project.Entity.HackathonTeams;
import edu.sjsu.cmpe275.project.Service.HackathonTeamsService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class SubmissionController {
	@Autowired
	HackathonTeamsService hackathonTeamsService;

	@PostMapping("submission/{teamId}/{hackId}")
	public ResponseEntity<Object> addSubmissionUrl(@PathVariable("teamId") int teamId,
			@PathVariable("hackId") int hackId, @RequestParam("url") String url) {
		try {
			List<HackathonTeams> hackathonTeams = this.hackathonTeamsService.getHackathonTeamsByHackIdAndTeamId(hackId, teamId);
			if(hackathonTeams.size() == 0) {
				return new ResponseEntity<Object>("No team with hackathon present", HttpStatus.FORBIDDEN);
			}
			HackathonTeams currentTeam = hackathonTeams.get(0);
//			currentTeam.setSubmissionUrl(url);
			this.hackathonTeamsService.addHackathonTeams(currentTeam);
			return new ResponseEntity<Object>(currentTeam, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
