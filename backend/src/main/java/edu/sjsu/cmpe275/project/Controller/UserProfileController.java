package edu.sjsu.cmpe275.project.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.Profile;
import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Service.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class UserProfileController {

	@Autowired
	UserService userService;

	@GetMapping("profile/{userId}")
	public ResponseEntity<Profile> getProfile(@PathVariable int userId) {
		try {
			Optional<User> user = this.userService.getUser(userId);
			if (!user.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(user.get().getProfile());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/profile/{id}")
	public ResponseEntity<Profile> updateProfile(@PathVariable("id") int userId, @RequestBody Profile userProfile) {
		try {
			Optional<User> user = this.userService.getUser(userId);
			if (!user.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			User updatedUser = user.get();
			updatedUser.setProfile(userProfile);
			this.userService.addUser(updatedUser);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
