package edu.sjsu.cmpe275.project.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;;

@Entity
@Table(name = "HACKATHON")
public class Hackathon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

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
//	@JsonIgnoreProperties(value = { "manager", "reports", "address", "collaborators", "employer" })
	private List<User> judgeList;
	
	@ManyToMany
	@JoinTable(name = "HACK_ORG", joinColumns = {
			@JoinColumn(name = "ORG_ID", table = "ORGANIZATION", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "HACKATHON_ID", referencedColumnName = "ID") })
//	@JsonIgnoreProperties(value = { "manager", "reports", "address", "collaborators", "employer" })
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
	private String status;

}
