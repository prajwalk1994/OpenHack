package edu.sjsu.cmpe275.project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.project.Entity.Organization;

import edu.sjsu.cmpe275.project.Repository.OrganizationDao;

@Service
@Transactional
public class OrganizationService {
	@Autowired
	OrganizationDao organizationDao;
	
	public List<Organization> getAllOrganizations() {
        return this.organizationDao.findAll();
    }
	
	public Organization addOrganization(Organization organization) {
        return this.organizationDao.save(organization);
    }
	
	public void deleteOrganization(int ID) {
        this.organizationDao.deleteById(ID);
    }
	
	public Optional<Organization> getOrganization(int ID) {
        return this.organizationDao.findById(ID);
    }
}
