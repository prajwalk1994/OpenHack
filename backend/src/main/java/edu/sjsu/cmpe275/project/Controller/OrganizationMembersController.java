package edu.sjsu.cmpe275.project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.project.Entity.Organization;
import edu.sjsu.cmpe275.project.Entity.OrganizationMembers;
import edu.sjsu.cmpe275.project.Entity.User;
import edu.sjsu.cmpe275.project.Service.OrganizationMembersService;
import edu.sjsu.cmpe275.project.Service.OrganizationService;
import edu.sjsu.cmpe275.project.Service.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class OrganizationMembersController {

	@Autowired
	OrganizationMembersService organizationMembersService;

	@Autowired
	UserService userService;

	@Autowired
	OrganizationService organizationService;

	@GetMapping("/organizationMember")
	public ResponseEntity<Object> getOrganizationsByUser(@RequestParam("userId") int userId) {
		try {
			System.out.println(userId);
			List<OrganizationMembers> members = this.organizationMembersService.getOrganizationsOfUser(userId);
			System.out.println(members);
			return new ResponseEntity<>(this.organizationMembersService.getOrganizationsOfUser(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/organizationMember/{userId}/{organizationId}")
	public ResponseEntity<Object> addOrganizationMember(@PathVariable("userId") int userId,
			@PathVariable("organizationId") int organizationId) {
		try {
			Optional<User> user = this.userService.getUser(userId);
			Optional<Organization> organization = this.organizationService.getOrganization(organizationId);
			if (!user.isPresent() || !organization.isPresent()) {
				return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
			}
			OrganizationMembers member = new OrganizationMembers();
			member.setOrganization(organization.get());
			member.setUser(user.get());
			member.setApproval(OrganizationMembers.Approve.No);
			return new ResponseEntity<Object>(this.organizationMembersService.addOrganizationMembers(member),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/activateMember/{userId}/{organizationId}")
	public ResponseEntity<Object> activateMembership(@PathVariable("userId") int userId,
			@PathVariable("organizationId") int organizationId) {
		try {
			Optional<OrganizationMembers> member = this.organizationMembersService
					.getOrganizationMembersByUserAndOrg(organizationId, userId);
			if (!member.isPresent()) {
				return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
			}
			OrganizationMembers currentMember = member.get();
			currentMember.setApproval(OrganizationMembers.Approve.Yes);
			return new ResponseEntity<Object>(this.organizationMembersService.addOrganizationMembers(currentMember), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
