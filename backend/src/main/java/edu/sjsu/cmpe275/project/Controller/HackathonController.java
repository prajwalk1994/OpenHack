package edu.sjsu.cmpe275.project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.Hackathon;
import edu.sjsu.cmpe275.project.Service.HackathonService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class HackathonController {
	@Autowired
	HackathonService hackathonService;

	@PostMapping("hackathon")
	public ResponseEntity<Hackathon> createHackathon(@RequestBody Hackathon hackathon,
			@RequestParam("email") String email) {
		// check if admin
		// mandatory attribute check
		System.out.println("Inside Create Hackathon");
		int len = email.length();
		if (!email.substring(len - 8, len).equals("sjsu.edu")) {
			return ResponseEntity.badRequest().build();
		}
		if (hackathon.getName() == null || hackathon.getName().length() == 0 || hackathon.getStartDate() == null
				|| hackathon.getEndDate() == null || hackathon.getDescription() == null
				|| hackathon.getDescription().length() == 0) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(this.hackathonService.addHackathon(hackathon));
	}
	
	@GetMapping("hackathons")
	public ResponseEntity<List<Hackathon>> getHackathons(){
		return ResponseEntity.ok(this.hackathonService.getAllHackathons());
	}
	
	@GetMapping("hackathon/{id}")
	public ResponseEntity<Hackathon> getHackathon(@PathVariable("id") int id){
		try {
			Optional<Hackathon> hackathon = this.hackathonService.getHackathon(id);
			if(!hackathon.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(hackathon.get());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
