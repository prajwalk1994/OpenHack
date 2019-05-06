package edu.sjsu.cmpe275.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Service.TeamMemberService;
import edu.sjsu.cmpe275.project.Service.TeamService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class TeamController {
	@Autowired
	TeamService teamService;
	
	@Autowired
	TeamMemberService teamMemberService;
	
	
}
