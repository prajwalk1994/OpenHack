package edu.sjsu.cmpe275.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.Entity.Organization;
import edu.sjsu.cmpe275.project.Entity.OrganizationMembers;

@Repository
public interface OrganizationDao extends JpaRepository<Organization, Integer>{
	List<Organization> findOrganizationByOwnerId(int ownerId);
}
