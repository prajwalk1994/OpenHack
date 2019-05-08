package edu.sjsu.cmpe275.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.project.Entity.OrganizationMembers;
import edu.sjsu.cmpe275.project.Entity.User;

@Repository
public interface OrganizationMembersDao extends JpaRepository<OrganizationMembers, Integer> {

//	@Query("select a from org_members a where user_id = ?1")
	List<OrganizationMembers> findOrganizationMembersByUserId(int userId);
	List<OrganizationMembers> findOrganizatonMembersByOrganizationIdAndUserId(int organizationId, int userId);
}
