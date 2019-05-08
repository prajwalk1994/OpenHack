package edu.sjsu.cmpe275.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.Hackathon;
import edu.sjsu.cmpe275.project.Entity.TeamMember;
import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Repository.TeamMemberDao;
import edu.sjsu.cmpe275.project.Service.MailingService;
import edu.sjsu.cmpe275.project.Service.TeamMemberService;

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
	
	@PostMapping("checkPayment1")
	public ResponseEntity<Object> makePayment() {
		System.out.println();
		
		Hackathon hackathon=new Hackathon();
		int teamid=6;
		int userid=2;
		String email="kovurivinay@gmail.com";
		mailService.makePaymentMail(hackathon, teamid, userid, email);
		return null;
	}
	
	@GetMapping(value = "/individualPayment")
//	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public ResponseEntity<Object> individualPayment(@RequestParam("userid") int userid,
			@RequestParam("teamid") int teamid) {
		try {
			List<TeamMember> teamMembers = tms.getTeamMemberByTeamIdAndUserId(teamid,userid);

			if (teamMembers.size() == 0) {
				return new ResponseEntity<>("FAILURE-invalid mail!", HttpStatus.OK);
			}
			TeamMember teamMember = teamMembers.get(0);
			teamMember.setPayment(true);
			tmd.save(teamMember);
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		return new ResponseEntity<>("Success!", HttpStatus.OK);
	}
}
