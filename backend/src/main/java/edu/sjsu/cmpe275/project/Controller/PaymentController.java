package edu.sjsu.cmpe275.project.Controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.Hackathon;
import edu.sjsu.cmpe275.project.Entity.Organization;
import edu.sjsu.cmpe275.project.Entity.Profile;
import edu.sjsu.cmpe275.project.Entity.Team;
import edu.sjsu.cmpe275.project.Entity.TeamMember;
import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Repository.TeamMemberDao;
import edu.sjsu.cmpe275.project.Service.HackathonService;
import edu.sjsu.cmpe275.project.Service.MailingService;
import edu.sjsu.cmpe275.project.Service.TeamMemberService;
import edu.sjsu.cmpe275.project.Service.TeamService;
import edu.sjsu.cmpe275.project.Service.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class PaymentController {
	@Autowired
	MailingService mailService;
	@Autowired
	TeamMemberService tms;
	@Autowired
	TeamMemberDao tmd;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HackathonService hackathonService;
	
	@Autowired
	TeamService teamService;

	@PostMapping("checkPayment1")
	public ResponseEntity<Object> makePayment() {
		System.out.println();

		Hackathon hackathon = new Hackathon();
		int teamid = 6;
		int userid = 2;
		String email = "kovurivinay@gmail.com";
		mailService.makePaymentMail(hackathon, teamid, userid, email);
		return null;
	}

	@PostMapping("checkPayment")
	public ResponseEntity<Object> checkPayment(@RequestBody int teamid) {
		System.out.println();

		List<TeamMember> tm = tms.getTeamMemberByTeamId(teamid);
		for (TeamMember check : tm) {
			if (!check.isPayment()) {
				return new ResponseEntity<Object>("Make payments", HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<Object>("Success!", HttpStatus.OK);
	}

	@GetMapping(value = "/individualPayment")
	// @CrossOrigin(origins = {"http://54.193.119.24:3000",
	// "http://localhost:3000"})
	public ResponseEntity<Object> individualPayment(@RequestParam("userid") int userid,
			@RequestParam("teamid") int teamid) {
		try {
			List<TeamMember> teamMembers = tms.getTeamMemberByTeamIdAndUserId(teamid, userid);

			if (teamMembers.size() == 0) {
				return new ResponseEntity<>("FAILURE-invalid mail!", HttpStatus.OK);
			}
			TeamMember teamMember = teamMembers.get(0);
			teamMember.setPayment(true);
			tmd.save(teamMember);

		}

		catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseEntity<>("Success!", HttpStatus.OK);
	}

	@GetMapping("/sponsorDiscount/{userId}/{hackId}")
	public ResponseEntity<Object> getSponsorDiscount(@PathVariable("userId") int userId,
			@PathVariable("hackId") int hackId) {
		try {
			Optional<User> user = this.userService.getUser(userId);
			Optional<Hackathon> hackathon = this.hackathonService.getHackathon(hackId);
			if(!user.isPresent() || !hackathon.isPresent()) {
				return new ResponseEntity<Object>("User or Hackathon not found", HttpStatus.NOT_FOUND);
			}
			Profile userProfile = user.get().getProfile();
			List<Organization> orgList = hackathon.get().getOrgList();
			if(userProfile == null) {
				return new ResponseEntity<Object>("No organization found", HttpStatus.NOT_FOUND);
			}
			if(!orgList.contains(userProfile.getOrganization())) {
				return new ResponseEntity<Object>("No organization found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Object>(hackathon.get().getSponDiscount(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("paymentStatus/{teamId}")
	public ResponseEntity<Object> getPaymentStatus(@PathVariable("teamId") int teamId){
		try {
			Map<Integer, Boolean> payment = new HashMap<>();
			Optional<Team> team = this.teamService.getTeam(teamId);
			if(!team.isPresent()) {
				return new ResponseEntity<Object>("Team not found", HttpStatus.NOT_FOUND);
			}
			List<TeamMember> teamMembers = team.get().getTeamMembers();
			for(TeamMember member : teamMembers) {
				payment.put(member.getUser().getId(), member.isPayment());
			}
			return new ResponseEntity<Object>(payment, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
