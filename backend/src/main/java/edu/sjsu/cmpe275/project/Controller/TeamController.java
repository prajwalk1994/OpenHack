package edu.sjsu.cmpe275.project.Controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.Team;
import edu.sjsu.cmpe275.project.Entity.TeamMember;
import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Service.TeamMemberService;
import edu.sjsu.cmpe275.project.Service.TeamService;
import edu.sjsu.cmpe275.project.Service.UserService;
import edu.sjsu.cmpe275.project.Entity.TeamMember.Role;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class TeamController {
	@Autowired
	TeamService teamService;

	@Autowired
	TeamMemberService teamMemberService;

	@Autowired
	UserService userService;

	@PostMapping("/team/{teamName}/{hackId}")
	public ResponseEntity<Object> createTeam(@RequestBody Map<String, Role> usernames,
			@PathVariable("teamName") String teamName, @PathVariable("hackId") int hackId) {
		try {
			List<TeamMember> users = new ArrayList<>();
			
			for (String username : usernames.keySet()) {
				List<User> user = this.userService.getUserByUsername(username);
				if (user.size() == 0) {
					return new ResponseEntity<Object>("User " + username + " Not found", HttpStatus.NOT_FOUND);
				}
				TeamMember teamMember = new TeamMember();
				teamMember.setUser(user.get(0));
				teamMember.setRole(usernames.get(username));
				users.add(teamMember);
			}
			for (TeamMember member : users) {
				this.teamMemberService.addTeamMember(member);
				
			}
			Team team = new Team();
			team.setTeamMembers(users);
			team.setTeamName(teamName);
			return new ResponseEntity<Object>(this.teamService.addTeam(team), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/team/{userId}")
	public ResponseEntity<Object> getTeamByUser(@PathVariable("userId") int userId) {
		try {
			Optional<User> user = this.userService.getUser(userId);
			if (!user.isPresent()) {
				return new ResponseEntity<Object>("User not found", HttpStatus.NOT_FOUND);
			}
			List<TeamMember> teamMembers = this.teamMemberService.getTeamMemberByUser(userId);
			return new ResponseEntity<Object>(teamMembers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
