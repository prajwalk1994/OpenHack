package edu.sjsu.cmpe275.project.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TEAM")
public class Team {
	
	public enum Role{
		ProductManager,
		Engineer,
		FullStack,
		Designer,
		Other
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@OneToOne
	@JoinColumn(table = "USER", name= "ID")
	private User user;
	
	@OneToOne
	@JoinColumn(table = "HACKATHON", name = "ID")
	private Hackathon hackathon;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ROLE")
	private Role role;

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

	public Hackathon getHackathon() {
		return hackathon;
	}

	public void setHackathon(Hackathon hackathon) {
		this.hackathon = hackathon;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@OneToMany
//	(mappedBy = "hackId", cascade = CascadeType.ALL)
	@JoinColumn(name = "teamId",table="HackathonTeams")
    private List<HackathonTeams> HackathonTeams;		
	
	
}
