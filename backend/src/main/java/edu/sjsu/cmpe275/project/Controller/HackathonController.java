package edu.sjsu.cmpe275.project.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.Hackathon;
import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Repository.UserDao;
import edu.sjsu.cmpe275.project.Service.HackathonService;

class HackathonTemp{
	private String name;
	private Date startDate;
	private Date endDate;
	private String description;
	private List<String> judgeList;
	private int regFee;
	private int minTeam;
	private int maxTeam;
	private int sponDiscount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getJudgeList() {
		return judgeList;
	}
	public void setJudgeList(List<String> judgeList) {
		this.judgeList = judgeList;
	}
	public int getRegFee() {
		return regFee;
	}
	public void setRegFee(int regFee) {
		this.regFee = regFee;
	}
	public int getMinTeam() {
		return minTeam;
	}
	public void setMinTeam(int minTeam) {
		this.minTeam = minTeam;
	}
	public int getMaxTeam() {
		return maxTeam;
	}
	public void setMaxTeam(int maxTeam) {
		this.maxTeam = maxTeam;
	}
	public int getSponDiscount() {
		return sponDiscount;
	}
	public void setSponDiscount(int sponDiscount) {
		this.sponDiscount = sponDiscount;
	}

}
@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class HackathonController {
	@Autowired
	HackathonService hackathonService;
	@Autowired
	UserDao userDao;
	
	@PostMapping("hackathon")
	public ResponseEntity<Object> createHackathon(@RequestBody HackathonTemp hackathonTemp,
			@RequestParam("email") String email) {
		// check if admin
		// mandatory attribute check
		Hackathon hackathon= new Hackathon();
		System.out.println(hackathonTemp);
		hackathon.setName(hackathonTemp.getName());
		hackathon.setDescription(hackathonTemp.getDescription());
		hackathon.setStartDate(hackathonTemp.getStartDate());
		hackathon.setEndDate(hackathonTemp.getEndDate());
		hackathon.setMinTeam(hackathonTemp.getMinTeam());
		hackathon.setMaxTeam(hackathonTemp.getMaxTeam());
		hackathon.setRegFee(hackathonTemp.getRegFee());
		hackathon.setSponDiscount(hackathonTemp.getSponDiscount());
		
		List<User> users = new ArrayList<User>();
		for(String str : hackathonTemp.getJudgeList()){
			ArrayList<User> user = userDao.findUserByEmail(str);
			if(user.size()==0) {
				return new ResponseEntity<>("FAILURE-Invalid_Judges", HttpStatus.OK);
			}
			users.add(user.get(0));
		}
		hackathon.setJudgeList(users);
		
		System.out.println(hackathon.getJudgeList());
		System.out.println("Inside Create Hackathon");
		int len = email.length();
		if (!email.substring(len - 8, len).equals("sjsu.edu")) {
			return new ResponseEntity<>("FAILURE-Invalid_admin", HttpStatus.OK);
		}
		
		if (hackathon.getName() == null || hackathon.getName().length() == 0 || hackathon.getStartDate() == null
				|| hackathon.getEndDate() == null || hackathon.getDescription() == null
				|| hackathon.getDescription().length() == 0 || hackathon.getRegFee()<0 || hackathon.getJudgeList().size()==0
				|| hackathon.getMaxTeam()<=0 || hackathon.getMinTeam()<=0 || hackathon.getSponDiscount()>50) {
			return new ResponseEntity<>("FAILURE-Empty-fields", HttpStatus.OK);
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
	
	@PostMapping("hackathon/changeStatus/{hackId}/{status}")
	public ResponseEntity<Object> changeHackathonStatus(@PathVariable("hackId") int hackId, @PathVariable("status") String status){
		try {
			Optional<Hackathon> hackathon = this.hackathonService.getHackathon(hackId);
			if(!hackathon.isPresent()) {
				return new ResponseEntity<Object>("Hackathon Not found", HttpStatus.NOT_FOUND);
			}
			Hackathon currentHackathon = hackathon.get();
			currentHackathon.setStatus(status);
			return new ResponseEntity<Object>(this.hackathonService.addHackathon(currentHackathon), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
