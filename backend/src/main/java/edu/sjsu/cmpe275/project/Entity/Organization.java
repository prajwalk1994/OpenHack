package edu.sjsu.cmpe275.project.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ORGANIZATION")
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Embedded
	private Address address;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToOne 
	@JoinColumn(name = "OWNERID")
	@JsonIgnoreProperties(value = {"password", "role", "accessToken", "profile"})
	private User owner;
	
	@OneToMany(mappedBy = "organization")
	private List<OrganizationMembers> organizationRequests;
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

	public List<OrganizationMembers> getOrganizationRequests() {
		return organizationRequests;
	}

	public void setOrganizationRequests(List<OrganizationMembers> organizationRequests) {
		this.organizationRequests = organizationRequests;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", address=" + address + ", description=" + description
				+ ", owner=" + owner + "]";
	}
	
	

}
