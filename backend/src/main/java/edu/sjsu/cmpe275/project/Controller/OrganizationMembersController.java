package edu.sjsu.cmpe275.project.Controller;

import java.util.ArrayList;
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
import org.springframework.web.context.annotation.RequestScope;

import edu.sjsu.cmpe275.project.Entity.Organization;
import edu.sjsu.cmpe275.project.Entity.OrganizationMembers;
import edu.sjsu.cmpe275.project.Entity.Profile;
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
			System.out.println("organizationMember route inside Organization Member Controller!");
			Optional<User> user = this.userService.getUser(userId);
			Optional<Organization> organization = this.organizationService.getOrganization(organizationId);
//			System.out.println(user);
//			System.out.println(organization);
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

	@PostMapping("/activateMember/{userId}/{ownerId}/{organizationId}")
	public ResponseEntity<Object> activateMembership(@PathVariable("userId") int userId,
			@PathVariable("ownerId") int ownerId, @PathVariable("organizationId") int organizationId) {
		try {
			Optional<User> owner = this.userService.getUser(ownerId);
			Optional<Organization> organization = this.organizationService.getOrganization(organizationId);
			List<OrganizationMembers> member = this.organizationMembersService
					.getOrganizationMembersByUserAndOrg(organizationId, userId);
			if (!owner.isPresent() || !organization.isPresent()) {
				return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
			}
			if(owner.get().getId() != organization.get().getOwner().getId()) {
				return new ResponseEntity<Object>("Owner not the actual owner", HttpStatus.FORBIDDEN);
			}
			OrganizationMembers currentMember = member.get(0);
			currentMember.setApproval(OrganizationMembers.Approve.Yes);
			return new ResponseEntity<Object>(this.organizationMembersService.addOrganizationMembers(currentMember),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/leaveOrganization/{userId}/{organizationId}")
	public ResponseEntity<Object> leaveOrganization(@PathVariable("userId") int userId,
			@PathVariable("organizationId") int organizationId) {
		
		try {
			List<OrganizationMembers> orgMem=this.organizationMembersService.getOrganizationMembersByUserAndOrg(organizationId, userId);
			this.organizationMembersService.deleteOrganization(orgMem.get(0).getId());
			return new ResponseEntity<Object>("Left from Organization", HttpStatus.OK);
//			Optional<User> user = this.userService.getUser(userId);
//			Optional<Organization> organization = this.organizationService.getOrganization(organizationId);
//			if (!user.isPresent() || !organization.isPresent()) {
//				return new ResponseEntity<Object>("User or Organization with the given Id not found",
//						HttpStatus.NOT_FOUND);
//			}
//			User currentUser = user.get();
//			
//			Profile currentUserProfile = currentUser.getProfile();
//			System.out.println("hHeeeeeeeeeeeeeeeeeeeee");
//			if (currentUserProfile != null) {
////				if (currentUserProfile.getOrganization().equals(organization.get())) {
//				System.out.println(currentUserProfile.getOrganization());
//					currentUserProfile.setOrganization(null);
//					currentUser.setProfile(currentUserProfile);
//					return new ResponseEntity<Object>(this.userService.addUser(currentUser), HttpStatus.OK);
//				} else {
//					return new ResponseEntity<Object>("User does not belong to this organization",
//							HttpStatus.NOT_MODIFIED);
//				}
////			} else {
////				return new ResponseEntity<Object>("User Profile not updated", HttpStatus.NOT_FOUND);
////			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Error while leaving Organization", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/organizationRequests/{ownerId}")
	public ResponseEntity<Object> getOrganizationRequests(@PathVariable("ownerId") int ownerId){
		try {
			Optional<User> owner = this.userService.getUser(ownerId);
			if(!owner.isPresent()) {
				return new ResponseEntity<Object>("Owner not found", HttpStatus.NOT_FOUND);
			}
			List<OrganizationMembers> requests = new ArrayList<>();
			List<Organization> organizations = this.organizationService.getOrganizationsByOwnerId(ownerId);
			for(Organization organization : organizations) {
				requests.addAll(organization.getOrganizationRequests());
			}
			return new ResponseEntity<Object>(requests, HttpStatus.OK); 
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
