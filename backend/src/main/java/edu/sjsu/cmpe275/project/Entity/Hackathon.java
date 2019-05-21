package edu.sjsu.cmpe275.project.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.JoinColumn;;

@Entity
@Table(name = "HACKATHON")
public class Hackathon {
	
	public enum Status{
		Open,
		Closed,
		Final
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADMIN")
	private String admin;
	
	@Column(name = "STARTDATE")
	private Date startDate;

	@Column(name = "ENDDATE")
	private Date endDate;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToMany
	@JoinTable(name = "HACK_JUDGE", joinColumns = {
			@JoinColumn(name = "USER_ID", table = "USER", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "HACKATHON_ID", referencedColumnName = "ID") })
	@JsonIgnoreProperties(value = { "password", "verified", "accessToken", "profile" })
	private List<User> judgeList;
	
	@ManyToMany
	@JoinTable(name = "HACK_ORG", joinColumns = {
			@JoinColumn(name = "ORG_ID", table = "ORGANIZATION", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "HACKATHON_ID", referencedColumnName = "ID") })
	@JsonIgnoreProperties(value = { "address", "description" })
	private List<Organization> orgList;
	

	@Column(name = "REGFEE")
	private int regFee;

	@Column(name = "MINTEAM")
	private int minTeam;

	@Column(name = "MAXTEAM")
	private int maxTeam;

	@Column(name = "SPONDISCOUNT")
	private int sponDiscount;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy = "hackId", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<HackathonTeams> HackathonTeams;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getJudgeList() {
		return judgeList;
	}

	public void setJudgeList(List<User> judgeList) {
		this.judgeList = judgeList;
	}

	public List<Organization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}

	public int getRegFee() {
		return regFee;
	}

	public void setRegFee(int regFee) {
		this.regFee = regFee;
	}

	public int getMinTeam() {
		return minTeam;
	}

	public void setMinTeam(int minTeam) {
		this.minTeam = minTeam;
	}

	public int getMaxTeam() {
		return maxTeam;
	}

	public void setMaxTeam(int maxTeam) {
		this.maxTeam = maxTeam;
	}

	public int getSponDiscount() {
		return sponDiscount;
	}

	public void setSponDiscount(int sponDiscount) {
		this.sponDiscount = sponDiscount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@JsonIgnore
	public List<HackathonTeams> getHackathonTeams() {
		return HackathonTeams;
	}

	public void setHackathonTeams(List<HackathonTeams> hackathonTeams) {
		HackathonTeams = hackathonTeams;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

}
