package edu.sjsu.cmpe275.project.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.Team;
import edu.sjsu.cmpe275.project.Service.TeamMemberService;
import edu.sjsu.cmpe275.project.Service.TeamService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class TeamMemberController {

	@Autowired
	TeamMemberService teamMemberService;
	
	@Autowired
	TeamService teamService;
	
	@GetMapping("/getTeamMembers/{teamId}")
	public ResponseEntity<Object> getTeamMembers(@PathVariable("teamId") int teamId){
		try {
			Optional<Team> team = this.teamService.getTeam(teamId);
			if(!team.isPresent()) {
				return new ResponseEntity<Object>("No team found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Object>(this.teamMemberService.getTeamMemberByTeamId(teamId), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
