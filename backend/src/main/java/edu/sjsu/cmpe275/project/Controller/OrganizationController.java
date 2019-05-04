package edu.sjsu.cmpe275.project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import edu.sjsu.cmpe275.project.Entity.Organization;

import edu.sjsu.cmpe275.project.Service.OrganizationService;


@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class OrganizationController {
	@Autowired
	OrganizationService organizationService;

	@GetMapping("/organization/{organizationId}")
	public ResponseEntity<Organization> getOrganization(@PathVariable int organizationId) {
		try {
			Optional<Organization> organization = this.organizationService.getOrganization(organizationId);
			if (!organization.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(organization.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/organization/{organizationId}")
	public ResponseEntity<List<Organization>> getAllOrganizations() {
		try {
			List<Organization> organization = this.organizationService.getAllOrganizations();
			return ResponseEntity.ok(organization);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/organization/{organizationId}")
	public ResponseEntity<Organization> putOrganization(@RequestBody Organization organization  ){
		try {
			
			this.organizationService.addOrganization(organization);
			return ResponseEntity.ok().build();
		}
		catch(Exception e) { 
			return ResponseEntity.badRequest().build();}
	}
	
	@DeleteMapping("/organization/{organizationId}")
	public ResponseEntity<Organization> deleteOrganization(@RequestBody Organization organization  ){
		try {
			
			this.organizationService.deleteOrganization(organization.getId());
			return ResponseEntity.ok().build();
		}
		catch(Exception e) { 
			return ResponseEntity.badRequest().build();}
	}

	
//	@PostMapping("/organization/{organizationId}")
//	public ResponseEntity<Organization> updateOrganization(@PathVariable int organizationId, @RequestBody Organization organization){
//		try {
//			Optional<User> user = this.userService.getUser(userId);
//			if(!user.isPresent()) {
//				return ResponseEntity.notFound().build();
//			}
//			User updatedUser = user.get();
//			updatedUser.setProfile(userProfile);
//			this.userService.addUser(updatedUser);
//			return ResponseEntity.ok().build();
//		}
//		catch(Exception e) { return ResponseEntity.badRequest().build();}
//	}
}
