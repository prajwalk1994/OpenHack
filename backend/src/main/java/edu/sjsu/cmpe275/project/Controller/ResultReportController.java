package edu.sjsu.cmpe275.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Service.HackathonService;
import edu.sjsu.cmpe275.project.Service.HackathonTeamsService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class ResultReportController {
	@Autowired
	HackathonService hackathonService;
	
	@Autowired
	HackathonTeamsService hackathonTeamService;
	
	@GetMapping("/finalHackathons")
	public ResponseEntity<Object> getAllFinalHackathons(){
		try {
			return new ResponseEntity<Object>(this.hackathonService.getFinalHackathons(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
