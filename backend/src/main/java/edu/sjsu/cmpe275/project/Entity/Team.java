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
import javax.persistence.JoinTable;
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
	
	@OneToMany
	@JoinTable(
			name = "TEAM",
			joinColumns = @JoinColumn(name = "USERID", referencedColumnName = "ID")
	)
	private List<User> user;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ROLE")
	private Role role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}	
	
	
}
