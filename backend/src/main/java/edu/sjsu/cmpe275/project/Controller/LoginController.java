package edu.sjsu.cmpe275.project.Controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Repository.UserDao;
import edu.sjsu.cmpe275.project.Service.UserService;

@RestController
@RequestMapping("/")
public class LoginController {
	
	@Autowired 
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@RequestBody User user) {
		Optional<User> loginUser = userService.getUser(user.getId());
		if(!loginUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		if(loginUser.get().getPassword()==user.getPassword()) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/signUp")
//	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public ResponseEntity<Object> signup(@RequestBody User req){
		String email = req.getEmail();
		String password = req.getPassword();
		String emailCheck[] = email.split("@");
		String type;
		if(emailCheck[1].equals("sjsu.edu")){
			type = "ADMIN";
		} else {
			type = "USER";
		}
		
		// To check if the email and password are not empty
		if(email.equals("")){
			return new ResponseEntity<>("FAILURE-EMPTY_EMAIL", HttpStatus.OK);
		}
		if(password.equals("")){
			return new ResponseEntity<>("FAILURE-EMPTY_PASSWORD", HttpStatus.OK);
		}
		
		//To check if the user already exists
		List<User> users = userDao.getUserByEmail(email);
		if(users.size() > 0){
			return new ResponseEntity<>("Already Exists!", HttpStatus.OK);
		}
		
//		Date newdate=new Date();
//	    java.sql.Timestamp presentdate = new Timestamp(newdate.getTime());
		User user = new User();
		user.setPassword(req.getPassword());
		user.setEmail(email);
//		user.setActivated(0);
//		user.setSubscription(0);
//		user.setSignupdate(presentdate);
//		String code = String.valueOf(new Random(System.nanoTime()).nextInt(100000));
//		user.setCode(code);
		user = userDao.save(user);
		
//        emailSer.sendEmail(code, email);
		
		return new ResponseEntity<>("Success!", HttpStatus.OK);
	}
}
