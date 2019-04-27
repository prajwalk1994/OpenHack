package edu.sjsu.cmpe275.project.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="HACKATHON_TEAMS")
public class HackathonTeams implements Serializable{

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

	public Hackathon getHackId() {
		return hackId;
	}

	public void setHackId(Hackathon hackId) {
		this.hackId = hackId;
	}

	public Team getTeamId() {
		return teamId;
	}

	public void setTeamId(Team teamId) {
		this.teamId = teamId;
	}

	public String getGrade() {
		return Grade;
	}

	public void setGrade(String grade) {
		Grade = grade;
	}

	public String getPayments() {
		return Payments;
	}

	public void setPayments(String payments) {
		Payments = payments;
	}

	public String getSubmission_status() {
		return Submission_status;
	}

	public void setSubmission_status(String submission_status) {
		Submission_status = submission_status;
	}

	public String getDiscount() {
		return Discount;
	}

	public void setDiscount(String discount) {
		Discount = discount;
	}
	
	
	
	
}
