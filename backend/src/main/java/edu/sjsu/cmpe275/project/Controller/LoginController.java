package edu.sjsu.cmpe275.project.Controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Repository.UserDao;
import edu.sjsu.cmpe275.project.Service.MailingService;
import edu.sjsu.cmpe275.project.Service.UserService;

@RestController
@RequestMapping("/")
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	UserDao userDao;
	
	@Autowired
	PasswordEncoder pe;
	
	@Autowired
	MailingService mailService;

	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@RequestBody User user) {
		Optional<User> loginUser = userService.getUser(user.getId());
		if (!loginUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		if (loginUser.get().getPassword() == user.getPassword()) {
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
		String role;
		if(emailCheck[1].equals("sjsu.edu")){
			role = "ADMIN";
		} else {
			role = "USER";
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
		String accessToken = String.valueOf(new Random(System.nanoTime()).nextInt(10000));
		Date d=new Date();
	    java.sql.Timestamp datepresent = new Timestamp(d.getTime());
		User user = new User();
		user.setPassword(pe.encode(req.getPassword()));
		user.setEmail(email);
		user.setVerified(false);
//		user.setSubscription(0);
//		user.setSignupdate(presentdate);
		user.setAccessToken(accessToken);
		user = userDao.save(user);
		
        mailService.sendMail(accessToken, email);
		
		return new ResponseEntity<>("Success!", HttpStatus.OK);
	}
	

	@GetMapping(value="/activateLogin")
//	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public ResponseEntity<Object> activateLogin(@RequestParam("email") String email, @RequestParam("accessToken") String accessToken){
		System.out.println("accessToken "+accessToken);
		System.out.println("email "+email);
		User user = userDao.findByEmail(email);
		
		if(user == null){
			return new ResponseEntity<>("FAILURE-invalid mail!", HttpStatus.OK);
		}
		
		if(user.getAccessToken().equals(accessToken)){
			user.setVerified(true);
			userDao.save(user);
			return new ResponseEntity<>("Success!", HttpStatus.OK);
		} else {
			System.out.println("inside verify failure");
			return new ResponseEntity<>("FAILURE-invalid code!", HttpStatus.OK);
		}
	}

}
