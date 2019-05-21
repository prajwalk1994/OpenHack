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
import edu.sjsu.cmpe275.project.Entity.Hackathon.Status;
import edu.sjsu.cmpe275.project.Entity.Organization;
import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Repository.OrganizationDao;
import edu.sjsu.cmpe275.project.Repository.UserDao;
import edu.sjsu.cmpe275.project.Service.HackathonService;
import edu.sjsu.cmpe275.project.Service.UserService;

class HackathonTemp{
	private String name;
	private Date startDate;
	private Date endDate;
	private String description;
	private List<String> judgeList;
	private List<String> sponsers;
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
	public List<String> getsponsers() {
		return sponsers;
	}
	public void setOrgList(List<String> sponsers) {
		this.sponsers = sponsers;
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
	@Autowired
	OrganizationDao organizationDao;
	@Autowired
	UserService userService;
	
	@PostMapping("hackathon")
	public ResponseEntity<Object> createHackathon(@RequestBody HackathonTemp hackathonTemp,
			@RequestParam("email") String email) {
		// check if admin
		// mandatory attribute check
		Hackathon hackathon= new Hackathon();
//		System.out.println(hackathonTemp);
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
//			System.out.println(str);
			ArrayList<User> user = userDao.findUserByEmail(str);
			if(user.size()==0) {
				return new ResponseEntity<>("FAILURE-Invalid_Judges", HttpStatus.BAD_REQUEST);
			}
			users.add(user.get(0));
		}
		System.out.println(hackathonTemp.getsponsers());
		List<Organization> orgs= new ArrayList<Organization>();
		for(String str : hackathonTemp.getsponsers()) {
			System.out.println(str);
			ArrayList<Organization> org= this.organizationDao.findOrganizationByName(str);
			if(org.size()==0) {
				return new ResponseEntity<>("FAILURE-Invalid_Organizations", HttpStatus.BAD_REQUEST);
			}
			orgs.add(org.get(0));
		}
		
		hackathon.setJudgeList(users);
		hackathon.setOrgList(orgs);
		
		System.out.println(hackathon.getJudgeList());
		System.out.println(hackathon.getOrgList());
		System.out.println("Inside Create Hackathon");
		int len = email.length();
		if (!email.substring(len - 8, len).equals("sjsu.edu")) {
			return new ResponseEntity<>("FAILURE-Invalid_admin", HttpStatus.BAD_REQUEST);
		}
		
		if (hackathon.getName() == null || hackathon.getName().length() == 0 || hackathon.getStartDate() == null
				|| hackathon.getEndDate() == null || hackathon.getDescription() == null
				|| hackathon.getDescription().length() == 0 || hackathon.getRegFee()<0 || hackathon.getJudgeList().size()==0
				|| hackathon.getMaxTeam()<=0 || hackathon.getMinTeam()<=0 || hackathon.getSponDiscount()>50) {
			return new ResponseEntity<>("FAILURE-Empty_fields", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(this.hackathonService.addHackathon(hackathon), HttpStatus.OK);
	}
	
	@GetMapping("hackathons")
	public ResponseEntity<Object> getHackathons(){
		try {
		return ResponseEntity.ok(this.hackathonService.getAllHackathons());
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
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
	public ResponseEntity<Object> changeHackathonStatus(@PathVariable("hackId") int hackId, @PathVariable("status") Status status){
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
	
	@PostMapping("hackathon/{userId}/{hackId}")
	public ResponseEntity<Object> judgeHackathon(@PathVariable("userId") int userId, @PathVariable("hackId") int hackId){
		try {
			Optional<User> user = this.userService.getUser(userId);
			Optional<Hackathon> hackathon = this.hackathonService.getHackathon(hackId);
			if(!user.isPresent() || !hackathon.isPresent()) {
				return new ResponseEntity<Object>("User or Hackathon not found", HttpStatus.NOT_FOUND);
			}
			User currUser = user.get();
			Hackathon currHackathon = hackathon.get();
			List<User> currentJudgeList = currHackathon.getJudgeList();
			currentJudgeList.add(currUser);
			currHackathon.setJudgeList(currentJudgeList);
			return new ResponseEntity<Object>(this.hackathonService.addHackathon(currHackathon), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("hackathon/judges/{hackId}")
	public ResponseEntity<Object> getJudgeList(@PathVariable("hackId") int hackId){
		try {
			Optional<Hackathon> hackathon = this.hackathonService.getHackathon(hackId);
			if(!hackathon.isPresent()) {
				return new ResponseEntity<Object>("Hackathon not found", HttpStatus.NOT_FOUND);
			}
			Hackathon currHackathon = hackathon.get();
			return new ResponseEntity<Object>(currHackathon.getJudgeList(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
