package edu.sjsu.cmpe275.project.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORG_MEMBERS")
public class OrganizationMembers implements Serializable {
	
	private enum Approve{
		Yes,
		No
	}
	
	@Id
	@ManyToOne
	@JoinColumn
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn
	private Organization organization;
	
	@Enumerated(EnumType.STRING)
	private Approve approval;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Approve getApproval() {
		return approval;
	}

	public void setApproval(Approve approval) {
		this.approval = approval;
	}

	
}
