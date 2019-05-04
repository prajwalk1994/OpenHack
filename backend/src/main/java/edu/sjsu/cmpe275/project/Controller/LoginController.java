package edu.sjsu.cmpe275.project.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Service.UserService;

@RestController
@RequestMapping("/")
public class LoginController {

	@Autowired
	UserService userService;

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

	// @PostMapping("/signUp"){
	//
	// }
}
