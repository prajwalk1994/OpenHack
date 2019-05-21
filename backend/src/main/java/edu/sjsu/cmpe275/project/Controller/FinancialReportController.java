package edu.sjsu.cmpe275.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Service.HackathonService;
import edu.sjsu.cmpe275.project.Service.HackathonTeamsService;
import edu.sjsu.cmpe275.project.Service.TeamMemberService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class FinancialReportController {
	
	@Autowired
	HackathonService hackathonService;
	
	@Autowired
	HackathonTeamsService hackathonTeamsService;
	
	@Autowired
	TeamMemberService teamMemberService;
	
	@GetMapping("/hackathon/members/{hackId}")
	public ResponseEntity<Object> getTeamMembersByHackathon(@PathVariable("hackId") int hackId){
		try {
			return new ResponseEntity<Object>(this.teamMemberService.getTeamMemberByHackId(hackId), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
