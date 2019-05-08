package edu.sjsu.cmpe275.project.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="HACKATHON_TEAMS")
public class HackathonTeams implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
    @OneToOne
    @JoinColumn
    private Hackathon hackId;
	
    @OneToOne
    @JoinColumn
    private Team teamId;
	
	@Column(name="GRADE")
	private String Grade;
	
	@Column(name="PAYMENTS")
	private String Payments;
	
	@Column(name="SUBMISSION_STATUS")
	private String Submission_status;
	
	@Column(name = "SUBMISSION_URL")
	private String submissionUrl;
	
	@Column(name="DISCOUNT")
	private String Discount;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getSubmissionUrl() {
		return submissionUrl;
	}

	public void setSubmissionUrl(String submissionUrl) {
		this.submissionUrl = submissionUrl;
	}
	
	
	
	
}
