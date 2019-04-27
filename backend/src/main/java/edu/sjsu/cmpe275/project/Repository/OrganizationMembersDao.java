package edu.sjsu.cmpe275.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.Entity.OrganizationMembers;

@Repository
public interface OrganizationMembersDao extends JpaRepository<OrganizationMembers, Integer>{

}
