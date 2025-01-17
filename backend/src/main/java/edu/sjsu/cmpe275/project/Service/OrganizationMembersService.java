package edu.sjsu.cmpe275.project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.project.Entity.OrganizationMembers;

import edu.sjsu.cmpe275.project.Repository.OrganizationMembersDao;
@Service
@Transactional
public class OrganizationMembersService {
	@Autowired
	OrganizationMembersDao organizationMembersDao;
	
	public List<OrganizationMembers> getAllOrganizationMembers() {
        return this.organizationMembersDao.findAll();
    }
	
	public OrganizationMembers addOrganizationMembers(OrganizationMembers organizationMembers) {
        return this.organizationMembersDao.save(organizationMembers);
    }
	
	public void deleteOrganization(int ID) {
        this.organizationMembersDao.deleteById(ID);
    }
	
	public Optional<OrganizationMembers> getOrganizationMembers(int ID) {
        return this.organizationMembersDao.findById(ID);
    }
	
	public List<OrganizationMembers> getOrganizationsOfUser(int userId){
		return this.organizationMembersDao.findOrganizationMembersByUserId(userId);
	}
	
	public List<OrganizationMembers> getOrganizationMembersByUserAndOrg(int organizationId, int userId) {
		return this.organizationMembersDao.findOrganizatonMembersByOrganizationIdAndUserId(organizationId, userId);
	}
}
