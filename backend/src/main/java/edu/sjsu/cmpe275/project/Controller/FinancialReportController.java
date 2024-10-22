package edu.sjsu.cmpe275.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Service.TeamMemberService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class FinancialReportController {

	@Autowired
	TeamMemberService teamMemberService;
	
	@GetMapping("/getMetrics/{hackId}")
	public ResponseEntity<Object> getHackathonMetrics(@PathVariable("hackId") int hackId){
		try {
			return new ResponseEntity<Object>(this.teamMemberService.getHackathonMetrics(hackId), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
