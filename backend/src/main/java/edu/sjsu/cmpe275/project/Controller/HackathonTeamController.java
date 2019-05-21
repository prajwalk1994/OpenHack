package edu.sjsu.cmpe275.project.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sendgrid.Response;

import edu.sjsu.cmpe275.project.Entity.Hackathon;
import edu.sjsu.cmpe275.project.Entity.HackathonTeams;
import edu.sjsu.cmpe275.project.Entity.Team;
import edu.sjsu.cmpe275.project.Entity.TeamMember;
import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Service.HackathonService;
import edu.sjsu.cmpe275.project.Service.HackathonTeamsService;
import edu.sjsu.cmpe275.project.Service.MailingService;
import edu.sjsu.cmpe275.project.Service.TeamMemberService;
import edu.sjsu.cmpe275.project.Service.TeamService;
import edu.sjsu.cmpe275.project.Service.UserService;

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
	
	@Autowired
	TeamMemberService teamMemberService;
	
	@Autowired
	MailingService mailService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/hackathonteam/{hackId}/{teamId}")
	public ResponseEntity<Object> addTeamToHackathon(@PathVariable("hackId") int hackId, @PathVariable("teamId") int teamId){
		try {
			System.out.println("addTeamToHackathon inside Hackathon Team Controller called!");
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
	
	@PostMapping("/makePayment/{userid}/{teamid}/{email}/{amount}")
	public ResponseEntity<Object> makePayment(
			@PathVariable("userid") int userid,@PathVariable("teamid") int teamid,@PathVariable("email") String tempEmail,
			@PathVariable("amount") float amount){
		// Team members payment
		Optional<User> mainUser=userService.getUser(userid);
		List<TeamMember> a=teamMemberService.getTeamMemberByTeamIdAndUserId(teamid, userid);
		System.out.println(a.get(0).getRole());
		TeamMember currMember=a.get(0);
		currMember.setPayment(true);
		//Set amount
		currMember.setAmount(amount);
		this.teamMemberService.addTeamMember(currMember);
		List<TeamMember> a1=teamMemberService.getTeamMemberByTeamId(teamid);
		for(TeamMember member:a1) {
//			System.out.println(member.isPayment());
			if(!member.isPayment()) {
				this.mailService.confirmationMail(mainUser.get().getEmail());
				return new ResponseEntity<Object>("Your payment is done. But Team Payment Not Done", HttpStatus.OK);
			}
		}
		this.mailService.confirmationMail(mainUser.get().getEmail());
		Optional<HackathonTeams> hackTeam=hackathonTeamsService.getHackathonTeams(teamid);
		if(!hackTeam.isPresent()) {
			return new ResponseEntity<Object>("Team Not present", HttpStatus.OK);
		}
		HackathonTeams currTeam=hackTeam.get();
		currTeam.setPayments(true);
		this.hackathonTeamsService.addHackathonTeams(currTeam);
		// throw mail
		for(TeamMember member:a1) {
			this.mailService.confirmationMailTeam(member.getUser().getEmail());
		}
		System.out.println("Mail sent as team payment is done!");
		return new ResponseEntity<Object>("Team Payment is also Done and Mail Sent", HttpStatus.OK);
	
	}
	
	
	@GetMapping("/hackathonsByUser/{userId}")
	public ResponseEntity<Object> getHackathonsByUser(@PathVariable("userId") int userId){
		try {
			System.out.println("inside hackathonsByUser route in Hackathon Team Controller!");
			Optional<User> user = this.userService.getUser(userId);
			List<HackathonTeams> hackathons = new ArrayList<>();
			if(!user.isPresent()) {
				return new ResponseEntity<Object>("User not found", HttpStatus.NOT_FOUND);
			}
			List<TeamMember> teams = this.teamMemberService.getTeamMemberByUser(userId);
//			System.out.println(userId);
			for(TeamMember member : teams) {
//				System.out.println(member.getId());
				Team team = member.getTeam();
				List<HackathonTeams> hackathonTeams = this.hackathonTeamsService.getHackathonTeamsByTeamId(team.getId());
				
				for(HackathonTeams hackTeam: hackathonTeams) {
					hackathons.add(hackTeam);
				}
			}
			return new ResponseEntity<Object>(hackathons, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("teamsByHackathon/{hackId}")
	public ResponseEntity<Object> getHackathonTeamsByHackathon(@PathVariable("hackId") int hackId){
		try {
			return new ResponseEntity<Object>(this.hackathonTeamsService.getHackathonTeamsByHackIdOrdered(hackId), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("hackathonTeams")
	public ResponseEntity<Object> hackathonTeams(){
		try {
			return new ResponseEntity<Object>(this.hackathonTeamsService.getAllHackathonTeams(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/checkTeamPayment/{hackId}/{teamId}")
	public ResponseEntity<Object> checkTeamPayment(@PathVariable("hackId") int hackId, @PathVariable("teamId") int teamId){
		try {
			System.out.println("In checkTeamPayment route of Hackathon Team Controller");
			List<HackathonTeams> hackteams=this.hackathonTeamsService.getHackathonTeamsByTeamId(teamId);
			if(hackteams.size()==0) {
				return new ResponseEntity<Object>("Team Not found", HttpStatus.NOT_FOUND);
			}
			for(HackathonTeams temp:hackteams) {
				if(temp.getHackId().getId()==hackId && temp.getPayments()==true) {
					return new ResponseEntity<Object>("Payment Done", HttpStatus.OK);
				}
			}
//			if(hackteams.get(0).ge) {
//				return new ResponseEntity<Object>("Payment Done", HttpStatus.OK);
//			}
			return new ResponseEntity<Object>("All the Team members should complete the payment!", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}		
	}
}
