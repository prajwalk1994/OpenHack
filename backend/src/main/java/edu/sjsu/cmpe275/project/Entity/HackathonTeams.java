package edu.sjsu.cmpe275.project.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="HACKATHON_TEAMS")
public class HackathonTeams {

	@Id
    @ManyToOne
    @JoinColumn
    private Hackathon hackId;
	
	@Id
    @ManyToOne
    @JoinColumn
    private Team teamId;
	
	@Column(name="GRADE")
	private String Grade;
	
	@Column(name="PAYMENTS")
	private String Payments;
	
	@Column(name="SUBMISSION_STATUS")
	private String Submission_status;
	
	@Column(name="DISCOUNT")
	private String Discount;
	
	
	
	
}
