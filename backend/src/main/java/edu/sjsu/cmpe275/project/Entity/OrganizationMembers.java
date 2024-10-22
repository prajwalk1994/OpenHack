package edu.sjsu.cmpe275.project.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ORG_MEMBERS")
public class OrganizationMembers implements Serializable {

	public static enum Approve {
		Yes, No
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties(value = {"password", "role", "accessToken", "profile"})
	private User user;

	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties(value = {"owner", "address", "organizationRequests"})
	private Organization organization;

	@Enumerated(EnumType.STRING)
	private Approve approval;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
