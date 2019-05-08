package edu.sjsu.cmpe275.project.Entity;

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
@Table(name = "TEAMMEMBER")
public class TeamMember {
	
	public static enum Role{
		ProductManager,
		Engineer,
		FullStack,
		Designer,
		Other
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "USERID")
	@JsonIgnoreProperties(value = {"password", "role", "accessToken", "profile"})
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "TEAMID")
	@JsonIgnoreProperties(value = {"teamMembers", "score"})
	private Team team;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "PAYMENT")
	private boolean payment;

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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}
	
	
	
}
