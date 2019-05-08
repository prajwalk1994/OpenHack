package edu.sjsu.cmpe275.project.Entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
public class Profile {
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "URL", nullable = true)
	private String url;
	
	@Column(name = "BUSINESS_TITLE")
	private String businessTitle;
	
	@OneToOne
	@JoinColumn(name = "ORG_ID")
	@JsonIgnoreProperties(value = {"address"})
	private Organization organization;
	
	@Column(name = "ABOUT_ME")
	private String aboutMe;
	
	@Embedded
	private Address address;

	public Profile() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBusinessTitle() {
		return businessTitle;
	}

	public void setBusinessTitle(String businessTitle) {
		this.businessTitle = businessTitle;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
